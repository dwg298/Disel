package kh.study.shop.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.shop.config.MemberRole;
import kh.study.shop.config.MemberStatus;
import kh.study.shop.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public void join(MemberVO memberVO) {
		sqlsession.insert("memberMapper.join", memberVO);
	}

	@Override
	public MemberVO login(MemberVO memberVO) {
		return sqlsession.selectOne("memberMapper.login", memberVO);
	}

}













