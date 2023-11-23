package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
    private MemberRepository memberRepository;  // memberRepository 객체 선언
    @GetMapping("/members/new")
    public String newMembersForm(){
        return "members/new";
    }

    @PostMapping("/join") // post방식 데이터 전송 맵핑
    public String createMemberForm(MemberForm form){
        System.out.println(form.toString());

        Member member = form.toEntity();
        System.out.println(member.toString());

        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());

        return "";
    }
}
