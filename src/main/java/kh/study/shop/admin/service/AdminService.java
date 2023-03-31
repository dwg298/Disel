package kh.study.shop.admin.service;

import java.util.List;
import java.util.Map;

import kh.study.shop.item.vo.CategoryVO;
import kh.study.shop.item.vo.ItemVO;
import kh.study.shop.member.vo.MemberVO;

public interface AdminService {
	List<CategoryVO> selectCategoryListAll();
	List<CategoryVO> selectCategoryListInUse();
	void insertCategory(CategoryVO categoryVO);
	void updateStatus(CategoryVO categoryVO);
	void regItem(ItemVO itemVO);
	List<MemberVO> selectMemberListForAdmin();
	MemberVO getMemberDetail(String memberId);
	List<ItemVO> getItemListForAdmin(Map<String, String> map);
	void updateStock(ItemVO itemVO);
}



















