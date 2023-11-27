package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
    private MemberRepository memberRepository;  // memberRepository 객체 선언

    // Write
    @GetMapping("/members/new")
    public String newMembersForm(){
        return "members/new";
    }

    // Insert
    @PostMapping("/join") // post방식 데이터 전송 맵핑
    public String createMemberForm(MemberForm form){
        log.info(form.toString());

        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    // VIEW
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id);

        // 1. id 조회해서 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        // 뷰페이지 반환
        return "members/show";
    }

    // LIST
    @GetMapping("/members")
    public String index(Model model){
        // 1. DB에서 모든 데이터 가져오기
        List<Member> memberEntityList = memberRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);
        // 3. 사용자에게 보여줄 뷰 페이지 반환하기
        return "members/index";
    }

    // EDIT VIEW
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    // UPDATE
    @PostMapping("/members/update")
    public String update(MemberForm form){

        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            memberRepository.save(memberEntity);
        }

        return "redirect:/members/" + memberEntity.getId();
    }

    // DELETE
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Member target = memberRepository.findById(id).orElse(null);
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        return "redirect:/members";
    }

}
