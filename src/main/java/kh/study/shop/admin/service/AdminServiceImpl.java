package kh.study.shop.admin.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.shop.item.vo.CategoryVO;
import kh.study.shop.item.vo.ItemVO;
import kh.study.shop.member.vo.MemberVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> selectCategoryListAll() {
		return sqlSession.selectList("adminMapper.selectCategoryListAll");
	}
	
	@Override
	public List<CategoryVO> selectCategoryListInUse() {
		return sqlSession.selectList("adminMapper.selectCategoryListInUse");
	}

	@Override
	public void insertCategory(CategoryVO categoryVO) {
		sqlSession.insert("adminMapper.insertCategory", categoryVO);
	}

	@Override
	public void updateStatus(CategoryVO categoryVO) {
		sqlSession.update("adminMapper.updateStatus", categoryVO);
	}

	@Override
	public void regItem(ItemVO itemVO) {
		sqlSession.insert("adminMapper.regItem", itemVO);
	}

	@Override
	public List<MemberVO> selectMemberListForAdmin() {
		return sqlSession.selectList("adminMapper.selectMemberListForAdmin");
	}

	@Override
	public MemberVO getMemberDetail(String memberId) {
		return sqlSession.selectOne("adminMapper.getMemberDetail", memberId);
	}

	@Override
	public List<ItemVO> getItemListForAdmin(Map<String, String> map) {
		return sqlSession.selectList("adminMapper.getItemListForAdmin", map);
	}

	@Override
	public void updateStock(ItemVO itemVO) {
		sqlSession.update("adminMapper.updateStock", itemVO);
	}
	
	
	
	
	
	
}








