package com.team4.shoppingmall.coupon;

import java.util.List;

public interface CouponDAOInterface {
	
	// ������
	public CouponDTO selectById(String coupon_id);
	
	// �������
	public List<CouponDTO> selectAll();
	
//	public List<CouponDTO> selectByCondition();
	
	// �������
//	public int couponInsert(CouponDTO coupon);
	
	// ��������
//	public int couponUpdate(CouponDTO coupon);
	
	// ��������
//	public int couponDelete(String coupon_id);
	
	// �������
	public int couponUse(String coupon_id);
}
