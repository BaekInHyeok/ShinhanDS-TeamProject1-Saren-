package com.team4.shoppingmall.prodTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.shoppingmall.buyer_inq.Buyer_InqDTO;
import com.team4.shoppingmall.buyer_inq.Buyer_InqService;
import com.team4.shoppingmall.cart.CartDTO;
import com.team4.shoppingmall.cart.CartService;
import com.team4.shoppingmall.order_detail.Order_DetailService;
import com.team4.shoppingmall.order_prod.OrderProdService;
import com.team4.shoppingmall.prod.ProductNewVO;
import com.team4.shoppingmall.prod_option.Prod_OptionDTO;
import com.team4.shoppingmall.prod_optionTest.Prod_OptionTestService;
import com.team4.shoppingmall.rent.RentService;
import com.team4.shoppingmall.rent_detail.RentDetailService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockListDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
import com.team4.shoppingmall.reviews.ReviewsService;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestDTO;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestService;

@Controller
@RequestMapping("/prod")
public class ProdTestController {
	
	/* ���߿� ����,DTO �� �̸����� Test����, ���� ���� �ű�� */
	
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
	
	@Autowired
	RentService rentService; //�뿩
	
	@Autowired
	RentProdStockService rentProdStockService; //�뿩��ǰ��� 
	
	@Autowired
	RentDetailService rentDetailService; //�뿩 ��

	@Autowired
	OrderProdService orderProdService; //�ֹ�
	
	@Autowired
	Order_DetailService order_DetailService; //�ֹ��� 
	
	
	
	/* ��ǰ ��� ������ */
	@GetMapping("/productlistTest")
	public void productList() {
		
	}
		
	/* ��ǰ �� ������ */
	@GetMapping("/product_detail")
	public void productDetail(String prod_id, Model model) throws JsonProcessingException {
		
		//���߿� �����ϱ�
		prod_id = "����Ű ����_1234-1234";
		//prod_id = "��ũ_1234-1234"; //�뿩��� ���� ��ǰ �׽�Ʈ
		 
		//��ǰ�� ������ �ɼ� ��ȸ
		//�ɼǸ�� �� ���� ��ȸ
		//List<Object> prod_Options = prod_OptionSerTestService.selectAllOptionsByProdId(prod_id);
		ArrayList<Object> prod_Options = (ArrayList<Object>) prod_OptionSerTestService.selectAllOptionsByProdId(prod_id);
		model.addAttribute("prod_Options", prod_Options);
		//System.out.println("�ɼ� ��ȸ : "+ prod_Options);
		 
		//�Ǹ� ��ǰ �ɼǺ� ��� ��ȸ
		List<Seller_Prod_StockTestDTO> seller_prod_stockDTO = seller_Prod_StockTestService.selectSpsOptionByProdId(prod_id);
		model.addAttribute("seller_prod_stockDTO", seller_prod_stockDTO);
		ObjectMapper mapper = new ObjectMapper();
		String jsonText = mapper.writeValueAsString( seller_prod_stockDTO );
		model.addAttribute( "stockList", jsonText );

		//��ǰ ���� ��ȸ
		Map<String,Object> prod_detail_info = prodTestService.selectProdDetailInfoByProdId(prod_id);
		prod_detail_info.put("prod_id", prod_id); 
		model.addAttribute("prod_detail_info", prod_detail_info);
		
		//�뿩 ��ǰ �ɼǺ� ��� ��ȸ
		List<RentProdStockDTO> rentStockList = rentProdStockService.selectRpsOptionByProdId(prod_id);
		model.addAttribute("rentStockList", rentStockList);
		ObjectMapper rentMapper = new ObjectMapper();
		String jsonTextRent = rentMapper.writeValueAsString( rentStockList );
		model.addAttribute( "rentStockList", jsonTextRent );
		
		/* ��ǰ ��ü ���� ��� */		
		List<Map<String,String>> productReviews = reviewsService.selectAllProductReviewByProdId(prod_id);
		model.addAttribute("productReviews", productReviews);
		
		//���� ��Ͽ� �ҷ��� ��ǰ�� �ɼ� ���
		List<Prod_OptionDTO> prodOptions = prod_OptionSerTestService.productAllOptionsByProdId(prod_id);
		model.addAttribute("prodOptions", prodOptions);
		
		/* ��ǰ ��ü ���� ��� */
		List<Buyer_InqDTO> buyer_inqList = buyer_InqService.selectByProdId(prod_id);
		model.addAttribute("buyer_inqList", buyer_inqList);
		
		//��ǰ�� �뿩���ID ��ȸ - �뿩 ��ư Ȱ��ȭ
		List<RentProdStockDTO> rentProductStockCheck = rentProdStockService.selectRentStockByProdId2(prod_id);
		model.addAttribute("rentProductStockCheck", rentProductStockCheck);
		
	}
	
	
	//��ٱ��� - ��ǰ(�Ǹ�)
	@PostMapping("/productCartInsert.do")
	@ResponseBody
	public int productCartInsert(String prod_id,
								 HttpServletRequest request, 
								 HttpSession session, 
								 Model model,
								 @RequestBody ProductNewVO prodVO) 
	{
		//session���� ���� ���� 
        String member_id = "testid";
        prod_id = "";
        
        if(prodVO.getS_stock_id() == null || prodVO == null) {
    		return 0; //��ٱ��� ���� ����
		}
        
		//��ٱ��� ���� (ȸ��id, �Ǹ����id, ����)
		int sellProdCartInsert = cartService.cartInsert(prodVO, member_id);
		
		return sellProdCartInsert;
		
	}
	//��ٱ��� - ��ǰ(�뿩)
	@PostMapping("/rentProductCartInsert.do")
	@ResponseBody
	public int rentProductCartInsert(String prod_id,
								 HttpServletRequest request, 
								 HttpSession session, 
								 Model model,
								 @RequestBody ProductNewVO prodVO) 
	{
		//session���� ���� ���� 
        String member_id = "testid";
        prod_id = "";
        
        if(prodVO == null || prodVO.getR_stock_id() == null) {
    		return 0; //��ٱ��� ���� ����
		}
        
		//��ٱ��� ���� (ȸ��id, �Ǹ����id, ����)
		int cartRentProductInsert = cartService.cartRentProductInsert(prodVO, member_id);
		
		return cartRentProductInsert;
		
	}
	    
	/* �����ϱ� */ 
	//ProductNewVO ����
	@PostMapping("/productOrderInsert.do")
	@ResponseBody
	public int productOrderInsert(HttpServletRequest request, 
								  Model model,
								  @RequestBody ProductNewVO prodVO,
								  HttpSession session) throws UnsupportedEncodingException
	{
    	//session���� ���� ���� 
        String member_id = "testid";
        //��ǰID
        String prod_id = "";
		
		//��� üũ (����Ʈ���� üũ �ߴµ� �鵵 ���߿� �߰�)
    	if(prodVO.getS_stock_id() == null || prodVO == null) {
    		return 0; //�ֹ�����
		}
    	
		int productPrice = Integer.parseInt(prodVO.getProductPrice());
		int total_price = productPrice * prodVO.getOrder_num();  //�ֹ� �ѱݾ�
		
		//1.�ֹ�,�ֹ��� ���� (���񽺿��� ���� ó��)
		int orderProdInsert =  orderProdService.orderprodInsert(prodVO, total_price, member_id);
		
		return orderProdInsert; 
    }
	
	/* �뿩�ϱ� ������ */
	@PostMapping("/rentProductOrderInsert.do")
	@ResponseBody
	public int rentProductOrderInsert(HttpServletRequest request, 
								  Model model,
								  @RequestBody ProductNewVO prodVO,
								  HttpSession session) 
	{
		//session���� ���� ���� 
		String member_id = "testid";	 
		//��ǰID
        String prod_id = "";
        
        if(prodVO.getR_stock_id() == null || prodVO == null) {
        	return 0; //�뿩 ����
        }
        
        //1.�뿩, �뿩�� ���� (���񽺿��� ���� ó��)
        int rentProdInsert = rentService.rentInsert2(prodVO, member_id);
        
        return rentProdInsert; 
        
	}

	/* ��ǰ �����ϱ� (������=>�Ǹ���) */
	@PostMapping(value = "/productQnaInsert.do", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String productQnaInsert( 
			@RequestParam("prod_id") String prod_id,
			@RequestParam String qnaTitle,	//���� ����
			@RequestParam String qnaTestarea, //���� ����
			HttpSession session //session���� �α��� ���� �б�
	){
		Buyer_InqDTO buyer_InqDTO = new Buyer_InqDTO();
		
		Map<String,String> buyer_inq_map = new HashMap<String,String>();
		
		System.out.println("���� ����: "+qnaTitle);
		System.out.println("���� ����: "+qnaTestarea);
		
		//prod_id = "";
		//String prod_id = "";
		prod_id = "����Ű ����_1234-1234";
		
		/* ���ε� */
        //���� ���� 
        buyer_inq_map.put("buyer_inq_title", qnaTitle); 

        //���ǳ��� 
		 buyer_inq_map.put("buyer_inq_content", qnaTestarea); 
		 
		//ȸ��ID �׽�Ʈ - �����δ� ���ǿ��� �����;� ��
		 String member_id = "testid";
		 
		 buyer_inq_map.put("member_id", member_id); 
		 
		//��ǰID �׽�Ʈ - ���� prod_id ���� ������� ��
		 buyer_inq_map.put("prod_id", prod_id); 
		
		 
		//insert�� - Buyer_InA���̺� 
		int result = buyer_InqService.buyer_inqInsert(buyer_inq_map);
		
		String message;
		
		if(result > 0) {
			message = "���ǰ� ��ϵǾ����ϴ�.";
			
			return  message;		
			
		}else {
			message = "������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.";
			return  message;
		}
		
	}
	
}
