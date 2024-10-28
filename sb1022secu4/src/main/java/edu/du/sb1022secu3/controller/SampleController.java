package edu.du.sb1022secu3.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sample/")
@Log4j2
public class SampleController {

    @GetMapping("/accessDenied")
    public void accessDenied() {}

    @GetMapping("/all")
    public void exAll(){
        log.info("exAll..........");
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin..........");
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("exMember..........");
    }

    @GetMapping("/login")
    public void login(String errorCode, String logout) {
        log.info("login 페이지..........");
        if (logout != null) {
            log.info("user logout..........");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 현재 인증 정보를 가져온다
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보가 존재할 경우 로그아웃을 수행한다
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/sample/login?logout";
    }

}
