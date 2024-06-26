package com.team4.shoppingmall;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.shoppingmall.order_prod.OrderProdDTO;
import com.team4.shoppingmall.order_prod.OrderProdService;
import com.team4.shoppingmall.prod.ProdDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.rent.RentDTO;
import com.team4.shoppingmall.rent.RentService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;

@Controller
@RequestMapping("/customer")
public class TestControllerYun { 
	
		@Autowired
		OrderProdService orderProdService;
		@Autowired
		RentService rentService;
		@Autowired
		ProdService prodService;
	
		// ��ǰ���
		@GetMapping("/productlist.do")
		public String test1(Model model, HttpServletRequest request) {
			System.out.println("/customer/productlist.jsp");  
			// ��� ��ǰ���
			List<Map<String, Object>> prodAllOrders = prodService.selectAll2();
			System.out.println("��ü��ǰ���" + prodAllOrders);
			model.addAttribute("prodAllOrders", prodAllOrders);

			return "customer/productlist";   
		}
		
		// �뿩���
		@GetMapping("/rentlist.do")
		public String test2(Model model, HttpServletRequest request) {
			System.out.println("/customer/rentlist.jsp"); 
			
			// ��� �뿩���
			List<RentDTO> rentAllOrders = rentService.selectAll(); 
			System.out.println("��ü�뿩����" + rentAllOrders);
			
			// �ֹ��� �󼼸�� Map
	        Map<Integer, Map<String, Object>> rentDetailsMap = new HashMap();
			
	        // �� �ֹ��� ���� �ֹ� �� ���
	        for (RentDTO rent : rentAllOrders) {
	            int rental_code = rent.getRental_code();
	            Map<String, Object> rentDetails = rentService.selectById2(rental_code);
	            rentDetailsMap.put(rental_code, rentDetails);
	        }
	        System.out.println("�� �󼼴뿩����" + rentDetailsMap);
	        
	        //�� ��ǰ�� ��� �ɼ�
	        List<RentProdStockDTO> optionList = rentService.selectOptions();
			
			model.addAttribute("rentAllOrders", rentAllOrders);
			model.addAttribute("rentDetailsMap", rentDetailsMap);
			model.addAttribute("optionList", optionList);
			
			return "customer/rentlist";   
		}
		
		// �ֹ����
		@GetMapping("/orderlist.do")
		public String test3(Model model, HttpServletRequest request) {
			System.out.println("/customer/orderlist.jsp");
			
			// ��� �ֹ����
			List<OrderProdDTO> allorders = orderProdService.selectAll();
			System.out.println("��ü�ֹ�����" + allorders);  
			
			// �ֹ��� �󼼸�� Map
	        Map<Integer, Map<String, Object>> orderDetailsMap = new HashMap();
			
	        // �� �ֹ��� ���� �ֹ� �� ���
	        for (OrderProdDTO order : allorders) {
	            int orderId = order.getOrder_id();
	            Map<String, Object> orderDetails = orderProdService.selectById2(orderId);
	            orderDetailsMap.put(orderId, orderDetails);
	        }
			System.out.println("�� ���ֹ�����" + orderDetailsMap);
			
			model.addAttribute("allOrders", allorders);
			model.addAttribute("orderDetailsMap", orderDetailsMap);
			model.addAttribute("optionList", orderProdService.selectOptions());
			
			return "customer/orderlist";  
		} 
		
	    // ȯ�ҿ�û
	    @PostMapping("/refund.do")
	    public void processRefund(@RequestParam("orderId") int orderId, HttpServletResponse response) throws IOException {
	        System.out.println("ȯ�ҿ�û(�ֹ���ȣ): " + orderId);
	        
	        //�Ǹ��ڿ��� ȯ�ҿ�û ������
	        
	        //boolean refundSuccess = orderProdService.processRefund(orderId);
	        boolean refundSuccess = true;
	        
	        if (refundSuccess) {
	            response.getWriter().write("success");
	        } else {
	            response.getWriter().write("failure");
	        }
	    }

		

}
