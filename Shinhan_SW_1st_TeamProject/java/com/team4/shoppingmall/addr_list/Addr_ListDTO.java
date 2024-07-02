package com.team4.shoppingmall.addr_list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Addr_ListDTO {
	int addr_num;			//�ּ�ID
	String member_id;		//ȸ��ID
	String zipcode;			//�����ȣ
	String main_address;	//���θ� �ּ�
	String detail_address;	//���ּ�
	Boolean is_master_addr;	//��ǥ�ּ� ����
	String sub_address;		//�����ּ�
	
}