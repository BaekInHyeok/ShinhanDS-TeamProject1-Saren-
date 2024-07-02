package com.team4.shoppingmall.prodTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.shoppingmall.buyer_inq.Buyer_InqDTO;
import com.team4.shoppingmall.buyer_inq.Buyer_InqService;
import com.team4.shoppingmall.cart.CartDTO;
import com.team4.shoppingmall.cart.CartService;
import com.team4.shoppingmall.prod_option.Prod_OptionDTO;
import com.team4.shoppingmall.prod_optionTest.Prod_OptionTestService;
import com.team4.shoppingmall.rent.RentDTO;
import com.team4.shoppingmall.rent.RentService;
import com.team4.shoppingmall.rent_detail.RentDetailDTO;
import com.team4.shoppingmall.rent_detail.RentDetailService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
import com.team4.shoppingmall.reviews.ReviewsService;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestDTO;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestService;
import com.team4.shoppingmall.util.DateUtil;

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
	
	/* ��ǰ ��� ������ */
	@GetMapping("/productlistTest")
	public void productList() {
		
	}
		
	/* ��ǰ �� ������ */
	@GetMapping("/product_detail")
	public void productDetail(String prod_id, Model model) {
		
		//���߿� �����ϱ�
		prod_id = "����Ű ����_1234-1234";
		
		/* ��ǰ�� ������ �ɼ� ��ȸ */
		//�ɼǸ�� �� ���� ��ȸ
		List<Object> prod_Options = prod_OptionSerTestService.selectAllOptionsByProdId(prod_id);
		model.addAttribute("prod_Options", prod_Options);
		//System.out.println("�ɼ� ��ȸ : "+ prod_Options);
		 
		//�Ǹ� ��ǰ �ɼǺ� ��� ��ȸ
		List<Seller_Prod_StockTestDTO> seller_prod_stockDTO = seller_Prod_StockTestService.selectSpsOptionByProdId(prod_id);
		model.addAttribute("seller_prod_stockDTO", seller_prod_stockDTO);

		//��ǰ ���� ��ȸ
		Map<String,Object> prod_detail_info = prodTestService.selectProdDetailInfoByProdId(prod_id);
		prod_detail_info.put("prod_id", prod_id); 
		model.addAttribute("prod_detail_info", prod_detail_info);
		//System.out.println("��ǰ �� ���� ��ȸ : "+ prod_detail_info);
		
		
		/* ��ǰ ��ü ���� ��� */		
		List<Map<String,String>> productReviews = reviewsService.selectAllProductReviewByProdId(prod_id);
		model.addAttribute("productReviews", productReviews);
		//System.out.println("��ǰ ��ü ���� ��� : " + productReviews);
		
		//���� ��ǰ�� �ɼ� ���
		List<Prod_OptionDTO> prodOptions = prod_OptionSerTestService.productAllOptionsByProdId(prod_id);
		model.addAttribute("prodOptions", prodOptions);
		
		
		//System.out.println("���� ��ǰ �ɼ� ��� : " + prodOptions);
		
		/* ��ǰ ��ü ���� ��� */
		List<Buyer_InqDTO> buyer_inqList = buyer_InqService.selectByProdId(prod_id);
		model.addAttribute("buyer_inqList", buyer_inqList);
		//System.out.println("��ǰ ��ü ���� ��� : " + buyer_inqList);
		
	}
	
	/* ��ٱ��� */
	//��ǰ ������ �ɼ� �� �����ϱ� -> ��ٱ��Ϸ� insert
	@GetMapping(value = "/productCartInsert.do", produces = "text/plain;charset=utf-8")
	public @ResponseBody String productCartInsert(HttpServletRequest request) throws UnsupportedEncodingException {

		HttpSession session = request.getSession();
    	
		//���߿� �����ϱ�
		String prod_id = "����Ű ����_1234-1234";
		
    	//1.session���� ���� ���� 
        String member_id = "testid";
		//String memberId = (String) session.getAttribute("member_id"); ���߿� �ּ� Ǯ��
		
		//���õ� �ɼ� �� Ȯ�� 
		String param = request.getQueryString();
        System.out.println("Selected option(param): " + param);
        
        String message = null;
        
        //param�� null�� �ƴ� ���
        if(param != null) {
        	System.out.println("(�ɼ�����)Selected option: " + param);
        	
			//(1)param���� �Ѿ�� ���� �ɼǵ� ����
        	HashMap<String, String> map = new HashMap<>(); 
        	HashMap<String, String> optionMap = new HashMap<>(); //�ɼ�Map-Ű(�ɼǸ�):��(�ɼǰ�)
        	String[] propertis = param.split("&");
        	for(String pro: propertis) {
        		String[] keyValue = pro.split("=");
        		
        		//��ǰID �ѱ� ���� �ذ� decode
        		map.put(keyValue[0], URLDecoder.decode(keyValue[1], "utf-8"));
        		
        		//discountPrice�� ��ٱ��Ͽ� �ʿ����(param���� �Ѿ�� �ʿ����) - ���� �ʿ�
        		//if(!keyValue[0].contains("quantity")&& !keyValue[0].contains("discountPrice") &&  !keyValue[0].contains("prod_id"))
        		if(!keyValue[0].contains("quantity")&& !keyValue[0].contains("productPrice") &&  !keyValue[0].contains("prod_id"))
        			optionMap.put(keyValue[0], keyValue[1]);
        	}
        	System.out.println("param���� �Ѿ�� ��ü �� map : " + map);
        	
           
            //2.�ɼǿ� �ش��ϴ� ��ǰ ���id (�ǸŻ�ǰ���)
            String sellStockId = cartService.searchStockId(optionMap, map.get("prod_id"));
            
            if(sellStockId != null) {
            	
                //1.select�� - ��ٱ��Ͽ� ���� ��ǰ �ִ��� ��ȸ
                Map<String,String> map2 = new HashMap<>();
                map2.put("member_id", member_id);
                map2.put("sellstock_id", sellStockId);
                
                CartDTO cartBySellstockDTO = cartService.selectCartBySellstock(map2);
            	
                if(cartBySellstockDTO != null) {
                	//2.update�� - ���� ��ǰ ���� �� ���� ������Ʈ 
                	
                	CartDTO cart = new CartDTO();
                	
                	//select�� ��ȸ�� ����� �ִ� cart_id�� �����ͼ� ����
                	cart.setCart_id(cartBySellstockDTO.getCart_id()); 
                	//cart_amount ���� ���� => ������ �ִ� cart_amount + ����(quantity)	
                	cart.setCart_amount(Integer.parseInt(map.get("quantity")));
                	
                	//int updateResult = cartService.updateCartBySellstock(cart);
                	cartService.updateCartBySellstock(cart);
                	
                } else {
                	//3.insert�� - ��ٱ��� ���̺� ���� ���
                	//���⼭ ���� cart_id = 0�� �ǹ̾���. ������ �����
                	CartDTO cartDTO = new CartDTO(0, member_id, sellStockId, Integer.parseInt(map.get("quantity")));
                	cartService.cartInsert(cartDTO);
                }
         
            }
            message = "��ٱ��Ͽ� ����Ǿ����ϴ�.";
            System.out.println("���ID: "+ sellStockId);
            
            return message; //��ٱ��� ������ ���� ���� X
            
        }else {//(2)param�� null�� ���
				System.out.println("(�ɼ�null)Selected option: " + param);
				message = "�ɼ��� ������ �ּ���";
				return message;  
			  }
    }
	    
	/* �����ϱ� */
	//��ǰ ������ �ɼ� �� �����ϱ� -> �ֹ�������
	//������ �� �Ŀ� �ֹ������̺� ����Ǵ°�? 
	//@GetMapping("/?")
	//public void productOrderInsert(String productOption) {
	
	//�ֹ����̺� insert 1 : �ֹ������̺� insert  n
	
	
	//
	//}

	/* ��ǰ �뿩 �������� üũ => �뿩�ϱ� ���� */
	@PostMapping("/isProductRentable")
	@ResponseBody
	public int isProductRentable(HttpServletRequest request, Model model) {
		
		//ȸ�� ���ǿ��� �б�
		String member_id = "testid";
    	 
		String param = request.getQueryString();
		String prod_id="";
		
		String rent_prod_quantity = "";
		String rent_start_date = "";
		String rent_end_date = "";
		String total_amount = "";
		
		//1.��ǰ�� �뿩��� �����ϴ��� ã��
		//{color=4, size=1, rent_prod_quantity=1, rent_start_date=2024-06-27, rent_end_date=2024-07-04, prod_id=%EB%82%98%EC%9D%B4%ED%82%A4%20%EB%B0%98%ED%8C%94_1234-1234, total_amount=30000}
		
    	HashMap<String, String> map = new HashMap<>(); 
    	HashMap<String, String> optionMap = new HashMap<>(); //�ɼ�Map
    	String[] propertis = param.split("&");
    	
    	List<Integer> optList = new ArrayList<>();   
    	
    	int index=0;
    	
    	for(String pro: propertis) {
    		String[] keyValue = pro.split("=");
    		
    		map.put(keyValue[0], keyValue[1]);
    		
    		//��ǰid
    		if(keyValue[0].equals("prod_id")) prod_id = keyValue[1];
    		
    		//�뿩 ��
    		if(keyValue[0].equals("rent_prod_quantity")) rent_prod_quantity = keyValue[1];  //����
    		//�뿩 ������
    		if(keyValue[0].equals(("rent_start_date"))) rent_start_date = keyValue[1]; 
    		//�뿩 ������
    		if(keyValue[0].equals(("rent_end_date"))) rent_end_date = keyValue[1];
    		//�뿩��
    		if(keyValue[0].equals(("total_amount"))) total_amount = keyValue[1]; //�뿩��
    		
    		//�ɼǵ�
    		if(!keyValue[0].contains("prod_id")&& 
			  !keyValue[0].contains("rent_prod_quantity")&& 
			  !keyValue[0].contains("rent_start_date") &&  
			  !keyValue[0].contains("rent_end_date") && 
			  !keyValue[0].contains("total_amount")) {
    			
    			optList.add( Integer.parseInt(keyValue[1])); 
    			index++; //��) 1,4,,,
    		}
    	}
    	
	    // Option list ����
    	optList.sort((a,b)->a-b);
    	List<String> result = optList.stream().map(i->i.toString()).collect(Collectors.toList());
    	String optionString = String.join("," , result);
    	for(int i=1; i<=5-index;i++) { optionString += ","; }
		 
        //�ɼ� ���� �޸��� ���е� ���ڿ��� ����
        map.put("prod_id", prod_id);
        map.put("optionString", optionString);

        System.out.println(prod_id + ":" + optionString);
        
		try {
			prod_id = URLDecoder.decode(map.get("prod_id"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(prod_id +":" + optionString); 
		
		//�ش� �ɼ��� �뿩 ��ǰ ��� ��ȸ
		Map<String, String> rentProductStockCheck = rentProdStockService.selectRentStockByProdId(prod_id, optionString);
        model.addAttribute("rentProductStockCheck",rentProductStockCheck);
        //rentProductStockCheck => {OPT_ID2=4, OPT_ID1=1, R_STOCK_ID=����Ű ����_1234-1234_RENT_1, STOCK=50}
        
        
        /* �뿩 */
        // 2.�뿩��� �����ϴ� ��� �뿩�ϱ� ����
         if (rentProductStockCheck != null) { 
        	
        	 RentDTO rent = new RentDTO();
        	
        	 rent.setRent_start_date( DateUtil.getSQLDate(rent_start_date)  );
        	 rent.setRent_end_date(DateUtil.getSQLDate(rent_end_date));
             rent.setMember_id(member_id);
             rent.setTotal_rent_price(Integer.parseInt(total_amount));
            
        	 int rentProdInsert = rentService.rentInsert(rent);
        	 
        	 
        	 /* �뿩 �� -------------------------||������||---------------------------------------- 
        	 // ���Ե� �뿩ID�� ã�� select�� RENT���̺� ���ԵǴ� RENTAL_CODE ã��
             int rentalCode = rentService.selectInsertRentalCode();
    	 	
        	 //3.�ɼǿ� �ش��ϴ� ��ǰ�� �뿩���ID(r_stock_id) ���ϰ� �뿩�� ����
             if (rentProductStockCheck != null && rentProductStockCheck.containsKey("R_STOCK_ID")) {
                 String rentStockId = rentProductStockCheck.get("R_STOCK_ID");
                 System.out.println("R_STOCK_ID: " + rentStockId);
                 
                 //�뿩ID�� ��� ã��?
                 
                 RentDetailDTO rentDetailDTO = new RentDetailDTO();
                 
                 //rentDetailDTO.setRentdetail_id(); 	//������
                 rentDetailDTO.setRent_product_price(Integer.parseInt(total_amount)); //�ϴ� �뿩���� ����
                 rentDetailDTO.setR_stock_id(rentStockId);  //�뿩���id
                 rentDetailDTO.setRent_num(Integer.parseInt(rent_prod_quantity)); //�뿩 �ֹ� ����
                 
                 rentDetailDTO.setRental_code(rentalCode); //�뿩id (������)
                 
                 
                 //�뿩�� ���� Insert (�뿩id, �뿩���id, �ֹ�����, ��ǰ����(�뿩��), �ֹ��󼼹�ȣ(������) )
                 //���ε� sql�� ��ã�� �̽�
                 int rentDetailInsert = rentDetailService.rentDetailInsert(rentDetailDTO);
                 
                 System.out.println("rentDetailInsert: " + rentDetailInsert);
                 
                 
             } else {
                 System.out.println("R_STOCK_ID ���� �������� �ʽ��ϴ�.");
             }
             //------------------------------------------------------------------------
        	 */
        	
        	 
        	 
             return 1; // �뿩 ����
             
         } else{
        	 
        	 return 0; //�뿩���� (������)
         }
         
         
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* ��ǰ �����ϱ� (������=>�Ǹ���) */
	@PostMapping(value = "/productQnaInsert.do", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String productQnaInsert( 
			@RequestParam String qnaTitle,	//���� ����
			@RequestParam String qnaTestarea, //���� ����
			HttpSession session //session���� �α��� ���� �б�
	){
		Buyer_InqDTO buyer_InqDTO = new Buyer_InqDTO();
		
		Map<String,String> buyer_inq_map = new HashMap<String,String>();
		
		System.out.println("���� ����: "+qnaTitle);
		System.out.println("���� ����: "+qnaTestarea);
		
		
		/* ���ε� */
        //���� ���� 
        //buyer_InqDTO.setBuyer_inq_title(qnaTitle); 
        buyer_inq_map.put("buyer_inq_title", qnaTitle); 

        //���ǳ��� 
		//buyer_InqDTO.setBuyer_inq_content(qnaTestarea); 
		 buyer_inq_map.put("buyer_inq_content", qnaTestarea); 
		 
		//ȸ��ID �׽�Ʈ - �����δ� ���ǿ��� �����;� ��
		 String member_id = "testid";
		//String memberId = (String) session.getAttribute("member_id"); 
		 
		 buyer_inq_map.put("member_id", member_id); 
		 
		//��ǰID �׽�Ʈ - ���� prod_id ���� ������� ��
		 String prod_id = "����Ű ����_1234-1234";
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
