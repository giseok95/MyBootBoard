package kr.gi.mybootboard.member.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gi.mybootboard.member.entity.Member;
import kr.gi.mybootboard.member.repository.MemberRepository;
import kr.gi.mybootboard.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if (findMember.isPresent()) { // findMember!==null
			return findMember.get();
		} else {
			return null;
		}
	}

}
