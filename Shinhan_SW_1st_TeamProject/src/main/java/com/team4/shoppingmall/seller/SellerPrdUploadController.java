package com.team4.shoppingmall.seller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String handleFileUpload(
			@RequestParam("prdName") String prdName,
			@RequestParam("prdPrice") int prdPrice,
			@RequestParam("prdCategory") String prdCategory,
			@RequestParam("file") List<MultipartFile> files,
			@RequestParam("prdDescription") String prdDescription,
			@RequestParam("optName") List<String> optNames,
            @RequestParam("optValue") List<String> optValues,
			@RequestParam("prdStock") int prdStock,
			RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		
		
		//�ѱ� ������ ���� �ذ�
		String productName = new String(prdName.getBytes("8859_1"),"utf-8");
		String productCategory = new String(prdCategory.getBytes("8859_1"),"utf-8");
		String productDescription = new String(prdDescription.getBytes("8859_1"),"utf-8");
		
		List<String> fileUrls = new ArrayList<>();//�������� URL���� ������ ����Ʈ
				
        for(MultipartFile file : files) {
        	// ���� Ÿ�� üũ
            String contentType = file.getContentType();
            System.out.println(contentType);
            if(!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            	redirectAttributes.addFlashAttribute("PrdRegisterResult", "�̹��� ������ �ƴմϴ�.");
            	return "/seller/sellerPrdList";//������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
            }
            
            //���� ó��(URL�� ��ȯ)
            try {
            	
            	//���� ���ϸ��� '��ǰ��_��Ͻð�_�̹������Ͽ����̸�'
            	//�ϴ� �̷��� �����α� �ߴµ� ���߿� '��ǰID_��ȣ'�� '���ID_��ȣ'�� ���Ŀ� �°� ��ĥ ����
            	String filename = new String(prdName.getBytes("8859_1"),"utf-8")+"_"+System.currentTimeMillis() + "_" + new String(file.getOriginalFilename().getBytes("8859_1"),"utf-8");//�ϴ� �̷��� �����α� �ߴµ� ���߿� ��ǰID�� ���ID�� ���Ŀ� �°� ��ĥ ����
            	Path filePath = Paths.get(uploadDir).resolve(filename);
            	Files.createDirectories(filePath.getParent()); // ���丮�� �������� ������ ����
                Files.write(filePath, file.getBytes()); // ���� ����
            	
                // ���� URL ����
                String fileUrl = "/images/" + filename;
                System.out.println("�̹�������URL : "+fileUrl);
                fileUrls.add(fileUrl);
                
            	
            }catch(IOException e) {
            	e.printStackTrace();
            	redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
            	return "/seller/sellerPrdList";//������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
            }
            
        }
        
        //�ɼǸ� & �ɼǰ� ó��
        List<Map<String,String>> options = new ArrayList<>();
        for(int i=0;i<optNames.size();i++) {
        	Map<String, String> option = new HashMap<String, String>();
        	option.put("optName", new String(optNames.get(i).getBytes("8859_1"),"utf-8"));
        	option.put("optValue", new String(optValues.get(i).getBytes("8859_1"),"utf-8"));
        	
        	System.out.println(option);
        	options.add(option);
        }

        //������ʹ� ��ǰ �����͸� DB�� �����ϴ� ������ ����
        
        
        redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
        return "/seller/sellerPrdList";//���� �������� �����̷���(��������Ʈ������ �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
	}
	
}
