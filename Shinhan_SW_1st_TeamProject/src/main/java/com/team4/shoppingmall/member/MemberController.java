package com.team4.shoppingmall.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member_test")
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    private GmailService gmailService;
    
    private Map<String, String> emailVerificationCodes = new HashMap<>();

	@GetMapping("/member_test.do")
	public void detailTest(Model model, String member_id) {
		model.addAttribute("memberVO", memberService.selectById(member_id));
	}

	@GetMapping("/member_button.do")
	public void defaultpage() {

	}
	//�α���
	@GetMapping("/login.do")
	public String loginstart() {
		return "user/login";
	}
	
	@PostMapping("/login.do")
	public String login(@RequestParam("member_id") String member_id, @RequestParam("member_pw") String member_pw, HttpSession session, HttpServletRequest request) {
		MemberDTO member = memberService.loginChk(member_id);
		if(member == null) {
			session.setAttribute("loginResult", "�������� �ʴ� ID");
			return "redirect:login.do";
		}else if(!member.getMember_pw().equals(member_pw)) {
			session.setAttribute("loginResult", "password ����");
			return "redirect:login.do";
		}else {
			session.setAttribute("loginResult", "�α��� ����");
			session.setAttribute("member", member);
		}
		return "redirect:/customer/myPage.do";
	}
	
    // �α׾ƿ�
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate(); // ���� ��ȿȭ
        return "redirect:/"; // �α׾ƿ� �� ���� �������� ���𷺼�
    }
	
	//ȸ������
	@GetMapping("/signup.do")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup.do")
	public String sendSignup(MemberDTO member, Model model) {
		System.out.println(member);
		if(member.getMember_type().equals(2)) {
			model.addAttribute("model", model);
			return "redirect:/";
			//�ӽ÷� �� ��ġ�� ������ ������ Ȯ���������� �̵��Ǿ�� �Ѵ�.
		}
		memberService.memberInsert(member);
		return "redirect:login.do";
	}
	
	//IDã��
	@GetMapping("/findid")
	public String findid() {
		return "user/findid";
	}
	
	@PostMapping("/findid")
	@ResponseBody
	public MemberDTO findidresult(@RequestParam("name") String name, @RequestParam("phone") String phone, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberDTO member = memberService.findId(name, phone);
		System.out.println(member);
		if(!member.member_name.equals(name)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "�߸��� ��û�Դϴ�.");
		}
		System.out.println("����");
		return member;
	}
	
	//���ã��
	@GetMapping("/findpassword")
	public String findpassword() {
		return "user/findpassword";
	}
	
	@PostMapping("/findpassword")
	@ResponseBody
	public MemberDTO findpasswordresult(@RequestParam("userId") String userId) {
		MemberDTO member = memberService.selectById(userId);
		System.out.println(member);
		return member;
	}

	//�̸��� ����
    @GetMapping("/verify")
    public String showVerificationForm() {
        return "verify";
    }

    @PostMapping("/verify")
    public String sendVerificationCode(@RequestParam("name") String name,
                                       @RequestParam("email") String email, Model model) {
        String code = generateVerificationCode();
        try {
            gmailService.sendEmail(email, "Verification Code", "Your verification code is " + code);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("verificationCode", code);
            return "confirm";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    @GetMapping("/verifypassword")
    public String verifypassword(@RequestParam("userId") String userId, Model model) {
        // userId�� ����Ͽ� �ʿ��� ������ ó���մϴ�.
        // ���� ���, �����ͺ��̽����� ����� ������ ��ȸ�� �� �ֽ��ϴ�.
    	MemberDTO member = memberService.selectById(userId);
    	
    	//�������� Ȯ�ι��� �߼�
    	String code = generateVerificationCode();
        gmailService.sendEmail(member.email, "Verification Code", "Your verification code is " + code);
    	System.out.println(userId);
    	System.out.println(member.email);
    	System.out.println(code);
        
        // model�� userId�� �߰��Ͽ� view�� �����մϴ�.
        model.addAttribute("userId", userId);
        model.addAttribute("email", member.email);
        model.addAttribute("verificationCode", code);
        
        // ��ȯ�� view �̸��� �����մϴ�.
        return "user/findpassword_check";
    }
    
    @PostMapping("/resetpassword")
    @ResponseBody
    public boolean resetPassword(@RequestParam("userId") String userId, @RequestParam("newPassword") String newPassword) {
        try {
        	MemberDTO member = memberService.selectById(userId);
        	member.member_pw = newPassword;
            memberService.updatePassword(member);
            System.out.println("������� ����");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @PostMapping("/sendEmailVerification")
    @ResponseBody
    public Map<String, Object> sendEmailVerification(@RequestParam("email") String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            String code = generateVerificationCode();
            gmailService.sendEmail(email, "Email Verification", "Your verification code is " + code);
            emailVerificationCodes.put(email, code);
            response.put("success", true);
            response.put("verificationCode", code); // ����� �������� ��ȯ
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        }
        return response;
    }
    
    //������ȣ�� �޴� �ڵ�
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

	@GetMapping("/phone_check.do")
	public void check() {

	}
}
