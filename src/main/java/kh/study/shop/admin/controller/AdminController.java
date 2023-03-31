package kh.study.shop.admin.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import kh.study.shop.admin.service.AdminService;
import kh.study.shop.config.ShopDateUtil;
import kh.study.shop.config.UploadFileUtil;
import kh.study.shop.item.vo.CategoryVO;
import kh.study.shop.item.vo.ItemVO;
import kh.study.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name = "adminService")
	private AdminService adminService;
	
	//모드 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	//상품 등록 페이지
	@GetMapping("/regItem")
	public String regItem(ItemVO itemVO, Model model) {
		//전체 카테고리 목록 조회
		model.addAttribute("categoryListAll", adminService.selectCategoryListAll());

		//사용중인 카테고리 목록 조회
		model.addAttribute("categoryListInUse", adminService.selectCategoryListInUse());
		
		return "content/admin/reg_item";
	}
	
	//카테고리 등록
	@PostMapping("/regCategory")
	public String regCategory(CategoryVO categoryVO) {
		adminService.insertCategory(categoryVO);
		
		return "redirect:/admin/regItem";
	}
	
	@ResponseBody
	@PostMapping("/changeStatus")
	public void changeStatus(CategoryVO categoryVO) {
		adminService.updateStatus(categoryVO);
	}
	
	//상품등록
	//일반적인 데이터는 커맨드 객체로 전달 받는다.
	//첨부파일 데이터는 MultipartFile 객체를 통해 전달받아야 함.
	@PostMapping("/regItem")
	public String regItemProcess(ItemVO itemVO
								, MultipartFile mainImg
								, List<MultipartFile> subImgs) {
		
		//단일 이미지 파일 첨부 - 메인이미지
		UploadFileUtil.uploadFile(mainImg);
		
		//다중 이미지 파일 첨부 - 서브이미지
		UploadFileUtil.multiUploadFile(subImgs);
		
		
		adminService.regItem(itemVO);
		
		return "redirect:/admin/regItem";
	}
	
	@ResponseBody
	@PostMapping("/selectCategoryListInUseAjax")
	public List<CategoryVO> selectCategoryListInUseAjax() {
		List<CategoryVO> cateList = adminService.selectCategoryListInUse();
		
		return cateList;
	}
	
	@GetMapping("/memberManage")
	public String memberManage(Model model) {
		model.addAttribute("memberList", adminService.selectMemberListForAdmin());
	
		model.addAttribute("menu", "3");
		
		return "content/admin/member_manager";
	}
	
	@ResponseBody
	@PostMapping("/getMemberDetail")
	public MemberVO getMemberDetail(String memberId) {
		return adminService.getMemberDetail(memberId);
	}
	
	//상품관리 페이지로 이동
	@RequestMapping("/itemManage")
	public String itemManage(@RequestParam Map<String, String> paramMap, Model model) {
		System.out.println("상품명 : " + paramMap.get("itemName"));
		System.out.println("카테고리코드 : " + paramMap.get("cateCode"));
		System.out.println("재고 : " + paramMap.get("itemStock"));
		System.out.println("fromDate : " + paramMap.get("fromDate"));
		System.out.println("toDate : " + paramMap.get("toDate"));
		System.out.println("상태 : " + paramMap.get("itemStatus"));
		
		model.addAttribute("itemList", adminService.getItemListForAdmin(paramMap));
		model.addAttribute("categoryList", adminService.selectCategoryListAll());
		
		//현재 날짜
		String nowDate = ShopDateUtil.getNowDateToString("-");//2020-10-10
		//한달 전날짜
		String beforeDate = ShopDateUtil.getBeforeMonthDateToString();
		
		//넘어오는 fromDate가 없다면 한달 전 날짜로 세팅
		if(paramMap.get("fromDate") == null) {
			paramMap.put("fromDate", beforeDate);
		}
		if(paramMap.get("toDate") == null){
			paramMap.put("toDate", nowDate);
		}
		
		model.addAttribute("paramMap", paramMap);
		
		return "content/admin/item_manage";
	}
	
	//상품목록에서 수량변경 클릭 시 진행
	@ResponseBody
	@PostMapping("/updateStock")
	public void updateStock(ItemVO itemVO) {
		adminService.updateStock(itemVO);
	}
	
	
	
}












