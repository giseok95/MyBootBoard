package kr.gi.mybootboard.member.repository;

import org.springframework.data.repository.CrudRepository;

import kr.gi.mybootboard.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
