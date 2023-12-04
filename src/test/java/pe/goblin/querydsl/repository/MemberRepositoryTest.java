package pe.goblin.querydsl.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import pe.goblin.querydsl.dto.MemberSearchCondition;
import pe.goblin.querydsl.dto.MemberTeamDto;
import pe.goblin.querydsl.entity.Member;
import pe.goblin.querydsl.entity.Team;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
	@Autowired
	EntityManager em;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void basicTest() {
		Member newMember = new Member("member1", 10);
		memberRepository.save(newMember);

		Member findMember = memberRepository.findById(newMember.getId()).get();
		Assertions.assertThat(findMember).isEqualTo(newMember);

		List<Member> result1 = memberRepository.findAll();
		Assertions.assertThat(result1).containsExactly(newMember);

		List<Member> result2 = memberRepository.findByUsername("member1");
		Assertions.assertThat(result2).containsExactly(newMember);
	}
}