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
        return "members/createMemberForm";  //이 파일 열어보면 위에 <form action="/members/new" method="post">라고 있는데, 이름 적고 등록 누르면 저 action url로 post 방식으로 넘어온다는 것. 즉 밑의 @PostMapping으로 넘어온다~!~! 그 후 create라는 메써드가 호출된다.
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {  //create라는 메써드가 호출되면, 값이 들어온다. MemberForm 파일 열어보면 private String name; 있는데 이 name에 내가 적은 name이 들어오는 것(setName을 통해서!! => 스프링이 setName라는 세터 호출해서 이름 등록해준당. 왜? private String name.. 프라이빗이라서 막 접근 못하고.. 세터 호출함.. 세터...세터...........🥺)
        Member member = new Member();
        member.setName(form.getName());  //위에서 setName으로 이름 등록했고, 여기서는 form에서 getName으로 이름 꺼내줌

        memberService.join(member);  //쪼인 로직 타고 들어가보셈. 이전에 미리 만들었던거임.

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);  //Members List 자체를 그냥 model에다가 담아서 뷰에 넘김
        return "members/memberList";
    }
}
