package com.team4.shoppingmall.customerTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.team4.shoppingmall.coupon.CouponDTO;
import com.team4.shoppingmall.coupon.CouponService;
import com.team4.shoppingmall.member.MemberDTO;
import com.team4.shoppingmall.member.MemberService;
import com.team4.shoppingmall.order_detail.Order_DetailDTO;
import com.team4.shoppingmall.order_detail.Order_DetailService;
import com.team4.shoppingmall.order_prod.OrderProdDTO;
import com.team4.shoppingmall.order_prod.OrderProdService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private OrderProdService orderProdService;
	
	@Autowired
	private Order_DetailService orderDetailService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PaymentService paymentService;
	
	

	// ��ǰ����������
	@GetMapping("/orderPay")
	public String orderPayPage(Model model1, Model model2, Model model3) {
		
		String customerID = "bih63879";//�ӽ� �� ID. Session���� �޾ƿ� ����
		
		Integer orderID = 1;//�ֹ�ID, �ֹ��ϱ� ��ư�� ���� �� �����Ǵ� ���� ������ ����
		
		//�ֹ� �׸� ������ ��������
		OrderProdDTO orderProdDTO = orderProdService.selectById(orderID);
		
		//�� �ֹ� ���� ���
		List<Order_DetailDTO> orderDetailList = orderDetailService.selectByOrder_Id(orderID);

		//ȸ������
		MemberDTO memberDTO = memberService.selectById(customerID);
		
		//ȸ���� �ֹ� ȭ�鿡�� ��� ������ ���� ���
		/*List<CouponDTO> usableCouponList = new 
				
				couponService.selectCustomerCouponList(customerID);*/
		
		
		model1.addAttribute("orderInfo",orderProdDTO);
		model2.addAttribute("orderDetailList",orderDetailList);
		model3.addAttribute("memberInfo", memberDTO);
		return "customer/customerPay";
	}

	// ��ǰ ���� �Ϸ�������
	@GetMapping("/orderSuccess")
	public String orderSuccessPage() {
		return "customer/customerOrderSuccess";
	}


	//���� �� ���� ������ ���� �����ݾ� �������
	@PostMapping("/preparePayment")
	@ResponseBody
	public String preparePayment(@RequestParam String merchantUid, @RequestParam int amount) {
		return paymentService.registerPaymentAmount(merchantUid, amount);
	}

	//���� ���뿡 ���� ����
	@PostMapping("/verifyPayment")
	@ResponseBody
	public String verifyPayment(@RequestParam("imp_uid") String impUid,
                                @RequestParam("merchant_uid") String merchantUid) {
		return paymentService.verifyPayment(impUid, merchantUid);
	}

}
