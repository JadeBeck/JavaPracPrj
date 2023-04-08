package smartnurse.smartnursepracpjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import smartnurse.smartnursepracpjt.domain.Member;
import smartnurse.smartnursepracpjt.service.MemberService;

import java.util.List;

@Controller
//컨트롤 애노테이션 붙이고 MemberControlelr 클래스 칸을 만들면 ➡ 스프링 컨테이너가 처음에 뜰 때(만들어질 때) 스프링이 MemberController 객체를 생성해서 넣어두고 직접 관리를 함.
//➡'스프링 컨테이너에서 스프링 빈이 관리된다'고 표현함
//스프링은.. 서비스나 레포처럼 따로 Bean 등록해주 지 않음. 그러므로 어떻게 임의로 설정 못하는거고, @Autowired 삭제 노노)
public class MemberController {

    @Autowired
    //위에서 처음에 스프링 컨테이너가 뜨면서 코드 쫙 훑어서 애노테이션이 붙은 객체들은 컨테이너에다가 넣어준다고 했져. 이때..
    //생성자에 Autowired 애노테이션이 붙어있으면 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜줘버림~!~!!~!~~!
    //근데.. memberService class 자체는 애노테이션이고 뭐고 아무것도 업는 plain java code거덩. 글서,,, public class MemberService 위에 @Service 애노테이션 붙여야댐.
    //똑같이,, MemoryMemberRepository도 구현체 가서 public class MemoryMemberRepository implements MemberRepository { 위에 @Repository 붙여줌
    //이렇게 애노테이션을 붙여놓으면 스프링 컨테이너가 뜨면서 코드 쫙 훑고 거기다가 객체들을 넣어둡니당~
    //위의 과정처럼 애노테이션을 붙여주는걸 '스프링 컨테이너에 스프링 빈으로 등록한다'라고 합니동. 글고!!!! 이게 바로 Dependency Injection⭐️🌙💛🍋⭐️
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //이렇게 하면 SpringConfig에서 내가 직접 등록한 memberService를 넣어줌

    private final MemberService memberService; // 뒤에 = new MemberService();로 memberService 객체를 새로 만드는게 아니라(memberService class 자체가 별 로직 없는데 .. 여러개 만들 필요가 없음) 이렇게 스프링컨테이너에 등록(딱 하나만 등록됨)해놓고 꺼내씀. 글고 생성자 만듬(~this.memberService = memberService)

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);  //쪼인 로직 타고 들어가보셈. 이전에 미리 만들었던거임.

        return "redirect:/";

    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
