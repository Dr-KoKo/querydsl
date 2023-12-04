package pe.goblin.querydsl.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.goblin.querydsl.dto.MemberSearchCondition;
import pe.goblin.querydsl.dto.MemberTeamDto;

public interface MemberCustomRepository {
	List<MemberTeamDto> search(MemberSearchCondition condition);

	Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);

	Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
