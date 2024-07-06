package com.team4.shoppingmall.seller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.team4.shoppingmall.admin_inq.Admin_InqDTO;
import com.team4.shoppingmall.admin_inq.Admin_InqService;
import com.team4.shoppingmall.buyer_inq.Buyer_InqDAOInterface;
import com.team4.shoppingmall.buyer_inq.Buyer_InqDTO;
import com.team4.shoppingmall.buyer_inq.Buyer_InqService;
import com.team4.shoppingmall.category.CategoryDTO;
import com.team4.shoppingmall.category.CategoryService;
import com.team4.shoppingmall.member.MemberDTO;
import com.team4.shoppingmall.member.MemberService;
import com.team4.shoppingmall.order_detail.OrderUpdateReqDTO;
import com.team4.shoppingmall.order_detail.Order_DetailDTO;
import com.team4.shoppingmall.order_detail.Order_DetailService;
import com.team4.shoppingmall.prod.ProdDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.prod_image.Prod_ImageDTO;
import com.team4.shoppingmall.prod_image.Prod_ImageService;
import com.team4.shoppingmall.prod_option.Prod_OptionDTO;
import com.team4.shoppingmall.prod_option.Prod_OptionService;
import com.team4.shoppingmall.rent.RentDTO;
import com.team4.shoppingmall.rent_detail.RentDetailDTO;
import com.team4.shoppingmall.rent_detail.RentDetailService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
import com.team4.shoppingmall.seller_prod_stock.StockUpdateDTO;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockDTO;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Controller
@RequestMapping("/seller")
public class SellerPageController {

	@Autowired
	Buyer_InqService buyer_inqService;

	@Autowired
	Admin_InqService admin_inqService;

	@Autowired
	MemberService memberService;

	@Autowired
	ProdService prodService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	Prod_ImageService imageService;

	@Autowired
	Seller_Prod_StockService seller_Prod_StockService;

	@Autowired
	RentProdStockService rentProdStockService;

	@Autowired
	Order_DetailService order_DetailService;

	@Autowired
	RentDetailService rentDetailService;

	@Autowired
	Prod_OptionService prod_OptionService;

	//String member_id = "573-50-00882";// 임시로 사용할 판매자ID(사업자등록번호)

	// 상품 이미지 파일 업로드 디렉토리
	// 1.메인 이미지 파일
	@Value("${file.main-img-upload-dir}")
	private String mainIMG_uploadDir;

	// 2.설명 이미지 파일
	@Value("${file.desc-img-upload-dir}")
	private String descIMG_uploadDir;

	// 메인페이지
	@GetMapping("/MainPage.do")
	public String mainpage(Model model, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		 
		model.addAttribute("sellerInfo", memberService.selectById(sellerID));
		
		// 여기서 SQL문을 사용해 model로 데이터를 끌어옴
		// 여기에는 판매자가 판매하는 상품들의 판매량 데이터를 끌어오고, 데이터를 그래프화하여 표현
		// model.addAttribute(result, flashMap);
		return "seller/sellerMain";
	}

	// 판매&대여 상품 페이지 보여주기
	@GetMapping("/PrdList.do")
	public String prdList(Model model, Model model1, Model model2, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		
		model.addAttribute("sellerInfo", memberService.selectById(sellerID));
		
		// 판매 상품 리스트
		model1.addAttribute("stockSList", seller_Prod_StockService.findSellStockList(sellerID));

		System.out.println("�뙋留ㅼ긽�뭹 由ъ뒪�듃 遺덈윭�샂");
		// ���뿬 �긽�뭹 由ъ뒪�듃
		model2.addAttribute("stockRList", rentProdStockService.findRentStockList(sellerID));

		System.out.println("���뿬�긽�뭹 由ъ뒪�듃 遺덈윭�샂");

		return "/seller/sellerPrdList";
	}

	// 판매&배송 페이지 보여주기
	@GetMapping("/DeliveryList.do")
	public String deliveryList(Model model, Model model1, Model model2, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		
		model.addAttribute("sellerInfo", memberService.selectById(sellerID));

		// 판매&배송 리스트
		// 1.판매 상품 대상 주문상세리스트
		System.out.println(order_DetailService.selectBySellerID(sellerID));
		System.out.println(rentDetailService.selectBySellerID(sellerID));

		model1.addAttribute("orderDetailList", order_DetailService.selectBySellerID(sellerID));
		model2.addAttribute("rentDetailList", rentDetailService.selectBySellerID(sellerID));
		return "/seller/sellerDelivery";
	}

	// 판매 주문 항목 일괄처리
	@PostMapping("/updateOrderStatus")
	@ResponseBody
	public String updateOrderStauts(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> orderDetailIds = request.getOrderDetailIds();
		System.out.println(orderDetailIds);

		String status = request.getStatus();

		for (Integer orderDetail : orderDetailIds) {

			Order_DetailDTO order_DetailDTO = new Order_DetailDTO();

			order_DetailDTO.setOrderdetail_id(orderDetail);
			order_DetailDTO.setOrder_state(status);

			int statusUpdateResult = order_DetailService.orderDetailStatusUpdate(order_DetailDTO);
		}
		return "Update Success";
	}

	// 판매 주문 항목 일괄삭제
	@PostMapping("/deleteOrderDetails")
	@ResponseBody
	public String deleteOrderDetails(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> orderDetailIds = request.getOrderDetailIds();
		System.out.println(orderDetailIds);
		for (Integer orderDetail : orderDetailIds) {
			int orderDetailDelResult = order_DetailService.orderDetailDelete(orderDetail);
		}

		return "Delete Success";
	}

	// 대여 주문 항목 일괄처리
	@PostMapping("/updateRentStatus")
	@ResponseBody
	public String updateRentStatus(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> rentDetailIds = request.getOrderDetailIds();
		System.out.println("���뿬 �씪愿꾩쿂由� ���긽 紐⑸줉:" + rentDetailIds);

		String status = request.getStatus();

		for (Integer rentDetail : rentDetailIds) {

			RentDetailDTO rentDatilDTO = new RentDetailDTO();

			rentDatilDTO.setRentdetail_id(rentDetail);
			rentDatilDTO.setRent_state(status);

			int statusUpdateResult = rentDetailService.rentDetailStatusUpdate(rentDatilDTO);
		}
		return "Update Success";
	}

	// 대여 주문 항목 일괄삭제
	@PostMapping("/deleteRentDetails")
	@ResponseBody
	public String deleteRentDetails(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> rentDetailIds = request.getOrderDetailIds();
		System.out.println(rentDetailIds);
		for (Integer rentDetail : rentDetailIds) {
			int rentDetailDelResult = rentDetailService.rentDetailDelete(rentDetail);
		}
		return "Delete Success";
	}

	@PostMapping("/deleteSellStocks")
	@ResponseBody
	public String deleteSellStocks(@RequestBody StockUpdateDTO request) {
		List<String> sellStockIds = request.getSellStockIds();
		for (String sellStockId : sellStockIds) {
			int sellStockDelResult = seller_Prod_StockService.seller_prod_stockDelete(sellStockId);
		}
		return "Delete Success";
	}

	@PostMapping("/deleteRentStocks")
	@ResponseBody
	public String deletesSellStocks(@RequestBody StockUpdateDTO request) {
		List<String> rentStockIds = request.getSellStockIds();
		for (String rentStockId : rentStockIds) {
			int rentStockDelResult = rentProdStockService.rentProdDelete(rentStockId);
		}
		return "Delete Success";
	}

	// 문의 목록 페이지 보여주기
	@GetMapping("/Q&AList.do")
	public String qaList(Model model, Model model3, Model model4, HttpServletRequest request, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		
		model.addAttribute("sellerInfo", memberService.selectById(sellerID));

		// 구매자의 문의 목록
		System.out.println(buyer_inqService.selectInqList(sellerID));
		model3.addAttribute("buyerQAList", buyer_inqService.selectInqList(sellerID));
		// System.out.println(model1);
		model4.addAttribute("adminQAList", admin_inqService.selectByMemberId(sellerID));

		return "/seller/sellerQ&dAList";
	}

	// 상품 등록 페이지
	@GetMapping("/AddProduct.do")
	public String addProduct(Model model, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();

		//Depth 값이 1이고, 부모 카테고리 ID가 Null인 카테고리들을 가져오기
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategory_depth(1);
		categoryDTO.setParent_category_id(null);
		
		List<CategoryDTO> firstCategoryList = categoryService.firstDepthCategoryList();

		
		model.addAttribute("sellerInfo", memberService.selectById(sellerID));
		model.addAttribute("depth1categoryList", firstCategoryList);
		return "/seller/seller_addPrd";
	}

	// 상품 수정 페이지
	@GetMapping("/ModifyProduct.do")
	public String modifyProduct(Model model, @RequestParam("stock_id") String stockID, HttpSession session) throws UnsupportedEncodingException {

		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		
		//Depth 값이 1이고, 부모 카테고리 ID가 Null인 카테고리들을 가져오기
		CategoryDTO categoryList = new CategoryDTO();
		categoryList.setCategory_depth(1);
		categoryList.setParent_category_id(null);
		
		List<CategoryDTO> firstCategoryList = categoryService.firstDepthCategoryList();
		
		String stock_id = URLDecoder.decode(stockID, "UTF-8");// 한글로 변환
		System.out.println("가져온 stock_id:" + stock_id);

		// 재고ID가 어느 재고 테이블에 속하는지 확인
		Seller_Prod_StockDTO seller_Prod_StockDTO = seller_Prod_StockService.selectByStockId(stock_id);// 재고 데이터를 전부 끌어옴
		System.out.println("확인결과:" + seller_Prod_StockDTO);
		if (Objects.isNull(seller_Prod_StockDTO)) {// 대여상품 재고일 경우
			RentProdStockDTO rentProdStockDTO = rentProdStockService.selectById(stock_id);// 재고의 기본 정보 끌어오기
			System.out.println("�옱怨쟅D:" + rentProdStockDTO);
			
			String ProdID = rentProdStockDTO.getProd_id();
			
			//해당 재고와 연동되어 있는 상품의 정보 가져오기
			ProdDTO prodDTO = prodService.selectByProdId(ProdID);
			
			//해당 재고와 연결되어 있는 상품ID와 연동되어 있는 카테고리 정보 가져오기
			int category_id = prodDTO.getCategory_id();
			CategoryDTO categoryDTO =categoryService.selectById(category_id);

			Prod_ImageDTO mainImageDTO = new Prod_ImageDTO();
			mainImageDTO.setProd_id(ProdID);
			mainImageDTO.setImg_type(0);

			Prod_ImageDTO descImageDTO = new Prod_ImageDTO();
			descImageDTO.setProd_id(ProdID);
			descImageDTO.setImg_type(1);

			// 해당 재고의 상품ID와 연동되어 있는 상품이미지ID(=이미지 파일명) 목록을 가져온다.
			List<String> prodMainImgList = imageService.findMainImgFileNameByProdID(mainImageDTO);
			List<String> prodDescImgList = imageService.findDescImgFileNameByProdID(descImageDTO);

			System.out.println(prodMainImgList);
			System.out.println(prodDescImgList);

			List<Integer> optionList = new ArrayList<Integer>();

			optionList.add(rentProdStockDTO.getOpt_id1());
			optionList.add(rentProdStockDTO.getOpt_id2());
			optionList.add(rentProdStockDTO.getOpt_id3());
			optionList.add(rentProdStockDTO.getOpt_id4());
			optionList.add(rentProdStockDTO.getOpt_id5());

			List<Prod_OptionDTO> optionDTOList = new ArrayList<Prod_OptionDTO>();

			for (Integer option : optionList) {
				System.out.println(option);
				if (!Objects.isNull(option)) {
					Prod_OptionDTO prod_OptionDTO = prod_OptionService.selectByOptionId(option);
					optionDTOList.add(prod_OptionDTO);
				}
			}

			model.addAttribute("sellerInfo", memberService.selectById(sellerID));
			model.addAttribute("StockInfo", rentProdStockDTO);
			model.addAttribute("ProductInfo", prodDTO);
			model.addAttribute("CategoryInfo", categoryDTO);
			model.addAttribute("ProdMainImgList", prodMainImgList);
			model.addAttribute("ProdDescImgList", prodDescImgList);
			model.addAttribute("optionList", optionDTOList);
			model.addAttribute("depth1categoryList", firstCategoryList);

			return "/seller/seller_RentStock_modifyPrd";
		} else {// 판매상품 재고일 경우
			String ProdID = seller_Prod_StockDTO.getProd_id();
			
			//해당 재고와 연동되어 있는 상품의 정보 가져오기
			ProdDTO prodDTO = prodService.selectByProdId(ProdID);
			
			//해당 재고와 연결되어 있는 상품ID와 연동되어 있는 카테고리 정보 가져오기
			int category_id = prodDTO.getCategory_id();
			CategoryDTO categoryDTO =categoryService.selectById(category_id);
			
			Prod_ImageDTO mainImageDTO = new Prod_ImageDTO();
			mainImageDTO.setProd_id(ProdID);
			mainImageDTO.setImg_type(0);

			Prod_ImageDTO descImageDTO = new Prod_ImageDTO();
			descImageDTO.setProd_id(ProdID);
			descImageDTO.setImg_type(1);

			// 해당 재고의 상품ID와 연동되어 있는 상품이미지ID(=이미지 파일명) 목록을 가져온다.
			List<String> prodMainImgList = imageService.findMainImgFileNameByProdID(mainImageDTO);
			List<String> prodDescImgList = imageService.findDescImgFileNameByProdID(descImageDTO);

			List<Integer> optionList = new ArrayList<Integer>();

			optionList.add(seller_Prod_StockDTO.getOpt_id1());
			optionList.add(seller_Prod_StockDTO.getOpt_id2());
			optionList.add(seller_Prod_StockDTO.getOpt_id3());
			optionList.add(seller_Prod_StockDTO.getOpt_id4());
			optionList.add(seller_Prod_StockDTO.getOpt_id5());

			List<Prod_OptionDTO> optionDTOList = new ArrayList<Prod_OptionDTO>();

			for (Integer option : optionList) {
				System.out.println(option);
				if (!Objects.isNull(option)) {
					Prod_OptionDTO prod_OptionDTO = prod_OptionService.selectByOptionId(option);
					optionDTOList.add(prod_OptionDTO);
				}
			}

			// System.out.println(uploadDir);
			model.addAttribute("sellerInfo", memberService.selectById(sellerID));
			model.addAttribute("StockInfo", seller_Prod_StockDTO);
			model.addAttribute("ProductInfo", prodDTO);
			model.addAttribute("CategoryInfo", categoryDTO);
			model.addAttribute("ProdMainImgList", prodMainImgList);
			model.addAttribute("ProdDescImgList", prodDescImgList);
			model.addAttribute("optionList", optionDTOList);
			model.addAttribute("depth1categoryList", firstCategoryList);

			return "/seller/seller_SellStock_modifyPrd";
		}
	}

	// 구매자문의 답변 팝업
	@GetMapping("/answerCustomer.do")
	public String answerCustomer(Model model, @RequestParam("buyer_inq_id") Integer buyer_inq_id,
			HttpServletRequest request) {
		System.out.println(buyer_inq_id);
		System.out.println(buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		System.out.println(buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		model.addAttribute("bqa", buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		return "/seller/seller_CustomerQAPopUp";
	}

	// 구매자 문의에 답변
	@PostMapping("/answerCustomer.do")
	@ResponseBody
	public String answerCquestion(@RequestParam("buyer_inq_id") Integer buyerInqId,
			@RequestParam("buyer_reply") String buyerReply) throws UnsupportedEncodingException {

		String buyer_reply = URLDecoder.decode(buyerReply, "UTF-8");

		System.out.println(buyer_reply);

		// 업로드 날짜
		// 오늘 날짜를 LocalDate로 가져옴
		LocalDate localDate = LocalDate.now();

		// LocalDate를 java.sql.Date로 변환
		Date sqlDate = Date.valueOf(localDate);

		Buyer_InqDTO buyer_InqDTO = new Buyer_InqDTO();
		buyer_InqDTO.setBuyer_inq_id(buyerInqId);
		buyer_InqDTO.setBuyer_reply(buyer_reply);
		buyer_InqDTO.setBuyer_reply_date(sqlDate);

		System.out.println(buyer_InqDTO);

		int result = buyer_inqService.buyer_inqUpdate(buyer_InqDTO);
		System.out.println(result);
		return "Answer submitted successfully.";
	}

	// 관리자문의 조회 팝업
	@GetMapping("/answerAdmin.do")
	public String answerAdmin(Model model, @RequestParam("admin_inq_id") Integer admin_inq_id) {
		model.addAttribute("aqa", admin_inqService.selectByInqId(admin_inq_id));
		return "/seller/seller_AdminAPopUp";
	}

	// 관리자문의 등록 팝업
	@GetMapping("/addAdminQA.do")
	public String addAdminQA(Model model, HttpSession session) {
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String sellerID = mem.getMember_id();
		
		System.out.println("판매자ID : " + memberService.selectById(sellerID));
		model.addAttribute("aqa", memberService.selectById(sellerID));
		return "/seller/seller_AdminQPopUp";
	}

	// 관리자에게 문의 등록
	@PostMapping("/addAdminQA.do")
	@ResponseBody
	public String registerStoAquestion(@RequestParam("member_id") String mid,
			@RequestParam("admin_inq_title") String StoAqTitle, @RequestParam("admin_inq_content") String StoAq)
			throws UnsupportedEncodingException {

		String member_id = URLDecoder.decode(mid, "UTF-8");
		String admin_inq_title = URLDecoder.decode(StoAqTitle, "UTF-8");
		String admin_inq_content = URLDecoder.decode(StoAq, "UTF-8");

		// 업로드 날짜
		// 오늘 날짜를 LocalDate로 가져옴
		LocalDate localDate = LocalDate.now();

		// LocalDate를 java.sql.Date로 변환
		Date sqlDate = Date.valueOf(localDate);

		Integer maxAdminInqID = admin_inqService.findMaxAdminInqId();
		
		if (Objects.isNull(maxAdminInqID))
			maxAdminInqID = 0;
		maxAdminInqID++;

		// 이후에 SQL문으로 DB에 등록

		Admin_InqDTO admin_InqDTO = new Admin_InqDTO();
		admin_InqDTO.setAdmin_inq_id(maxAdminInqID);
		admin_InqDTO.setAdmin_inq_title(admin_inq_title);
		admin_InqDTO.setAdmin_inq_content(admin_inq_content);
		admin_InqDTO.setAdmin_inq_date(sqlDate);
		admin_InqDTO.setMember_id(member_id);

		int result = admin_inqService.admin_inqInsert(admin_InqDTO);
		System.out.println(result);
		return "Answer submitted successfully.";
	}

}