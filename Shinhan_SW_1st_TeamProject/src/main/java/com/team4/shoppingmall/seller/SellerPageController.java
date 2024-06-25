package com.team4.shoppingmall.seller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/seller")
public class SellerPageController {

	@Autowired
	Buyer_InqService buyer_inqService;
	
	@Autowired
	Admin_InqService admin_inqService;
	
	@Autowired
	MemberService memberService;

	// ���� ȭ�� �����ֱ�
	@GetMapping("/MainPage.do")
	public String mainpage(Model model, HttpServletRequest request) {

		// �� ���� ������ ������ �� �𸣰ڴ�...
		String result = "";
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			result = (String) flashMap.get("deptResult");
			System.out.println("result:" + result);
		}

		// ���⼭ SQL���� ����� model�� �����͸� �����
		// ���⿡�� �Ǹ��ڰ� �Ǹ��ϴ� ��ǰ���� �Ǹŷ� �����͸� �������, �����͸� �׷���ȭ�Ͽ� ǥ��
		// model.addAttribute(result, flashMap);
		return "seller/sellerMain";
	}

	// �Ǹ�&�뿩 ��ǰ ������ �����ֱ�
	@GetMapping("/PrdList.do")
	public String prdList(Model model1, Model model2, HttpServletRequest request) {

		// �Ǹ� ��ǰ ����Ʈ
		// model1.addAttribute("stockSList", request);

		// �뿭 ��ǰ ����Ʈ
		// model2.addAttribute("stockRList", request);

		return "/seller/sellerPrdList";
	}

	// �Ǹ�&��� ������ �����ֱ�
	@GetMapping("/DeliveryList.do")
	public String deliveryList(Model model, HttpServletRequest request) {

		// �Ǹ�&��� ����Ʈ
		// model.addAttribute("deliveryList", request);

		return "/seller/sellerDelivery";
	}

	// ���� ��� ������ �����ֱ�
	@GetMapping("/Q&AList.do")
	public String qaList(Model model1, Model model2, HttpServletRequest request) {
		String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ)
		// �������� ���� ���
		System.out.println(buyer_inqService.selectInqList(member_id));
		model1.addAttribute("buyerQAList", buyer_inqService.selectInqList(member_id));
		//System.out.println(model1);
		model2.addAttribute("adminQAList", admin_inqService.selectByMemberId(member_id));
		
		return "/seller/sellerQ&dAList";
	}

	// ��ǰ ��� ������
	@GetMapping("/AddProduct.do")
	public String addProduct() {
		return "/seller/seller_addPrd";
	}

	// ��ǰ ���� ������
	@GetMapping("/ModifyProduct.do")
	public String modifyProduct() {
		return "/seller/seller_modifyPrd";
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
	public String answerCquestion(
			@RequestParam("buyer_inq_id") Integer buyerInqId,
			/*
			 * @RequestParam("member_id") String memberId,
			 * 
			 * @RequestParam("buyer_inq_title") String questionTitle,
			 * 
			 * @RequestParam("buyer_inq_content") String buyerInqContent,
			 */
			@RequestParam("buyer_reply") String buyerReply)
			throws UnsupportedEncodingException {
		

		String buyer_reply = URLDecoder.decode(buyerReply,"UTF-8");

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
	public String addAdminQA(Model model){
		String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ)
		System.out.println("��ȸ : "+ memberService.selectById(member_id));
		model.addAttribute("aqa", memberService.selectById(member_id));
		return "/seller/seller_AdminQPopUp";
	}

	// �����ڿ��� ���� ���
	@PostMapping("/addAdminQA.do")
	@ResponseBody
	public String registerStoAquestion(
		@RequestParam("member_id") String mid,
		@RequestParam("admin_inq_title") String StoAqTitle,
		@RequestParam("admin_inq_content") String StoAq
		) throws UnsupportedEncodingException {

		String member_id=URLDecoder.decode(mid,"UTF-8");
		String admin_inq_title=URLDecoder.decode(StoAqTitle,"UTF-8");
		String admin_inq_content=URLDecoder.decode(StoAq,"UTF-8");
		
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