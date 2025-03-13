package project.jennie.nexoracrm.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "views/user/login";
    }

    @GetMapping("/home")
    public String home() {
        return "views/home";
    }


}
