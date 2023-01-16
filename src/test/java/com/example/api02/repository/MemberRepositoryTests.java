package com.example.api02.repository;


import com.example.api02.domain.member.Member;
import com.example.api02.domain.member.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInserts() {

        Member member = Member.builder()
                .mid("user")
                .mpw("1111")
                .build();

        member.addRole(MemberRole.USER);
        member.addRole(MemberRole.MANAGER);
        member.addRole(MemberRole.ADMIN);

        memberRepository.save(member);
    }

    @Test
    public void testLogin() {

        Member member = memberRepository.login("user","1111");

        log.info(member);

    }

}
