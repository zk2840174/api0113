package com.example.api02.repository;

import com.example.api02.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {


    @Query("select  m from Member  m where m.mid = :mid and m.mpw = :mpw")
    Member login(@Param("mid") String mid, @Param("mpw") String mpw);

}
