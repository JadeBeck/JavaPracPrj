package smartnurse.smartnursepracpjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  //로컬8080 요청 오면, home화면에 매칭된게 있으면 그 컨트롤러 호출하고 끝! 기존에 index.html은 무시됨~!(priority가 존재)
    public String home() {
        return "home";
    }
}
