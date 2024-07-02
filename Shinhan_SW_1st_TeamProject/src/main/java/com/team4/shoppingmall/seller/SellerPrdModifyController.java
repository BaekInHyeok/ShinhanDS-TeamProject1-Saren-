package com.team4.shoppingmall.seller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.velocity.runtime.resource.loader.URLResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team4.shoppingmall.admin_inq.Admin_InqService;
import com.team4.shoppingmall.buyer_inq.Buyer_InqService;
import com.team4.shoppingmall.member.MemberService;
import com.team4.shoppingmall.prod.ProdDTO;
import com.team4.shoppingmall.prod.ProdService;
import com.team4.shoppingmall.prod_image.Prod_ImageDTO;
import com.team4.shoppingmall.prod_image.Prod_ImageService;
import com.team4.shoppingmall.prod_option.Prod_OptionService;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;
import com.team4.shoppingmall.rent_prod_stock.RentProdStockService;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockDTO;
import com.team4.shoppingmall.seller_prod_stock.Seller_Prod_StockService;

@Controller
@RequestMapping("/seller")
public class SellerPrdModifyController {

	@Autowired
	Buyer_InqService buyer_inqService;

	@Autowired
	Admin_InqService admin_inqService;

	@Autowired
	MemberService memberService;

	@Autowired
	ProdService prodService;

	@Autowired
	Prod_OptionService optionService;

	@Autowired
	Prod_ImageService imageService;

	@Autowired
	Seller_Prod_StockService seller_Prod_StockService;

	@Autowired
	RentProdStockService rentProdStockService;

	String member_id = "573-50-00882";// �ӽ÷� ����� �Ǹ���ID(����ڵ�Ϲ�ȣ)

	// ��ǰ �̹��� ���� ���ε� ���丮
	// 1.���� �̹��� ����
	@Value("${file.main-img-upload-dir}")
	private String mainIMG_uploadDir;

	// 2.���� �̹��� ����
	@Value("${file.desc-img-upload-dir}")
	private String descIMG_uploadDir;

	@PostMapping("/resetMainProdImg")
	@ResponseBody
	public String resetMainProdImg(@RequestBody Map<String, Object> request) throws IOException {
		System.out.println("���� ���� ��� ���� �۾� ����");
		String prod_id = (String) request.get("prodid");
		System.out.println("��ǰID:" + prod_id);
		System.out.println(prod_id);

		List<String> imgIdList = imageService.findAllImgFileNameByProdID(prod_id);

		int imgDeleteResult = imageService.prod_imageDelete(prod_id);
		System.out.println("���� �Ϸ�");
		if (imgDeleteResult > 0) {
			for (String imgID : imgIdList) {

				Path filePath = Paths.get(mainIMG_uploadDir).resolve(imgID);
				Files.delete(filePath);

				System.out.println(imgID + "���� �Ϸ�");
			}
		}

		return "resetImgSuccess";
	}

	@PostMapping("/resetDescProdImg")
	@ResponseBody
	public String resetDescProdImg(@RequestBody Map<String, Object> request) throws IOException {
		System.out.println("���� ���� ��� ���� �۾� ����");
		String prod_id = (String) request.get("prodid");
		System.out.println("��ǰID:" + prod_id);
		// String prod_id = "����Ű%20����2_573-50-00882";
		System.out.println(prod_id);
		List<String> imgIdList = imageService.findAllImgFileNameByProdID(prod_id);

		int imgDeleteResult = imageService.prod_imageDelete(prod_id);
		System.out.println("���� �Ϸ�");
		if (imgDeleteResult > 0) {
			for (String imgID : imgIdList) {

				Path filePath = Paths.get(mainIMG_uploadDir).resolve(imgID);
				Files.delete(filePath);

				System.out.println(imgID + "���� �Ϸ�");
			}
		}

		return "resetImgSuccess";
	}

	@PostMapping("updateStockInfo")
	public String updateStockInfo(
			@RequestParam("prdId") String prdID, 
			@RequestParam("prdPrice") int prdPrice, 
			@RequestParam("prdCategory") int prdCategory,
			@RequestParam("mainImgFile") List<MultipartFile> mainFiles, 
			@RequestParam("descImgFile") List<MultipartFile> descFiles, 
			@RequestParam("prdDescription") String prdDescription,
			@RequestParam("stockid") String stockid,
			@RequestParam("prdStock") int prdStock,
			RedirectAttributes redirectAttributes
			) throws UnsupportedEncodingException {
		String member_id = "573-50-00882";//�Ǹ���ID �ӽ�, Session���� �޾ƿ� ����
		
		String prod_id=URLDecoder.decode(prdID, "UTF-8");//��ǰID
		String prd_desc=URLDecoder.decode(prdDescription,"UTF-8");//��ǰ����
		int prd_category = prdCategory;
		
		ProdDTO prodDTO = new ProdDTO();
		
		prodDTO.setProd_id(prod_id);
		prodDTO.setProd_desc(prd_desc);
		prodDTO.setCategory_id(prd_category);
		prodDTO.setProd_price(prdPrice);
		
		int prdUpdateResult = prodService.prodModify(prodDTO);
		
		// ��ǰ�̹��� ����� ���� �̹��� ��� ��� >> ���� �̹��� ��� ��� ������ ����
		int mainfileIndex = 1; // ���� �̹��� ���� �ε����� 1�� �ʱ�ȭ
		int descfileIndex = 1; // ���� �̹��� ���� �ε����� 1�� �ʱ�ȭ
		
		
		//���� ���� �ʱ�ȭ �� ����
		if(!Objects.isNull(mainFiles)) {
			for (MultipartFile mainfile : mainFiles) {
				// ���� Ÿ�� üũ
				String contentType = mainfile.getContentType();
				System.out.println(contentType);
				if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "�̹��� ������ �ƴմϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}

				//���� ��ǻ���� ���丮�� ���� ����
				
				//(1)�����̹��� ����
				try {
					// ���ϸ��� '��ǰ��_�Ǹ���ID_mainimage_x'(x�� sequence)
					String filename = prod_id + "_mainimage_" + mainfileIndex+".png";
					Path filePath = Paths.get(mainIMG_uploadDir).resolve(filename);
					Files.createDirectories(filePath.getParent()); // ���丮�� �������� ������ ����
					Files.write(filePath, mainfile.getBytes()); // ���� ����

					// DB�� ����
					Prod_ImageDTO imageDTO = new Prod_ImageDTO();

					imageDTO.setImg_id(filename);//��ǰ��_�Ǹ���ID_image_fileindex
					imageDTO.setProd_id(prod_id);//��ǰ_�Ǹ���ID
					
					System.out.println(imageDTO);

					int prdMainImgRegResult = imageService.prod_imageInsert(imageDTO);

					mainfileIndex++;

				} catch (IOException e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}
			}
		}
		
		if(!Objects.isNull(descFiles)) {
			for (MultipartFile descfile : descFiles) {
				// ���� Ÿ�� üũ
				String contentType = descfile.getContentType();
				System.out.println(contentType);
				if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "�̹��� ������ �ƴմϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}

				// ���� ��ǻ���� ���丮�� ���� ����
				try {
					// ���ϸ��� '��ǰ��_�Ǹ���ID_descimage_x'(x�� sequence)
					String filename = prod_id + "_descimage_" + descfileIndex+".png";
					Path filePath = Paths.get(descIMG_uploadDir).resolve(filename);
					Files.createDirectories(filePath.getParent()); // ���丮�� �������� ������ ����
					Files.write(filePath, descfile.getBytes()); // ���� ����

					// DB�� ����
					Prod_ImageDTO imageDTO = new Prod_ImageDTO();

					imageDTO.setImg_id(filename);//��ǰ��_�Ǹ���ID_image_fileindex
					imageDTO.setProd_id(prod_id);//��ǰ_�Ǹ���ID
					
					System.out.println(imageDTO);

					int prdDescImgRegResult = imageService.prod_imageInsert(imageDTO);

					descfileIndex++;

				} catch (IOException e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� ���ε忡 �����Ͽ����ϴ�.");
					return "/seller/sellerPrdList";// ������������ �����̷���(��������Ʈ������ alert���� ������ ���� �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ)
				}
			}
		}
		
		//���ID-��� ����
		String stock_id = URLDecoder.decode(prdID, "UTF-8");
		if(stock_id.contains("_SELL_")) {//�Ǹ������ ���
			Seller_Prod_StockDTO seller_Prod_StockDTO = new Seller_Prod_StockDTO();
			seller_Prod_StockDTO.setS_stock_id(stock_id);
			seller_Prod_StockDTO.setStock(prdStock);
			
			int changeSellStock = seller_Prod_StockService.sellStockUpdate(seller_Prod_StockDTO);
		
		}
		else if(stock_id.contains("_RENT_")) {//�뿩����� ���
			RentProdStockDTO rentProdStockDTO = new RentProdStockDTO();
			rentProdStockDTO.setR_stock_id(stock_id);
			rentProdStockDTO.setStock(prdStock);
			
			int changeRentStock = rentProdStockService.rentStockUpdate(rentProdStockDTO);
		}
		
		redirectAttributes.addFlashAttribute("PrdRegisterResult", "��ǰ ���� �ֽ�ȭ�� �����Ͽ����ϴ�.");
		return "/seller/sellerPrdList";// ���� �������� �����̷���(��������Ʈ������ �Ǹ���-��ǰ ����Ʈ�� �����̷�Ʈ) 
	}

}
