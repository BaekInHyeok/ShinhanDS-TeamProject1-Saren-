package com.team4.shoppingmall.seller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/seller")
public class SellerQAController {

	//�����ڿ��� ���� ���
	@PostMapping("/registerStoAquestion")
	public void registerStoAquestion(
			@RequestParam("sellerID") String sellerID,
			@RequestParam("StoAquestionTitle") String StoAqTitle,
			@RequestParam("StoAquestion") String StoAq,
			RedirectAttributes redirectAttributes
			) throws UnsupportedEncodingException {
		
		//�ѱ� ���� �ذ�
		String sellerid = new String(sellerID.getBytes("8859_1"),"utf-8");
		String sToAqTitle = new String(StoAqTitle.getBytes("8859_1"),"utf-8");
		String sToAq = new String(StoAq.getBytes("8859_1"),"utf-8");

		//���ε� ��¥
		Date registeredDate = new Date();
		
		//����ID ����
		
		//���Ŀ� SQL������ DB�� ���
	}
	
	//������ ���ǿ� �亯
	@PostMapping("/answerCquestion")
	public void answerCquestion(
			@RequestParam("CtoSquestionID") int CtoSquestionID,
			@RequestParam("CtoSquestionTitle") String CtoSquestionTitle,
			@RequestParam("CtoSquestion") String CtoSquestion,
			@RequestParam("CtoSquestionAnswer") String CtoSquestionAnswer,
			RedirectAttributes redirectAttributes
			) throws UnsupportedEncodingException {
		//�ѱ� ���� �ذ�
		String cTosQtitle = new String(CtoSquestionTitle.getBytes("8859_1"),"utf-8");
		String cTosQ = new String(CtoSquestion.getBytes("8859_1"),"utf-8");
		String cTosQanswer = new String(CtoSquestionAnswer.getBytes("8859_1"),"utf-8");
		
		//���Ŀ� SQL������ DB�� ���
		
	}
}
