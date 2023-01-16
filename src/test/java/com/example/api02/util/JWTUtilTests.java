package com.example.api02.util;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


@SpringBootTest
@Log4j2
public class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testMake(){

        Map<String, Object > map = Map.of("AAAA","1111","BBBB","2222");

        log.info(jwtUtil.generateToken( map , 10));

    }

    @Test
    public void testValid() {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJBQUFBIjoiMTExMSIsIkJCQkIiOiIyMjIyIiwiaWF0IjoxNjczNzczOTc1LCJleHAiOjE2NzM3NzQyNzV9.n0YH95ZD4zhlZMP54C5OD7U4LZitkVfDUGiu2s5mhvQ";

        log.info(jwtUtil.validateToken(token));

    }
}
