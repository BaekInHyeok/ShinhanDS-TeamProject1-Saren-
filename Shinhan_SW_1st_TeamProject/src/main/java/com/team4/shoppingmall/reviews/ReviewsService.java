package com.team4.shoppingmall.reviews;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
	
	@Autowired
	ReviewsDAOInterface reviewsDAO;
	
	//�ֹ��� ��ǰ�� ��������
	public List<Map<String,String>> selectAllProductReviewByProdId(String prod_id){
		return reviewsDAO.selectAllProductReviewByProdId(prod_id);
	};
	
	public ReviewsDTO selectById(Integer review_id) {
		return reviewsDAO.selectById(review_id);
	}
	
	public List<ReviewsDTO> selectAll() {
		return reviewsDAO.selectAll();
	}
	
	public int reviewsInsert(ReviewsDTO reviews) {
		return reviewsDAO.reviewsInsert(reviews);
	}
	
	public int reviewsUpdate(ReviewsDTO reviews) {
		return reviewsDAO.reviewsUpdate(reviews);
	}
	
	public int reviewsDelete(Integer review_id) {
		return reviewsDAO.reviewsDelete(review_id);
	}
	
	//�� ���� �ҷ����⸦ ���� memberId�� select�ϱ�
	public List<ReviewsDTO> selectBymemId(String member_id){
		return reviewsDAO.selectBymemId(member_id);
	}
}