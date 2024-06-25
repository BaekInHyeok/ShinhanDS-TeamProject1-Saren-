package com.team4.shoppingmall.seller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team4.shoppingmall.prod.ProdDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.prod_image.Prod_ImageDTO;
import com.team4.shoppingmall.prod_image.Prod_ImageService;
import com.team4.shoppingmall.prod_option.Prod_OptionDTO;
import com.team4.shoppingmall.prod_option.Prod_OptionService;

import retrofit2.http.Multipart;

@Controller
@RequestMapping("/seller")
public class SellerPrdUploadController {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	ProdService prodService;
	
	@Autowired
	Prod_ImageService imageService;
	
	@Autowired
	Prod_OptionService optionService;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping("/uploadPrd")
	public String handleFileUpload(@RequestParam("prdType") String prdType, @RequestParam("prdName") String prdName,
			@RequestParam("prdPrice") int prdPrice, @RequestParam("prdCategory") int prdCategory,
			@RequestParam("file") List<MultipartFile> files, @RequestParam("prdDescription") String prdDescription,
			@RequestParam("optName") List<String> optNames, @RequestParam("optValue") List<String> optValues,
			@RequestParam("prdStock") int prdStock, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {

		String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ) >> �̰� �α��� ����� �������� ����Ǹ� Session���� ������ ����

		// �ѱ� ������ ���� �ذ�
		String productType = URLDecoder.decode(prdType, "UTF-8");
		String productName = URLDecoder.decode(prdName, "UTF-8");
		// String productCategory = URLDecoder.decode(prdCategory,"UTF-8");
		String productDescription = URLDecoder.decode(prdDescription, "UTF-8");

		System.out.println(productType);
		System.out.println(productName);
		// System.out.println(productCategory);
		System.out.println(productDescription);

		List<String> fileUrls = new ArrayList<>();// �������� URL���� ������ ����Ʈ

		/*----------������ʹ� ��ǰ �����͸� DB�� �����ϴ� ������ ����----------*/

		// ���ε� ��¥
		// ���� ��¥�� LocalDate�� ������
		LocalDate localDate = LocalDate.now();

		// LocalDate�� java.sql.Date�� ��ȯ
		Date sqlDate = Date.valueOf(localDate);

		// 1.��ǰ(Product) ���̺� ���� : ���� ��ǰ ID ���� ���� Ȯ�� �ʿ�
		String prod_id = productName + "_" + member_id; // ��ǰID : ��ǰ��_�Ǹ���ID

		System.out.println("��ǰID : " + prod_id);

		// PROD���� ��ǰ ID�� �˻��Ͽ� ������ ���� �����ϴ��� �켱 Ȯ��
		// if(�������� ������) ��ǰ ��� & ���� ��� �� ��� ���
		// else(�����ϸ�) => ���(Stock) �����͸� ����� ��
		ProdDTO prodDTO = prodService.selectByProdId(prod_id);

		if (Objects.isNull(prodDTO)) {
			// ��ǰ ���
			prodDTO.setProd_id(prod_id);
			prodDTO.setProd_name(productName);
			prodDTO.setProd_desc(prdDescription);
			prodDTO.setProd_price(prdPrice);
			prodDTO.setProd_added_date(sqlDate);
			prodDTO.setCategory_id(prdCategory);
			prodDTO.setMember_id(member_id);

			int prdRegResult = prodService.prodInsert(prodDTO);

			//��ǰ ���̺� ��� ����
			
			// 2.��ǰ �̹���(PROD_IMAGE) ���̺� ������ ����
			int fileIndex = 1; // ���� �ε����� 1�� �ʱ�ȭ
			
			for (MultipartFile file : files) {
				// ���� Ÿ�� üũ
				String contentType = file.getContentType();
				System.out.println(contentType);
				if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "�̹��� ������ �ƴմϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}

				// ���� ó��(URL�� ��ȯ)
				try {
					// ���ϸ��� '��ǰ��_�Ǹ���ID_image_x'(x�� sequence)
					String filename = prod_id + "_image_" + fileIndex;
					Path filePath = Paths.get(uploadDir).resolve(filename);
					Files.createDirectories(filePath.getParent()); // ���丮�� �������� ������ ����
					Files.write(filePath, file.getBytes()); // ���� ����

					// ���� URL ���� >> ����!!) �ش� ����URL�� ���� �������� ��� ���� ���������� ��ȿ��
					String fileUrl = "/images/" + filename;
					System.out.println("�̹�������URL : " + fileUrl);
					fileUrls.add(fileUrl);
					
					//DB�� ����
					
					Prod_ImageDTO imageDTO = new Prod_ImageDTO();
					
					imageDTO.setImg_id(filename);
					imageDTO.setProd_id(prod_id);
					
					int prdImgRegResult = imageService.prod_imageInsert(imageDTO);
					
					fileIndex++;
					
				} catch (IOException e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}
			}
		}
		
		// 3.���(Stock) ���̺� : ��� ���̺� ����� ��ǰID, 
		
		// �ɼǸ� & �ɼǰ� ó��
		List<Map<String, String>> options = new ArrayList<>();//�ɼǸ���Ʈ, �ٵ� �̰� �ʿ����
		
		for (int i = 0; i < optNames.size(); i++) {
			Map<String, String> option = new HashMap<String, String>();
			option.put("optName", new String(optNames.get(i).getBytes("8859_1"), "utf-8"));
			option.put("optValue", new String(optValues.get(i).getBytes("8859_1"), "utf-8"));

			String optionName =  URLDecoder.decode(optNames.get(i), "UTF-8");
			String optionValue =  URLDecoder.decode(optValues.get(i), "UTF-8");
			
			//���� DB���� ��ǰ ID ���� ���� ū �ɼ� ID�� �����´�
			int maxOptionID = optionService.findMaxOptId(prod_id);
			maxOptionID++;
			
			Prod_OptionDTO optionDTO = new Prod_OptionDTO();
			optionDTO.setOpt_id(maxOptionID);
			optionDTO.setOpt_name(optionName);
			optionDTO.setOpt_value(optionValue);
			optionDTO.setProd_id(prod_id);
			
			int optRegResult = optionService.optionInsert(optionDTO);
			
			System.out.println(option);
			options.add(option);
		}

		

		redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
		return "/seller/sellerPrdList";// ���� �������� �����̷���(��������Ʈ������ �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
	}

}
