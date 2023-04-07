package smartnurse.smartnursepracpjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("hello")  // '/hello'로 들어오면 아래의 메써드 호출함.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";  //이러면.. 스프링부트는 resources/templates/hello.html을 찾아 렌더링..!! 노드랑은 다름ㄷㄷ
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);  //위에서 파람으로 받은 name이 model로 넘어감
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  //res body에 이 내용을 직접 넣어 주겠다는 것
    }

    @GetMapping("hello-api")
    @ResponseBody  //가 있으면 view template에서 html 파일을 찾는게 아니라 http 응답에 그대로 데이터를 넘김. 넘기려고 했더니.. 35줄ㄱㄱ
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);  //커맨드 쉬프트 엔터 ! 인텔리제이 자동완성
        return hello;  //data가 문자가 아니라 객체임... 브라우저에 그냥 바로 내릴 수 없음. 그럼? 객체가 오면!! JSON 방식으로 바꿔 내림.
    }

    static class Hello {
        private String name;  //얘는 private이라 외부에서 바로 못꺼냄. 글서 아래에서 게터세터(메서드)로 접근함 <=자바 빈 표준 규약/프로퍼티 접근방식

        //게터세터 등 generate 하는 단축키? 커맨드 N
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
