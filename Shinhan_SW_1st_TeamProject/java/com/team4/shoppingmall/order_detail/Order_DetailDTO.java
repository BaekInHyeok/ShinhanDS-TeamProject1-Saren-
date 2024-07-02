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
	int order_id;				//�ֹ�ID
	int stock_id;				//���ID
	
}
