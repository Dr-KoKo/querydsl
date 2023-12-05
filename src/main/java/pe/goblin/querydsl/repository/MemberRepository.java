package pe.goblin.querydsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import pe.goblin.querydsl.entity.Member;

public interface MemberRepository
	extends JpaRepository<Member, Long>, MemberCustomRepository, QuerydslPredicateExecutor<Member> {
	List<Member> findByUsername(String username);
}
