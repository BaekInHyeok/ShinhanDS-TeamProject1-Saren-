package com.team4.shoppingmall;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/customer")
public class TestControllerYun { 
	
		//��ǰ���
		@GetMapping("/productlist.do")
		public String test1(Model model, HttpServletRequest request) {
			System.out.println("/customer/productlist.jsp"); 
			return "customer/productlist";   
		}
		//�뿩���
		@GetMapping("/rentlist.do")
		public String test2(Model model, HttpServletRequest request) {
			System.out.println("/customer/rentlist.jsp"); 
			return "customer/rentlist";  
		}
		//�ֹ����
		@GetMapping("/orderlist.do")
		public String test3(Model model, HttpServletRequest request) {
			System.out.println("/customer/orderlist.jsp"); 
			return "customer/orderlist";  
		}

}
