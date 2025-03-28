package project.jennie.nexoracrm.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.jennie.nexoracrm.domain.Member;
import project.jennie.nexoracrm.service.AccService;
import project.jennie.nexoracrm.service.ContactService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/Contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService conService;
    @Value("${board.page-size}")
    private int pageSize;

    @GetMapping("/view")
    public String view(Model m, String id) {

        m.addAttribute("ldOne", accService.readOndAcc(id));

        return "views/acc/view";
    }

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg,
                       HttpSession session) {

        if (session.getAttribute("loginUser")== null) {
            log.info(" ");
        }

        Member loginUser=  (Member)session.getAttribute("loginUser");
        String userid = loginUser.getUserid();
        String managedId = loginUser.getManagedId();
        String role = loginUser.getRole();

        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
        log.info("con/list 호출 !! ");

        m.addAttribute("consdto", conService.readCon(cpg,userid,managedId,role));
        m.addAttribute("totalCount", conService.countCon(userid, managedId,role));

        return "views/acc/list";

    }

}
