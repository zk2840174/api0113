package com.example.api02.interceptor;

import com.example.api02.annotations.JWTAuth;
import com.example.api02.util.JWTUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@Log4j2
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("-------------------TokenInterceptor---------------------");


        try{

            HandlerMethod method = (HandlerMethod) handler;

            JWTAuth jwtAuth = method.getMethodAnnotation(JWTAuth.class);

            if(jwtAuth != null){

                //Authorization
                String authHeader = request.getHeader("Authorization");

                if(authHeader == null){
                    throw new Exception("No Access Token");
                }

                log.info("===============================================");

                log.info(authHeader);

                Map<String, Object> values = jwtUtil.validateToken(authHeader.substring(7));


            }


        }catch(Exception e){


            String msg = e.getMessage();

            Class exclass = e.getClass();

            if(exclass == ExpiredJwtException.class){

                log.error("ExpiredJwtException...............");

                makeError("ExpiredJwt", response);

                return false;
            }

            log.error("ERROR>......................" + e.getClass());
            log.error(msg);

            if(msg.equals("No Access Token")){
            }

        }




        return true;
    }


    private void makeError(String msg, HttpServletResponse response){

        Gson gson = new Gson();

        String jsonStr = gson.toJson(Map.of("MSG", msg ));


        response.setStatus(403);

        response.setContentType("application/json");

        PrintWriter writer = null;
        try {


            writer = response.getWriter();
            writer.println(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }

}



