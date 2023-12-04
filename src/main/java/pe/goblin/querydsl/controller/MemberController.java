package pe.goblin.querydsl.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.goblin.querydsl.dto.MemberSearchCondition;
import pe.goblin.querydsl.dto.MemberTeamDto;
import pe.goblin.querydsl.repository.MemberJpaRepository;
import pe.goblin.querydsl.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberJpaRepository memberJpaRepository;
	private final MemberRepository memberRepository;

	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
		return memberJpaRepository.search(condition);
	}

	@GetMapping("/v2/members")
	public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition, Pageable pageable) {
		return memberRepository.searchPageSimple(condition, pageable);
	}

	@GetMapping("/v3/members")
	public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition, Pageable pageable) {
		return memberRepository.searchPageComplex(condition, pageable);
	}
}
