package com.team4.shoppingmall.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdController {
	
	/* ��ǰ �� ������ */
	//@GetMapping("/customer/product_detail")
	public void productDetail() {
		
	}
	
	//��ǰ ������ �ɼ� �� �����ϱ�
	//@GetMapping("/customer/productOption")
	public void productInsert(@RequestParam("product-size") String productSize,
							  @RequestParam("product-color") String productColor) {
		
		// ���õ� �ɼ� �� Ȯ�� 
        System.out.println("Selected option1: " + productSize);
        System.out.println("Selected option2: " + productColor);
	}
}
