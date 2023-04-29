package com.project.mlb.member.repository;

import com.project.mlb.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginIdValue(final String loginId);

    boolean existsByNickname(final String nickname);

    Optional<Member> findByLoginIdValueAndPasswordValue(String loginId, String password);

}
