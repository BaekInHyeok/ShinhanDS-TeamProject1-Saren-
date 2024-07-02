package com.team4.shoppingmall.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	
	@Autowired
	CouponDAOInterface couponDAO;
	
	// ������
	public CouponDTO selectById(String coupon_id) {
		return couponDAO.selectById(coupon_id);
	}
	
	// �������
	public List<CouponDTO> selectAll() {
		return couponDAO.selectAll();
	}
	
//	public int couponInsert(CouponDTO coupon) {
//		return couponDAO.couponInsert(coupon);
//	}
	
//	public int couponUpdate(CouponDTO coupon) {
//		return couponDAO.couponUpdate(coupon);
//	}
	
	// �������
	public int couponUse(String coupon_id) {
		return couponDAO.couponUse(coupon_id);
	}
}