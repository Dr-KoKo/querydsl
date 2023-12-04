package pe.goblin.querydsl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.goblin.querydsl.dto.MemberSearchCondition;
import pe.goblin.querydsl.dto.MemberTeamDto;
import pe.goblin.querydsl.repository.MemberJpaRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberJpaRepository memberJpaRepository;

	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
		return memberJpaRepository.search(condition);
	}
}
