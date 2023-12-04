package pe.goblin.querydsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.goblin.querydsl.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByUsername(String username);
}
