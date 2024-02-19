package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TODO #1: Controller로 만드세요.
@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // TODO #2: `GET /login` 요청을 처리하세요.
    //          `SESSION` 이라는 쿠키가 있으면 로그인 완료 메세지 출력 (`loginSuccess.jsp`).
    //          `SESSION` 이라는 쿠키가 없으면 로그인 폼 화면 출력 (`loginForm.jsp`).
    @GetMapping("/login")
    public String login(@CookieValue(value = "SESSION", required = false) String session,
                        Model model) {
        if (session != null) {
            model.addAttribute("id", session);
            return "loginSuccess";
        } else return "loginForm";
    }

    // TODO #3: `POST /login` 요청을 처리하세요.
    //          `userRepository.matches(id, password)` 메서드 이용.
    //          로그인 성공 시 `SESSION` 쿠키에 session id 값 저장하고
    //          모델을 이용해서 `loginSuccess.jsp`에 세션 아이디 전달.
    //          로그인 실패 시 `/login`으로 redirect.
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
        if (userRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);

            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            model.addAttribute("id", session.getId());
            return "loginSuccess";
        } else {
            return "redirect:/login";
        }
    }

}
