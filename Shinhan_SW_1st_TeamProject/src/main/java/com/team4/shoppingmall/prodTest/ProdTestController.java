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
	
	/* 나중에 서비스,DTO 등 이름에서 Test빼고, 파일 내용 옮기기 */
	
	@Autowired
	Seller_Prod_StockTestService seller_Prod_StockTestService; //판매 상품 재고
	
	@Autowired
	ProdTestService prodTestService;  //상품
	
	@Autowired
	Prod_OptionTestService prod_OptionSerTestService;  //상품 옵션
	
	@Autowired
	Buyer_InqService buyer_InqService; //구매자 문의(구매자-판매자)
	
	@Autowired
	CartService cartService; //장바구니
	
	@Autowired
	ReviewsService reviewsService; //리뷰
	
	@Autowired
	RentService rentService; //대여
	
	@Autowired
	RentProdStockService rentProdStockService; //대여상품재고 
	
	@Autowired
	RentDetailService rentDetailService; //대여 상세
	
	/* 상품 목록 페이지 */
	@GetMapping("/productlistTest")
	public void productList() {
		
	}
		
	/* 상품 상세 페이지 */
	@GetMapping("/product_detail")
	public void productDetail(String prod_id, Model model) {
		
		//나중에 삭제하기
		prod_id = "나이키 반팔_1234-1234";
		
		/* 상품의 정보와 옵션 조회 */
		//옵션명과 값 전부 조회
		List<Object> prod_Options = prod_OptionSerTestService.selectAllOptionsByProdId(prod_id);
		model.addAttribute("prod_Options", prod_Options);
		//System.out.println("옵션 조회 : "+ prod_Options);
		 
		//판매 상품 옵션별 재고량 조회
		List<Seller_Prod_StockTestDTO> seller_prod_stockDTO = seller_Prod_StockTestService.selectSpsOptionByProdId(prod_id);
		model.addAttribute("seller_prod_stockDTO", seller_prod_stockDTO);

		//상품 정보 조회
		Map<String,Object> prod_detail_info = prodTestService.selectProdDetailInfoByProdId(prod_id);
		prod_detail_info.put("prod_id", prod_id); 
		model.addAttribute("prod_detail_info", prod_detail_info);
		//System.out.println("상품 상세 정보 조회 : "+ prod_detail_info);
		
		
		/* 상품 전체 리뷰 목록 */		
		List<Map<String,String>> productReviews = reviewsService.selectAllProductReviewByProdId(prod_id);
		model.addAttribute("productReviews", productReviews);
		//System.out.println("상품 전체 리뷰 목록 : " + productReviews);
		
		//리뷰 상품의 옵션 목록
		List<Prod_OptionDTO> prodOptions = prod_OptionSerTestService.productAllOptionsByProdId(prod_id);
		model.addAttribute("prodOptions", prodOptions);
		
		
		//System.out.println("리뷰 상품 옵션 목록 : " + prodOptions);
		
		/* 상품 전체 문의 목록 */
		List<Buyer_InqDTO> buyer_inqList = buyer_InqService.selectByProdId(prod_id);
		model.addAttribute("buyer_inqList", buyer_inqList);
		//System.out.println("상품 전체 문의 목록 : " + buyer_inqList);
		
	}
	
	/* 장바구니 */
	//상품 선택한 옵션 값 저장하기 -> 장바구니로 insert
	@GetMapping(value = "/productCartInsert.do", produces = "text/plain;charset=utf-8")
	public @ResponseBody String productCartInsert(HttpServletRequest request) throws UnsupportedEncodingException {

		HttpSession session = request.getSession();
    	
		//나중에 삭제하기
		String prod_id = "나이키 반팔_1234-1234";
		
    	//1.session에서 읽을 예정 
        String member_id = "testid";
		//String memberId = (String) session.getAttribute("member_id"); 나중에 주석 풀기
		
		//선택된 옵션 값 확인 
		String param = request.getQueryString();
        System.out.println("Selected option(param): " + param);
        
        String message = null;
        
        //param이 null이 아닌 경우
        if(param != null) {
        	System.out.println("(옵션존재)Selected option: " + param);
        	
			//(1)param으로 넘어온 선택 옵션들 저장
        	HashMap<String, String> map = new HashMap<>(); 
        	HashMap<String, String> optionMap = new HashMap<>(); //옵션Map-키(옵션명):값(옵션값)
        	String[] propertis = param.split("&");
        	for(String pro: propertis) {
        		String[] keyValue = pro.split("=");
        		
        		//상품ID 한글 꺠짐 해결 decode
        		map.put(keyValue[0], URLDecoder.decode(keyValue[1], "utf-8"));
        		
        		//discountPrice는 장바구니에 필요없음(param으로 넘어올 필요없음) - 수정 필요
        		//if(!keyValue[0].contains("quantity")&& !keyValue[0].contains("discountPrice") &&  !keyValue[0].contains("prod_id"))
        		if(!keyValue[0].contains("quantity")&& !keyValue[0].contains("productPrice") &&  !keyValue[0].contains("prod_id"))
        			optionMap.put(keyValue[0], keyValue[1]);
        	}
        	System.out.println("param으로 넘어온 전체 값 map : " + map);
        	
           
            //2.옵션에 해당하는 상품 재고id (판매상품재고)
            String sellStockId = cartService.searchStockId(optionMap, map.get("prod_id"));
            
            if(sellStockId != null) {
            	
                //1.select문 - 장바구니에 같은 상품 있는지 조회
                Map<String,String> map2 = new HashMap<>();
                map2.put("member_id", member_id);
                map2.put("sellstock_id", sellStockId);
                
                CartDTO cartBySellstockDTO = cartService.selectCartBySellstock(map2);
            	
                if(cartBySellstockDTO != null) {
                	//2.update문 - 같은 상품 존재 시 수량 업데이트 
                	
                	CartDTO cart = new CartDTO();
                	
                	//select로 조회한 결과에 있는 cart_id를 가져와서 저장
                	cart.setCart_id(cartBySellstockDTO.getCart_id()); 
                	//cart_amount 변수 저장 => 기존에 있는 cart_amount + 수량(quantity)	
                	cart.setCart_amount(Integer.parseInt(map.get("quantity")));
                	
                	//int updateResult = cartService.updateCartBySellstock(cart);
                	cartService.updateCartBySellstock(cart);
                	
                } else {
                	//3.insert문 - 장바구니 테이블에 새로 등록
                	//여기서 쓰인 cart_id = 0은 의미없음. 시퀀스 사용중
                	CartDTO cartDTO = new CartDTO(0, member_id, sellStockId, Integer.parseInt(map.get("quantity")));
                	cartService.cartInsert(cartDTO);
                }
         
            }
            message = "장바구니에 저장되었습니다.";
            System.out.println("재고ID: "+ sellStockId);
            
            return message; //장바구니 페이지 아직 연결 X
            
        }else {//(2)param이 null인 경우
				System.out.println("(옵션null)Selected option: " + param);
				message = "옵션을 선택해 주세요";
				return message;  
			  }
    }
	    
	/* 구매하기 */
	//상품 선택한 옵션 값 저장하기 -> 주문페이지
	//결제가 된 후에 주문상세테이블에 저장되는것? 
	//@GetMapping("/?")
	//public void productOrderInsert(String productOption) {
	
	//주문테이블 insert 1 : 주문상세테이블 insert  n
	
	
	//
	//}

	/* 상품 대여 가능한지 체크 => 대여하기 진행 */
	@PostMapping("/isProductRentable")
	@ResponseBody
	public int isProductRentable(HttpServletRequest request, Model model) {
		
		//회원 세션에서 읽기
		String member_id = "testid";
    	 
		String param = request.getQueryString();
		String prod_id="";
		
		String rent_prod_quantity = "";
		String rent_start_date = "";
		String rent_end_date = "";
		String total_amount = "";
		
		//1.상품이 대여재고에 존재하는지 찾기
		//{color=4, size=1, rent_prod_quantity=1, rent_start_date=2024-06-27, rent_end_date=2024-07-04, prod_id=%EB%82%98%EC%9D%B4%ED%82%A4%20%EB%B0%98%ED%8C%94_1234-1234, total_amount=30000}
		
    	HashMap<String, String> map = new HashMap<>(); 
    	HashMap<String, String> optionMap = new HashMap<>(); //옵션Map
    	String[] propertis = param.split("&");
    	
    	List<Integer> optList = new ArrayList<>();   
    	
    	int index=0;
    	
    	for(String pro: propertis) {
    		String[] keyValue = pro.split("=");
    		
    		map.put(keyValue[0], keyValue[1]);
    		
    		//상품id
    		if(keyValue[0].equals("prod_id")) prod_id = keyValue[1];
    		
    		//대여 수
    		if(keyValue[0].equals("rent_prod_quantity")) rent_prod_quantity = keyValue[1];  //수량
    		//대여 시작일
    		if(keyValue[0].equals(("rent_start_date"))) rent_start_date = keyValue[1]; 
    		//대여 마감일
    		if(keyValue[0].equals(("rent_end_date"))) rent_end_date = keyValue[1];
    		//대여가
    		if(keyValue[0].equals(("total_amount"))) total_amount = keyValue[1]; //대여가
    		
    		//옵션들
    		if(!keyValue[0].contains("prod_id")&& 
			  !keyValue[0].contains("rent_prod_quantity")&& 
			  !keyValue[0].contains("rent_start_date") &&  
			  !keyValue[0].contains("rent_end_date") && 
			  !keyValue[0].contains("total_amount")) {
    			
    			optList.add( Integer.parseInt(keyValue[1])); 
    			index++; //예) 1,4,,,
    		}
    	}
    	
	    // Option list 정렬
    	optList.sort((a,b)->a-b);
    	List<String> result = optList.stream().map(i->i.toString()).collect(Collectors.toList());
    	String optionString = String.join("," , result);
    	for(int i=1; i<=5-index;i++) { optionString += ","; }
		 
        //옵션 값이 콤마로 구분된 문자열을 생성
        map.put("prod_id", prod_id);
        map.put("optionString", optionString);

        System.out.println(prod_id + ":" + optionString);
        
		try {
			prod_id = URLDecoder.decode(map.get("prod_id"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(prod_id +":" + optionString); 
		
		//해당 옵션의 대여 상품 재고 조회
		Map<String, String> rentProductStockCheck = rentProdStockService.selectRentStockByProdId(prod_id, optionString);
        model.addAttribute("rentProductStockCheck",rentProductStockCheck);
        //rentProductStockCheck => {OPT_ID2=4, OPT_ID1=1, R_STOCK_ID=나이키 반팔_1234-1234_RENT_1, STOCK=50}
        
        
        /* 대여 */
        // 2.대여재고가 존재하는 경우 대여하기 가능
         if (rentProductStockCheck != null) { 
        	
        	 RentDTO rent = new RentDTO();
        	
        	 rent.setRent_start_date( DateUtil.getSQLDate(rent_start_date)  );
        	 rent.setRent_end_date(DateUtil.getSQLDate(rent_end_date));
             rent.setMember_id(member_id);
             rent.setTotal_rent_price(Integer.parseInt(total_amount));
            
        	 int rentProdInsert = rentService.rentInsert(rent);
        	 
        	 
        	 /* 대여 상세 -------------------------||진행중||---------------------------------------- 
        	 // 삽입된 대여ID를 찾는 select문 RENT테이블에 삽입되는 RENTAL_CODE 찾기
             int rentalCode = rentService.selectInsertRentalCode();
    	 	
        	 //3.옵션에 해당하는 상품의 대여재고ID(r_stock_id) 구하고 대여상세 생성
             if (rentProductStockCheck != null && rentProductStockCheck.containsKey("R_STOCK_ID")) {
                 String rentStockId = rentProductStockCheck.get("R_STOCK_ID");
                 System.out.println("R_STOCK_ID: " + rentStockId);
                 
                 //대여ID는 어떻게 찾지?
                 
                 RentDetailDTO rentDetailDTO = new RentDetailDTO();
                 
                 //rentDetailDTO.setRentdetail_id(); 	//시퀀ㅅ
                 rentDetailDTO.setRent_product_price(Integer.parseInt(total_amount)); //일단 대여가격 넣음
                 rentDetailDTO.setR_stock_id(rentStockId);  //대여재고id
                 rentDetailDTO.setRent_num(Integer.parseInt(rent_prod_quantity)); //대여 주문 수량
                 
                 rentDetailDTO.setRental_code(rentalCode); //대여id (시퀀스)
                 
                 
                 //대여상세 생성 Insert (대여id, 대여재고id, 주문수량, 상품가격(대여가), 주문상세번호(시퀀스) )
                 //맵핑된 sql문 못찾음 이슈
                 int rentDetailInsert = rentDetailService.rentDetailInsert(rentDetailDTO);
                 
                 System.out.println("rentDetailInsert: " + rentDetailInsert);
                 
                 
             } else {
                 System.out.println("R_STOCK_ID 값이 존재하지 않습니다.");
             }
             //------------------------------------------------------------------------
        	 */
        	
        	 
        	 
             return 1; // 대여 성공
             
         } else{
        	 
        	 return 0; //대여실패 (재고없음)
         }
         
         
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* 상품 문의하기 (구매자=>판매자) */
	@PostMapping(value = "/productQnaInsert.do", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String productQnaInsert( 
			@RequestParam String qnaTitle,	//문의 제목
			@RequestParam String qnaTestarea, //문의 내용
			HttpSession session //session에서 로그인 정보 읽기
	){
		Buyer_InqDTO buyer_InqDTO = new Buyer_InqDTO();
		
		Map<String,String> buyer_inq_map = new HashMap<String,String>();
		
		System.out.println("문의 제목: "+qnaTitle);
		System.out.println("문의 내용: "+qnaTestarea);
		
		
		/* 업로드 */
        //문의 제목 
        //buyer_InqDTO.setBuyer_inq_title(qnaTitle); 
        buyer_inq_map.put("buyer_inq_title", qnaTitle); 

        //문의내용 
		//buyer_InqDTO.setBuyer_inq_content(qnaTestarea); 
		 buyer_inq_map.put("buyer_inq_content", qnaTestarea); 
		 
		//회원ID 테스트 - 실제로는 세션에서 가져와야 함
		 String member_id = "testid";
		//String memberId = (String) session.getAttribute("member_id"); 
		 
		 buyer_inq_map.put("member_id", member_id); 
		 
		//상품ID 테스트 - 실제 prod_id 값을 설정헤야 함
		 String prod_id = "나이키 반팔_1234-1234";
		 buyer_inq_map.put("prod_id", prod_id); 
		
		 
		//insert문 - Buyer_InA테이블 
		int result = buyer_InqService.buyer_inqInsert(buyer_inq_map);
		
		String message;
		
		if(result > 0) {
			message = "문의가 등록되었습니다.";
			
			return  message;		
			
		}else {
			message = "오류가 발생했습니다. 다시 시도해주세요.";
			return  message;
		}
		
	}
	
}
