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
import com.team4.shoppingmall.addr_list.Addr_ListController;
import com.team4.shoppingmall.addr_list.Addr_ListDTO;
import com.team4.shoppingmall.addr_list.Addr_ListService;
import com.team4.shoppingmall.coupon.CouponDTO;
import com.team4.shoppingmall.coupon.CouponService;
import com.team4.shoppingmall.customer.CustomerDTO;
import com.team4.shoppingmall.customer.CustomerService;
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
	private Addr_ListService addr_ListService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CustomerService customerService;
	
	String customerID = "bih63879";// �ӽ� �� ID. Session���� �޾ƿ� ����

	Integer orderID = 1;// �ֹ�ID, �ֹ��ϱ� ��ư�� ���� �� �����Ǵ� ���� ������ ����

	// ��ǰ����������
	@GetMapping("/orderPay")
	public String orderPayPage(Model model1, Model model2, Model model3, Model model4, Model model5, Model model6) {

		// �ֹ� �׸� ������ ��������
		OrderProdDTO orderProdDTO = orderProdService.selectById(orderID);

		// �� �ֹ� ���� ���
		List<Order_DetailDTO> orderDetailList = orderDetailService.selectByOrder_Id(orderID);

		// ȸ������
		MemberDTO memberDTO = memberService.selectById(customerID);
		
		// ȸ���� �ּ� ���
		List<Addr_ListDTO> addr_ListDTOs=addr_ListService.selectByMember_Id(customerID);

		// ȸ���� �ֹ� ȭ�鿡�� ��� ������ ���� ���
		List<CouponDTO> usableCouponList = couponService.selectCustomerCouponList(customerID);

		CustomerDTO customerDTO = customerService.selectById(customerID);
		System.out.println("���߰�����:"+customerDTO);
		model1.addAttribute("orderInfo", orderProdDTO);
		model2.addAttribute("orderDetailList", orderDetailList);
		model3.addAttribute("memberInfo", memberDTO);
		model4.addAttribute("couponList", usableCouponList);
		model5.addAttribute("customerInfo",customerDTO);
		model6.addAttribute("addrList", addr_ListDTOs);

		return "customer/customerPay";
	}

	@PostMapping("/applyCoupon")
	@ResponseBody
	public String applyCoupon(@RequestBody CouponRequestDTO couponRequestDTO) {

		String couponid = couponRequestDTO.getCouponid();
		int orderid = couponRequestDTO.getOrderid();

		if("���þ���".equals(couponid)) {
			return "Coupon applied";
		}else {
			// �ֹ� �׸� ������ ��������
			OrderProdDTO orderProdDTO = orderProdService.selectById(orderid);
			int totalPrice = orderProdDTO.getTotal_price();
			
			System.out.println("����ID:" + couponid);
			CouponDTO selectCouponDTO = couponService.selectById(couponid);

			System.out.println("��������:" + selectCouponDTO);
			double discountRate = selectCouponDTO.getDiscount_rate();

			int discountAmount = (int) Math.round(totalPrice * (discountRate / 100.0));

			int discountedPrice = totalPrice - discountAmount;

			System.out.println("����:" + discountAmount);
			System.out.println("���ΰ���:" + discountedPrice);
			
			int couponAmount = selectCouponDTO.getQuantity();
			selectCouponDTO.setQuantity(couponAmount-1);
			
			int couponUpdate = couponService.couponUse(selectCouponDTO);
			
			OrderProdDTO updatedPrice = new OrderProdDTO();
			updatedPrice.setOrder_id(orderid);
			updatedPrice.setTotal_price(discountedPrice);
			
			int appliedResult = orderProdService.updateOrderPrice(updatedPrice);
			
			return "Coupon applied";
		}
		
	}
	
	@PostMapping("/applyPoint")
	@ResponseBody
	public String applyCoupon(@RequestBody PointRequestDTO pointRequestDTO) {
		int point = pointRequestDTO.getPoint();
		System.out.println("����� ����Ʈ:"+point);
		int orderid = pointRequestDTO.getOrderid();
		
		OrderProdDTO orderProdDTO = orderProdService.selectById(orderid);
		int totalPrice = orderProdDTO.getTotal_price();
		
		int pointAppliedPrice = totalPrice - point;
		System.out.println("����Ʈ ��� �� ������:"+pointAppliedPrice);
		
		OrderProdDTO updatedPrice = new OrderProdDTO();
		updatedPrice.setOrder_id(orderid);
		updatedPrice.setTotal_price(pointAppliedPrice);
		
		CustomerDTO customerDTO = customerService.selectById(customerID);
		int existPoint = customerDTO.getPoint();
		customerDTO.setPoint(existPoint - point);
		
		int CustomerPointUpdate = customerService.customerUpdate(customerDTO);
		
		int appliedResult = orderProdService.updateOrderPrice(updatedPrice);
		
		return "Point Used";
	}
	
	@PostMapping("/applyAddress")
	@ResponseBody
	public String applyAddress(@RequestBody AddressRequestDTO request) {
		System.out.println(request);
		
		int addr_num=request.getAddr_num();
		int order_id=request.getOrder_id();
		
		OrderProdDTO orderProdDTO = orderProdService.selectById(order_id);
		System.out.println("�ֹ�����:"+orderProdDTO);
		orderProdDTO.setAddr_num(addr_num);
		
		int addrUpdateResult = orderProdService.orderprodUpdate(orderProdDTO);
		
		return "Address Saved";
	}

	// ��ǰ ���� �Ϸ�������
	@GetMapping("/orderSuccess")
	public String orderSuccessPage() {
		return "customer/customerOrderSuccess";
	}

	// ���� �� ���� ������ ���� �����ݾ� �������
	@PostMapping("/preparePayment")
	@ResponseBody
	public String preparePayment(@RequestParam String merchantUid, @RequestParam int amount) {
		return paymentService.registerPaymentAmount(merchantUid, amount);
	}

	// ���� ���뿡 ���� ����
	@PostMapping("/verifyPayment")
	@ResponseBody
	public String verifyPayment(@RequestParam("imp_uid") String impUid,
			@RequestParam("merchant_uid") String merchantUid) {
		return paymentService.verifyPayment(impUid, merchantUid);
	}

}
