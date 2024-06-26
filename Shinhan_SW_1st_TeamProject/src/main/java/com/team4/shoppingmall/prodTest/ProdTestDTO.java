package com.team4.shoppingmall.prodTest;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdTestDTO {
	String prod_id;
	String prod_name;
	String prod_desc;
	Integer prod_price;
	Date prod_added_date;
	Integer category_id;
	String member_id;
}