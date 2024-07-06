package com.team4.shoppingmall.cart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.shoppingmall.member.MemberDTO;
import com.team4.shoppingmall.order_detail.Order_DetailDTO;
import com.team4.shoppingmall.order_detail.Order_DetailService;
import com.team4.shoppingmall.order_prod.OrderProdDAOInterface;
import com.team4.shoppingmall.order_prod.OrderProdDTO;
import com.team4.shoppingmall.order_prod.OrderProdService;
import com.team4.shoppingmall.prod.ProdDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.rent.RentDAOInterface;
import com.team4.shoppingmall.rent.RentDTO;
import com.team4.shoppingmall.rent.RentService;
import com.team4.shoppingmall.rent_detail.RentDetailDTO;
import com.team4.shoppingmall.rent_detail.RentDetailService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockDTO;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderProdService orderProdService;
	
	@Autowired
	RentService rentService;
	
	@Autowired
	OrderProdDAOInterface orderprodDAO;
	
	@Autowired
	RentDAOInterface rentprodDAO;
	
	@Autowired
	Seller_Prod_StockService seller_Prod_StockService;
	
	@Autowired
	RentProdStockService rentProdStockService;
	
	@Autowired
	ProdService prodService;
	
	@Autowired
	Order_DetailService order_DetailService;
	
	@Autowired
	RentDetailService rentDetailService;
	
	@PostMapping("/createOrder.do")
	@ResponseBody
	public String createOrder(@RequestBody Integer request, HttpSession session) {
		
		MemberDTO mem = (MemberDTO)session.getAttribute("member");
		String customerID = mem.getMember_id();
		
		LocalDate localDate = LocalDate.now();
		// LocalDate�� ���� ��¥�� �޾ƿ� SQL.Date�� ��ȯ
		Date sqlDate = Date.valueOf(localDate);
		
		// 7�� ���� ��¥�� LocalDate�� ���
        LocalDate futureDate = localDate.plusDays(7);

        // 7�� ���� ��¥�� SQL Date�� ��ȯ
        Date sqlFutureDate = Date.valueOf(futureDate);
		
		//��ٱ��Ͽ��� ��ٱ��� ID ��� �Ѱܹޱ�
		List<Integer> cartIdList = request.getCartIds();
		
		//�뿩 �׸�� �ֹ� �׸��� �ִ밪�� ���� ��������
		int maxOrder_id = orderprodDAO.sequenceOrderId()+1; 
		int maxRent_id = rentprodDAO.searchRentId()+1;
		
		//�� ���Ű�
		int orderTotal_price = 0;
		int rentTotal_price = 0;
		
		//�� ��ٱ��� ID�� ���� �ݺ��� ó��
		for(Integer cartId : cartIdList) {
			CartDTO cartDTO = cartService.selectByCartId(cartId);
			
			String s_stock_ID = cartDTO.getS_stock_id();
			String r_stock_ID = cartDTO.getR_stock_id();
			Integer amount = cartDTO.getCart_amount();
			
			//��ٱ��� ID�� �Ǹ� ��ǰ�� ���� ���
			if(!Objects.isNull(s_stock_ID)&&Objects.isNull(r_stock_ID)) {
				//�ش� �� �ֹ��� �Ǹ� ����� ������ �˾Ƴ�
				Seller_Prod_StockDTO seller_Prod_StockDTO = seller_Prod_StockService.selectByStockId(s_stock_ID);
				ProdDTO prodDTO = prodService.selectByProdId(seller_Prod_StockDTO.getProd_id());
				
				//�ش� ��� ���� �Ǹ� �� ��ü ����
				Order_DetailDTO order_DetailDTO = new Order_DetailDTO();
				order_DetailDTO.setOrderdetail_id(0);//�ǸŻ� ID ����
				order_DetailDTO.setOrder_num(amount);
				order_DetailDTO.setOrder_id(maxOrder_id);
				order_DetailDTO.setOrder_product_price(prodDTO.getProd_price());
				order_DetailDTO.setS_stock_id(s_stock_ID);
				order_DetailDTO.setOrder_state("���������");
				
				int price = amount * prodDTO.getProd_price();
				orderTotal_price += price;
				
				int ordDetailInsertResult = order_DetailService.orderDetailInsert(order_DetailDTO);
			}
			//��ٱ��� ID�� �뿩 ��ǰ�� ���� ���
			else if(Objects.isNull(s_stock_ID)&&!Objects.isNull(r_stock_ID)) {
				//�ش� �� �ֹ��� �뿩 ��� ������ �˾Ƴ�
				RentProdStockDTO rentProdStockDTO = rentProdStockService.selectById(r_stock_ID);
				ProdDTO prodDTO = prodService.selectByProdId(rentProdStockDTO.getProd_id());
				
				//�ش� ��� ���� �뿩 �� ��ü ����
				RentDetailDTO rentDetailDTO = new RentDetailDTO();
				rentDetailDTO.setRentdetail_id(0);//�뿩��ID����
				rentDetailDTO.setRent_num(amount);
				rentDetailDTO.setRental_code(maxRent_id);
				rentDetailDTO.setRent_product_price(prodDTO.getProd_price());
				rentDetailDTO.setR_stock_id(r_stock_ID);
				rentDetailDTO.setRent_state("�뿩��û�Ϸ�");
				
				int price = amount * prodDTO.getProd_price();
				rentTotal_price += price;
				
				int rentDetailInsertResult = rentDetailService.rentDetailInsert(rentDetailDTO);
			}
		}
		
		List<Order_DetailDTO> OrderDetailList = order_DetailService.selectByOrder_Id(maxOrder_id);
		List<RentDetailDTO> RentDetailList = rentDetailService.selectByRental_code(maxRent_id);
		
		if(!Objects.isNull(OrderDetailList) && Objects.isNull(RentDetailList)) {
			OrderProdDTO orderProdDTO = new OrderProdDTO();
			orderProdDTO.setOrder_id(maxOrder_id);
			orderProdDTO.setOrder_date(sqlDate);
			orderProdDTO.setMember_id(customerID);
			orderProdDTO.setTotal_price(orderTotal_price);
			orderProdDTO.setAddr_num(0);
			
			int orderInsertResult = orderprodDAO.orderprodInsert(orderProdDTO);
			
			return "order Created";
		
		}else if(Objects.isNull(OrderDetailList) && !Objects.isNull(RentDetailList)) {
			RentDTO rentDTO = new RentDTO();
			rentDTO.setRental_code(maxRent_id);
			rentDTO.setRent_start_date(sqlDate);
			rentDTO.setRent_end_date(sqlFutureDate);
			rentDTO.setMember_id(customerID);
			rentDTO.setTotal_rent_price(rentTotal_price);
			
			int rentInsertResult = rentprodDAO.rentInsert(rentDTO);
			
			return "rent Created";
		}		
	}
	
	@PostMapping("/deleteCart.do")
	@ResponseBody
	public String deleteCart(@RequestParam("cart_id") Integer cartID) {
		int cartDeleteResult = cartService.cartDelete(cartID);
		
		return "cart Deleted";
	}
	
	@PostMapping("/changeCartAmount.do")
	@ResponseBody
	public String changeCartAmount(
			@RequestParam("cart_id") Integer cartID,
			@RequestParam("cart_amount") int cartAmount
			) {
		
		CartDTO cartDTO = cartService.selectByCartId(cartID);
		cartDTO.setCart_amount(cartAmount);
		
		int cartAmountUpdateResult = cartService.cartUpdate(cartDTO);
		
		return "cartAmount Updated";
	}
	
	
	
	
	
}
