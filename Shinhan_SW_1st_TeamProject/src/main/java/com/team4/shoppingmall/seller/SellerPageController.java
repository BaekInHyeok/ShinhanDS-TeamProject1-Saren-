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
import com.team4.shoppingmall.member.MemberService;
import com.team4.shoppingmall.order_detail.OrderUpdateReqDTO;
import com.team4.shoppingmall.order_detail.Order_DetailDTO;
import com.team4.shoppingmall.order_detail.Order_DetailService;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.prod_image.Prod_ImageService;
import com.team4.shoppingmall.rent.RentDTO;
import com.team4.shoppingmall.rent_detail.RentDetailDTO;
import com.team4.shoppingmall.rent_detail.RentDetailService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
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
	Prod_ImageService imageService;

	@Autowired
	Seller_Prod_StockService seller_Prod_StockService;

	@Autowired
	RentProdStockService rentProdStockService;
	
	@Autowired
	Order_DetailService order_DetailService; 
	
	@Autowired
	RentDetailService rentDetailService;

	String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ)

	// ���� �̹������� ���丮
	@Value("${file.upload-dir}")
	private String uploadDir;

	// ���� ȭ�� �����ֱ�
	@GetMapping("/MainPage.do")
	public String mainpage(Model model) {

		// ���⼭ SQL���� ����� model�� �����͸� �����
		// ���⿡�� �Ǹ��ڰ� �Ǹ��ϴ� ��ǰ���� �Ǹŷ� �����͸� �������, �����͸� �׷���ȭ�Ͽ� ǥ��
		// model.addAttribute(result, flashMap);
		return "seller/sellerMain";
	}

	// �Ǹ�&�뿩 ��ǰ ������ �����ֱ�
	@GetMapping("/PrdList.do")
	public String prdList(Model model1, Model model2) {

		// �Ǹ� ��ǰ ����Ʈ
		model1.addAttribute("stockSList", seller_Prod_StockService.findSellStockList(member_id));

		System.out.println("�ǸŻ�ǰ ����Ʈ �ҷ���");
		// �뿩 ��ǰ ����Ʈ
		model2.addAttribute("stockRList", rentProdStockService.findRentStockList(member_id));

		System.out.println("�뿩��ǰ ����Ʈ �ҷ���");

		return "/seller/sellerPrdList";
	}

	// �Ǹ�&��� ������ �����ֱ�
	@GetMapping("/DeliveryList.do")
	public String deliveryList(Model model1, Model model2) {

		// �Ǹ�&��� ����Ʈ
		//1.�Ǹ� ��ǰ ��� �ֹ��󼼸���Ʈ
		System.out.println(order_DetailService.selectBySellerID(member_id));
		System.out.println(rentDetailService.selectBySellerID(member_id));
		
		model1.addAttribute("orderDetailList", order_DetailService.selectBySellerID(member_id));
		model2.addAttribute("rentDetailList", rentDetailService.selectBySellerID(member_id));
		return "/seller/sellerDelivery";
	}
	
	
	//�Ǹ� �ֹ� �׸� �ϰ�ó��
	@PostMapping("/updateOrderStatus")
	@ResponseBody
	public String updateOrderStauts(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> orderDetailIds = request.getOrderDetailIds();
        System.out.println(orderDetailIds);
		
		String status = request.getStatus();
        
        for(Integer orderDetail : orderDetailIds) {
        	
        	Order_DetailDTO order_DetailDTO = new Order_DetailDTO();
        	
        	order_DetailDTO.setOrderdetail_id(orderDetail);
        	order_DetailDTO.setOrder_state(status);
        	
        	int statusUpdateResult = order_DetailService.orderDetailStatusUpdate(order_DetailDTO);
        }
		return "Update Success";
	}
	
	//�Ǹ� �ֹ� �׸� �ϰ�����
	@PostMapping("/deleteOrderDetails")
	@ResponseBody
	public String deleteOrderDetails(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> orderDetailIds = request.getOrderDetailIds();
		System.out.println(orderDetailIds);
		for(Integer orderDetail : orderDetailIds) {
			int orderDetailDelResult = order_DetailService.orderDetailDelete(orderDetail);
		}
		
		return "Delete Success";
	}
	
	
	//�뿩 �ֹ� �׸� �ϰ�ó��
	@PostMapping("/updateRentStatus")
	@ResponseBody
	public String updateRentStatus(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> rentDetailIds = request.getOrderDetailIds();
		System.out.println("�뿩 �ϰ�ó�� ��� ���:"+rentDetailIds);
		
		String status = request.getStatus();
		
		for(Integer rentDetail : rentDetailIds) {
			
			RentDetailDTO rentDatilDTO = new RentDetailDTO();
			
			rentDatilDTO.setRentdetail_id(rentDetail);
			rentDatilDTO.setRent_state(status);
			
			int statusUpdateResult = rentDetailService.rentDetailStatusUpdate(rentDatilDTO);	
		}
		return "Update Success";
	}
	
	//�뿩 �ֹ� �׸� �ϰ�����
	@PostMapping("/deleteRentDetails")
	@ResponseBody
	public String deleteRentDetails(@RequestBody OrderUpdateReqDTO request) {
		List<Integer> rentDetailIds = request.getOrderDetailIds();
		System.out.println(rentDetailIds);
		for(Integer rentDetail : rentDetailIds) {
			int rentDetailDelResult = rentDetailService.rentDetailDelete(rentDetail);
		}
		return "Delete Success";
	}
	
	

	// ���� ��� ������ �����ֱ�
	@GetMapping("/Q&AList.do")
	public String qaList(Model model3, Model model4, HttpServletRequest request) {

		// �������� ���� ���
		System.out.println(buyer_inqService.selectInqList(member_id));
		model3.addAttribute("buyerQAList", buyer_inqService.selectInqList(member_id));
		// System.out.println(model1);
		model4.addAttribute("adminQAList", admin_inqService.selectByMemberId(member_id));

		return "/seller/sellerQ&dAList";
	}

	// ��ǰ ��� ������
	@GetMapping("/AddProduct.do")
	public String addProduct() {
		return "/seller/seller_addPrd";
	}

	// ��ǰ ���� ������
	@GetMapping("/ModifyProduct.do")
	public String modifyProduct(Model model1, Model model2, Model model3, Model model4,
			@RequestParam("stock_id") String stockID) throws UnsupportedEncodingException {

		String stock_id = URLDecoder.decode(stockID, "UTF-8");// �ѱ۷� ��ȯ

		// ���ID�� ��� ��� ���̺� ���ϴ��� Ȯ��
		Seller_Prod_StockDTO seller_Prod_StockDTO = seller_Prod_StockService.selectByStockId(stock_id);// ��� �����͸� ���� �����
		if (Objects.isNull(seller_Prod_StockDTO)) {// �뿩��ǰ ����� ���
			RentProdStockDTO rentProdStockDTO = rentProdStockService.selectById(stock_id);// ��ǰ�� �⺻ ������ ������� ���� ������Ϳ���
																							// ��ǰID�� �����´�.
			String ProdID = rentProdStockDTO.getProd_id();

			// �ش� ����� ��ǰID�� �����Ǿ� �ִ� ��ǰ�̹���ID(=�̹��� ���ϸ�) ����� �����´�.
			List<String> prodImgList = imageService.findAllImgFileNameByProdID(ProdID);

			model1.addAttribute("StockInfo", rentProdStockDTO);
			model2.addAttribute("ProductInfo", prodService.selectByProdId(ProdID));
			model3.addAttribute("ProdImgList", prodImgList);
			model4.addAttribute("ImgDirectory", uploadDir);
		} else {// �ǸŻ�ǰ ����� ���
			String ProdID = seller_Prod_StockDTO.getProd_id();
			// �ش� ����� ��ǰID�� �����Ǿ� �ִ� ��ǰ�̹���ID(=�̹��� ���ϸ�) ����� �����´�.
			List<String> prodImgList = imageService.findAllImgFileNameByProdID(ProdID);
			System.out.println(uploadDir);

			System.out.println(uploadDir);
			model1.addAttribute("StockInfo", seller_Prod_StockDTO);
			model2.addAttribute("ProductInfo", prodService.selectByProdId(ProdID));
			model3.addAttribute("ProdImgList", prodImgList);
			model4.addAttribute("ImgDirectory", uploadDir);
		}

		return "/seller/seller_modifyPrd";
	}

	@PostMapping("/resetProdImg")
	@ResponseBody
	public String resetProdImg(){
		System.out.println("���� �۾� ����");
		String prod_id = "����Ű%20����2_573-50-00882";
		System.out.println(prod_id);
		int imgDeleteResult = imageService.prod_imageDelete(prod_id);
		System.out.println("���� �Ϸ�");

		/*
		 * List if(imgDeleteResult>0) { Path filePath =
		 * Paths.get(uploadDir).resolve(prod_id); Files.delete(filePath);
		 * System.out.println(file_name+"���� �Ϸ�"); } else
		 * System.out.println(file_name+"���� ����");
		 */
		return "resetImgSuccess";
	}

	// �����ڹ��� �亯 �˾�
	@GetMapping("/answerCustomer.do")
	public String answerCustomer(Model model, @RequestParam("buyer_inq_id") Integer buyer_inq_id,
			HttpServletRequest request) {
		System.out.println(buyer_inq_id);
		System.out.println(buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		System.out.println(buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		model.addAttribute("bqa", buyer_inqService.selectByInqIdFORseller(buyer_inq_id));

		return "/seller/seller_CustomerQAPopUp";
	}

	// ������ ���ǿ� �亯
	@PostMapping("/answerCustomer.do")
	@ResponseBody
	public String answerCquestion(@RequestParam("buyer_inq_id") Integer buyerInqId,
			/*
			 * @RequestParam("member_id") String memberId,
			 * 
			 * @RequestParam("buyer_inq_title") String questionTitle,
			 * 
			 * @RequestParam("buyer_inq_content") String buyerInqContent,
			 */
			@RequestParam("buyer_reply") String buyerReply) throws UnsupportedEncodingException {

		String buyer_reply = URLDecoder.decode(buyerReply, "UTF-8");

		System.out.println(buyer_reply);

		// ���ε� ��¥
		// ���� ��¥�� LocalDate�� ������
		LocalDate localDate = LocalDate.now();

		// LocalDate�� java.sql.Date�� ��ȯ
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

	// �����ڹ��� ��ȸ �˾�
	@GetMapping("/answerAdmin.do")
	public String answerAdmin(Model model, @RequestParam("admin_inq_id") Integer admin_inq_id) {
		model.addAttribute("aqa", admin_inqService.selectByInqId(admin_inq_id));
		return "/seller/seller_AdminAPopUp";
	}

	// �����ڹ��� ��� �˾�
	@GetMapping("/addAdminQA.do")
	public String addAdminQA(Model model) {
		String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ)
		System.out.println("��ȸ : " + memberService.selectById(member_id));
		model.addAttribute("aqa", memberService.selectById(member_id));
		return "/seller/seller_AdminQPopUp";
	}

	// �����ڿ��� ���� ���
	@PostMapping("/addAdminQA.do")
	@ResponseBody
	public String registerStoAquestion(@RequestParam("member_id") String mid,
			@RequestParam("admin_inq_title") String StoAqTitle, @RequestParam("admin_inq_content") String StoAq)
			throws UnsupportedEncodingException {

		String member_id = URLDecoder.decode(mid, "UTF-8");
		String admin_inq_title = URLDecoder.decode(StoAqTitle, "UTF-8");
		String admin_inq_content = URLDecoder.decode(StoAq, "UTF-8");

		// ���ε� ��¥
		// ���� ��¥�� LocalDate�� ������
		LocalDate localDate = LocalDate.now();

		// LocalDate�� java.sql.Date�� ��ȯ
		Date sqlDate = Date.valueOf(localDate);

		// ����ID ����
		Integer qid = 12305;

		// ���Ŀ� SQL������ DB�� ���

		Admin_InqDTO admin_InqDTO = new Admin_InqDTO();
		admin_InqDTO.setAdmin_inq_id(qid);
		admin_InqDTO.setAdmin_inq_title(admin_inq_title);
		admin_InqDTO.setAdmin_inq_content(admin_inq_content);
		admin_InqDTO.setAdmin_inq_date(sqlDate);
		admin_InqDTO.setMember_id(member_id);

		int result = admin_inqService.admin_inqInsert(admin_InqDTO);
		System.out.println(result);
		return "Answer submitted successfully.";
	}

}