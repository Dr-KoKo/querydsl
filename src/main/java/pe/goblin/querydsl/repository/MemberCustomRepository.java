package pe.goblin.querydsl.repository;

import java.util.List;

import pe.goblin.querydsl.dto.MemberSearchCondition;
import pe.goblin.querydsl.dto.MemberTeamDto;

public interface MemberCustomRepository {
	List<MemberTeamDto> search(MemberSearchCondition condition);
}
