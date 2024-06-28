package com.team4.shoppingmall.order_detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order_DetailDTO {
	int orderdetail_id;   	 	//�ֹ��󼼹�ȣ
	int order_product_price;	//��ǰ����
	int order_num;				//�ֹ�����
	String order_state;			//�ֹ�����
	int order_id;				//�ֹ�ID
	String s_stock_id;			//���ID
	
}
