package com.team4.shoppingmall.seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import retrofit2.http.Multipart;

@Controller
@RequestMapping("/seller")
public class SellerPrdUploadController {

	private JdbcTemplate jdbcTemplate;
	
	@Value("${file.upload-dir}")
    private String uploadDir;
	
	@PostMapping("/uploadPrd")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
		// ���� Ÿ�� üũ
        String contentType = file.getContentType();
        System.out.println(contentType);
        
        if(!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
        	redirectAttributes.addFlashAttribute("PrdRegisterResult", "�̹��� ������ �ƴմϴ�.");
        	return "/seller/sellerPrdList";//������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
        }
		
        try {
        	String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        	Path filePath = Paths.get(uploadDir).resolve(filename);
        	Files.createDirectories(filePath.getParent()); // ���丮�� �������� ������ ����
            Files.write(filePath, file.getBytes()); // ���� ����
        	
            // ���� URL ����
            String fileUrl = "/images/" + filename;
            
            System.out.println("�̹�������URL : "+fileUrl);
        	
        }catch(IOException e) {
        	e.printStackTrace();
        	redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
        	return "/seller/sellerPrdList";//������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
        }
        
        redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
        return "/seller/sellerPrdList";//���� �������� �����̷���(��������Ʈ������ �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
	}
	
}
