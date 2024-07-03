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
	
	//ȸ���� ���� ���� ���
	public List<CouponDTO> selectCustomerCouponList(String member_id){
		return couponDAO.selectCustomerCouponList(member_id);
	}
	
	// �������
	public int couponUse(CouponDTO couponDTO) {
		return couponDAO.couponUse(couponDTO);
	}
}