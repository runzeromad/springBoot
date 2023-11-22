package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 선언(annotaion)
public class SecondController {
    @GetMapping("/random-quote") // URL을 맵핑함(http://localhost:8080/random-quote)
    public String randomQuote(Model model){ // 모델객체 받아오기
        String[] quotes ={
                "행복은 습관이다. 그것을 몸에 지니라. " +
                        "-허버드-",
                "고개 숙이지 마십시오. 세상을 똑바로 정면으로 " +
                        "바라보십시오. -헬렌 켈러-",
                "고난의 시기에 동요하지 않는 것, 이것은 진정 " +
                        "칭찬 받을 만한 뛰어난 인물의 증거다. -베토벤-",
                "당신이 할 수 있다고 믿든 할 수 없다고 믿든 " +
                        "믿는 대로 될것이다. -헨리 포드-",
                "작은 기회로부터 종종 위대한 업적이 시작된다. " +
                        "-데모스테네스-"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote",quotes[randInt]); // randomQuote : 변수명(attributeName)
        return "quote"; // quote.mustache 파일을 반환함
    }
}
