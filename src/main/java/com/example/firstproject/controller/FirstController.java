package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 선언
public class FirstController { // 컨트롤러
    @GetMapping("/hi") // URL맵핑
    public String niceToMeetYou(Model model){ // 모델 추가( Model model 모델 객체 받아 오기 )
        model.addAttribute("username", "hongPark"); // 모델 변수 등록
        return "greetings"; // 뷰(템플릿)페이지 반환
    }

    @GetMapping("/bye")
    public String seeYouNextTime(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}
