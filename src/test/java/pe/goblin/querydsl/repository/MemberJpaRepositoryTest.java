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

	@Test
	public void searchTest_Builder() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);

		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);

		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setAgeGoe(35);
		condition.setAgeLoe(40);
		condition.setTeamName("teamB");

		List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
		Assertions.assertThat(result)
			.extracting("username")
			.containsExactly("member4");
	}

	@Test
	public void searchTest() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);

		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);

		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setAgeGoe(35);
		condition.setAgeLoe(40);
		condition.setTeamName("teamB");

		List<MemberTeamDto> result = memberJpaRepository.search(condition);
		Assertions.assertThat(result)
			.extracting("username")
			.containsExactly("member4");
	}
}