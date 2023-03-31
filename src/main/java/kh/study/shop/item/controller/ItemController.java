package kh.study.shop.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.shop.item.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name = "itemService")
	private ItemService itemService;
	
	//상품 목록 페이지
	//boolean isLoginFail : 로그인 실패할때만 데이터를 true로 받아온다.
	@GetMapping("/list")
	public String itemList(boolean isLoginFail, Model model) {
		//로그인 성공, 실패 여부 데이터를 html에 전달
		model.addAttribute("isLoginFail", isLoginFail);
		
		//상품목록 조회
		model.addAttribute("itemList", itemService.selectItemList());
		
		return "content/item/item_list";
	}
	
	@GetMapping("/itemDetail")
	public String itemDetail() {
	return "content/item/item_detail";	
	}
	
}











