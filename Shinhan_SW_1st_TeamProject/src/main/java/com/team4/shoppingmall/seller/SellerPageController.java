package com.team4.shoppingmall.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public class SellerPageController {

	// ���� ȭ�� �����ֱ�
	@RequestMapping("/MainPage.do")
	public String mainpage() {
		return "seller/sellerMain";
	}


	// �Ǹ�&�뿩 ��ǰ ������ �����ֱ�
	@GetMapping("/PrdList.do")
	public String prdList() {
		return "/seller/sellerPrdList";
	}

	// �Ǹ�&��� ������ �����ֱ�
	@GetMapping("/DeliveryList.do")
	public String deliveryList() {
		return "/seller/sellerDelivery";
	}

	// ���� ��� ������ �����ֱ�
	@GetMapping("/Q&AList.do")
	public String qaList() {
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
	public String answerCustomer() {
		return "/seller/seller_CustomerQAPopUp";
	}

	// �����ڹ��� ��ȸ �˾�
	@GetMapping("/answerAdmin.do")
	public String answerAdmin() {
		return "/seller/seller_AdminAPopUp";
	}

	// �����ڹ��� ��� �˾�
	@GetMapping("/addAdminQA.do")
	public String addAdminQA() {
		return "/seller/seller_AdminQPopUp";
	}

}