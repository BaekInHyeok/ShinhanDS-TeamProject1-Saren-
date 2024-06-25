package com.team4.shoppingmall.prodTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.shoppingmall.buyer_inq.Buyer_InqDTO;
import com.team4.shoppingmall.buyer_inq.Buyer_InqService;
import com.team4.shoppingmall.cart.CartDTO;
import com.team4.shoppingmall.cart.CartService;
import com.team4.shoppingmall.prod_optionTest.Prod_OptionTestService;
import com.team4.shoppingmall.reviews.ReviewsDTO;
import com.team4.shoppingmall.reviews.ReviewsService;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestDTO;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestService;

@Controller
@RequestMapping("/prod")
public class ProdTestController {
	
	@Autowired
	Seller_Prod_StockTestService seller_Prod_StockTestService; //�Ǹ� ��ǰ ���
	
	@Autowired
	ProdTestService prodTestService;  //��ǰ
	
	@Autowired
	Prod_OptionTestService prod_OptionSerTestService;  //��ǰ �ɼ�
	
	@Autowired
	Buyer_InqService buyer_InqService; //������ ����(������-�Ǹ���)
	
	@Autowired
	CartService cartService; //��ٱ���
	
	@Autowired
	ReviewsService reviewsService; //����
	
	/* ��ǰ ��� ������ */
	@GetMapping("/productlistTest")
	public void productList() {
		
	}
		
	/* ��ǰ �� ������ */
	@GetMapping("/product_detail")
	public void productDetail(String prod_id, Model model) {
		
		//�ɼǸ�� �� ���� ��ȸ
		List<Object> prod_Options = prod_OptionSerTestService.selectAllOptionsByProdId(prod_id);
		System.out.println("�ɼ� ��ȸ : "+ prod_Options);
		model.addAttribute("prod_Options", prod_Options);
		
		//�Ǹ� ��ǰ �ɼǺ� ��� ��ȸ
		List<Seller_Prod_StockTestDTO> seller_prod_stockDTO = seller_Prod_StockTestService.selectSpsOptionByProdId(prod_id);
		model.addAttribute("seller_prod_stockDTO", seller_prod_stockDTO);
		
		//��ǰ ���� ��ȸ
		List<Map<String,Object>> prod_detail_info = prodTestService.selectProdDetailInfoByProdId(prod_id);
		model.addAttribute("prod_detail_info", prod_detail_info);
		System.out.println("��ǰ �� ���� ��ȸ : "+ prod_detail_info);
		
		//System.out.println("---��� ������----");
		//System.out.println(seller_prod_stockDTO);
		
		
		/* ��ǰ ���� ��� */
		System.out.println("���� ���~~ ��ǰid : " + prod_id);
		
		//��ǰid �޾ƿ��� ��� �ٽ� ����
		
		//�ֹ���id->���id->�ش� ��ǰ id�� ������ ��ȸ	
		List<ReviewsDTO> productReviews = reviewsService.selectAllProductReviewByProdId(prod_id);
		model.addAttribute("productReviews", productReviews);
		
		System.out.println("���� ���~~ : " + productReviews);
	}
	
	/* ��ٱ��� */
	//��ǰ ������ �ɼ� �� �����ϱ� -> ��ٱ��Ϸ� insert
	@GetMapping(value = "/productCartInsert", produces = "text/plain;charset=utf-8")
	public @ResponseBody String productCartInsert(HttpServletRequest request) throws UnsupportedEncodingException {
		//���õ� �ɼ� �� Ȯ�� 
		String param = request.getQueryString();
        System.out.println("Selected option: " + param);
        
        String message = null;
        
        if(param != null) {
        	System.out.println("(�ɼ�����)Selected option: " + param);
        	// color=white&size=L&quantity=1&discountPrice=40000&prod_id=%EB%82%98%EC%9D%B4%ED%82%A4%20%EB%B0%98%ED%8C%94_1234-1234
        	//{quantity=1, color=black, size=L, discountPrice=40000, prod_id=����Ű ����_1234-1234}
            //color=black, size=L, prod_id=����Ű ����_1234-1234
        	
			//param���� �Ѿ�� ���� �ɼǵ� ����
        	HashMap<String, String> map = new HashMap<>(); //
        	HashMap<String, String> optionMap = new HashMap<>(); //�ɼ�Map-Ű(�ɼǸ�):��(�ɼǰ�)
        	String[] propertis = param.split("&");
        	for(String pro: propertis) {
        		String[] keyValue = pro.split("=");
        		
        		map.put(keyValue[0], URLDecoder.decode(keyValue[1], "utf-8")); //��ǰID�� �ѱ��� decode
        		
        		//discountPrice�� ��ٱ��Ͽ� �ʿ����(param���� �Ѿ�� �ʿ����) - ���� �ʿ�
        		if(!keyValue[0].contains("quantity")&& !keyValue[0].contains("discountPrice") &&  !keyValue[0].contains("prod_id"))
        			optionMap.put(keyValue[0], keyValue[1]);
        	}
        	System.out.println(map);
        	
        	
        	//1.session���� ���� ���� - ȸ��ID
            String member_id = "testid";
           
            //2.���id�˾Ƴ��� 
            String stockId = cartService.searchStockId(optionMap, map.get("prod_id"));
            
            //�α��� �� ����� cart_id�� �˻��Ѵ�. ������ ���� �����, ������ �����ϴ� cart_id�� ����Ѵ�. 
            if(stockId != null) {
            	//��ٱ������̺� Insert
            	//���⼭ ���� cart_id=0�� �ǹ̾���. ������ �����
            	CartDTO cartDTO = new CartDTO(0, member_id, stockId, null, Integer.parseInt(map.get("quantity")));
            	cartService.cartInsert(cartDTO);
            }
            
            message = "���ID: "+ stockId + " , ��ٱ��Ϸ� �̵�";
            
        	return message; //��ٱ��� ������ ���� ���� X
		}else {
			//������ �ɼǰ��� null�� ���
			System.out.println("(�ɼ�null)Selected option: " + param);
			message = "�ɼ��� ������ �ּ���";
			return message;  
		}
	}
	
	
	//��ǰ ������ �ɼ� �� �����ϱ� -> �ֹ�������
	@GetMapping("/?")
	public void productOrderInsert(String productOption) {
		
		// ���õ� �ɼ� �� Ȯ�� 
        //System.out.println("Selected option: " + productOption);
	}

	/* �뿩 ��ǰ */
	
	
	/* ��ǰ ���� */
	@GetMapping("/productQnaInsert")
	@ResponseBody
	public String productQnaInsert( 
			@RequestParam("qnaTitle") String qnaTitle,
			@RequestParam("qnaWriterName") String qnaWriterName, //Ȯ�� �ʿ�!
			@RequestParam("qnaTestarea") String qnaTestarea,
			HttpSession session) 
	{
		Buyer_InqDTO buyer_InqDTO = new Buyer_InqDTO();
		
		//�α��� ȸ�� ����
		//MemberDTO member = session.getAttribute("");//ȸ�� ���� ���� Ȯ�� �� ���� �ʿ�
		String writer = null;
		//if(member == null) {
		//	writer = "�մ�";	
		//}else {
		//	writer = member.getMember_id(); //ȸ��id
		//}

		//���ε�
		//Ȯ�� �ʿ�! (�˾�â�� �̸� �Է��� ����.) 
		buyer_InqDTO.setMember_id(writer); //�ۼ��� == �α����� ȸ��?
		buyer_InqDTO.setBuyer_inq_content(qnaTestarea); //���ǳ���
		buyer_InqDTO.setBuyer_inq_title(qnaTitle);
		
		
		//Buyer_InA���̺� insert��
		int result = buyer_InqService.buyer_inqInsert(buyer_InqDTO);
		String message;
		if(result > 0) {
			message = "���ǰ� ��ϵǾ����ϴ�.";
		}else {
			message = "������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.";
		}
       
        return  "redirect:product_detail";		
	}
	
}
