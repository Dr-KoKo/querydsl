package pe.goblin.querydsl.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import pe.goblin.querydsl.entity.Member;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
	@Autowired
	EntityManager em;
	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Test
	public void basicTest() {
		Member newMember = new Member("member1", 10);
		memberJpaRepository.save(newMember);

		Member findMember = memberJpaRepository.findById(newMember.getId()).get();
		Assertions.assertThat(findMember).isEqualTo(newMember);

		List<Member> result1 = memberJpaRepository.findAll();
		Assertions.assertThat(result1).containsExactly(newMember);

		List<Member> result2 = memberJpaRepository.findByUsername("member1");
		Assertions.assertThat(result2).containsExactly(newMember);
	}

	@Test
	public void basicTest_Querydsl() {
		Member newMember = new Member("member1", 10);
		memberJpaRepository.save(newMember);

		Member findMember = memberJpaRepository.findById(newMember.getId()).get();
		Assertions.assertThat(findMember).isEqualTo(newMember);

		List<Member> result1 = memberJpaRepository.findAll_Querydsl();
		Assertions.assertThat(result1).containsExactly(newMember);

		List<Member> result2 = memberJpaRepository.findByUsername_Querydsl("member1");
		Assertions.assertThat(result2).containsExactly(newMember);
	}
}