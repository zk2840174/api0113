package com.example.api02.controller;


import com.example.api02.dto.member.LoginDTO;
import com.example.api02.dto.member.RefreshDTO;
import com.example.api02.service.MemberService;
import com.example.api02.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/member/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(originPatterns = "*")

public class MemberController {


    private final JWTUtil jwtUtil;

    private final MemberService memberService;


    @PostMapping("/generate")
    public Map<String, String> getKey( @RequestBody LoginDTO loginDTO){

        log.info("---------------------------------------");
        log.info(loginDTO);

        String accessKey = jwtUtil.generateToken(Map.of("aaa", 123), 10);
        String refreshKey = jwtUtil.generateToken(Map.of("aaa", 123), 100);

        log.info("access: "+ accessKey);
        log.info("refresh: " + refreshKey);

        return Map.of("access", accessKey, "refresh", refreshKey, "mid", loginDTO.getMid());

    }

    @PostMapping("/refresh")

    public Map<String, String> refreshKey(@RequestBody RefreshDTO refreshDTO){

        log.info("---------------------------------------");
        log.info(refreshDTO);



        String mid = refreshDTO.getMid();

        String accessKey = jwtUtil.generateToken(Map.of("aaa", 123), 10);
        String refreshKey = jwtUtil.generateToken(Map.of("aaa", 123), 100);

        log.info("access: "+ accessKey);
        log.info("refresh: " + refreshKey);
        return Map.of("access", accessKey, "refresh", refreshKey, "mid", refreshDTO.getMid());

    }


}
