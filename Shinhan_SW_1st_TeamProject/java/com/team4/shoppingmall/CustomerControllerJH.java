package com.team4.shoppingmall;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.shoppingmall.addr_list.AddrService;
import com.team4.shoppingmall.addr_list.Addr_ListDTO;
import com.team4.shoppingmall.cart.CartDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.prod_optionTest.Prod_OptionTestService;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestDTO;
import com.team4.shoppingmall.seller_prod_stockTest.Seller_Prod_StockTestService;

@Controller
@RequestMapping("/customer")
public class CustomerControllerJH {
	
	@Autowired
	AddrService addrService; //ȸ�� �ּ� 
	

	/*���������� ����*/
	@GetMapping("/myPage.do")
	public String myPage() {
		//1.���� �ֹ� ��� �ҷ����� (����� 1���� ���δ�) 
		
		//2.���� �뿩 ��� �ҷ�����
		
		return "customer/myPage";
	}

	//���� �ֹ� ����Ʈ
	@GetMapping("/orderlist")
	public String orderlist() {
		
		return "customer/orderlist";
	}
	
	//���� �뿩 ����Ʈ
	@GetMapping("/rentlist")
	public String rentlist() {
		//�뿩 ��� ��ȸ
		
		return "customer/rentlist";
	}

	
	/* ȸ���������� */
	//step1
	@GetMapping("/myInfoUpdate.do")
	public String myInfoUpdate(Model model) {
		
		//����� ��� ��ȸ �׽�Ʈ ��
		List<Addr_ListDTO> addrlist = addrService.selectAll();
		model.addAttribute("addrlist", addrlist);
		
		System.out.println("--���� ����� ��ȸ" + addrlist);
		
		return "customer/myInfoUpdate";
	}
	
	//step2 - ��й�ȣ Ȯ�� â
	@GetMapping("/myInfoUpdatePw.do")
	public String myInfoUpdatePw() {
		
		return "customer/myInfoUpdate_step2";
	}
	
	
	//��й�ȣ üũ �� ���� ����(step3)
	@GetMapping("/myInfoUpdatePwCheck.do")
	public String myInfoUpdatePwCheck(@RequestParam("password") String password) {
		
		//�α��� ȸ�� ��й�ȣ üũ(session���� ���� ����)
        String member_id = "testid"; //pw = 1111
        
		if(password.equals("aaa")) {
			return "customer/myInfoUpdate_step3";
		}else {
			System.out.println("���������� ȸ�� ��й�ȣ Ȯ�� ����");
			return "redirect:customer/myInfoUpdate_step2";
		}
		
	}
	
	//step3 - ������ ȸ�� ���� �Է�â	
	@PostMapping("/myInfoUpdateForm.do")
	public String myInfoUpdateForm() {
		System.out.println("����� ��ȸ");
		
		return "customer/myPage";
	}
	
	
	/* ȸ�� Ż�� */
	@GetMapping("/memberDelete.do")
	public String memberDelete() {
		
		return "customer/memberDelete";
	}
	//��й�ȣ üũ �� ȸ�� Ż��
	@GetMapping("/memberDeletePwCheck.do")
	public String memberDeletePwCheck(@RequestParam("password") String password) {
		//�α��� ȸ�� ��й�ȣ üũ
		if(password.equals("aaa")) {
			return "customer/myPage";
		}else {
			System.out.println("ȸ�� Ż�� ����");
			return "redirect:customer/memberDelete";
		}
		
	}
	

}
