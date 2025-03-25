package project.jennie.nexoracrm.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.jennie.nexoracrm.domain.Member;
import project.jennie.nexoracrm.domain.OpptyListDTO;
import project.jennie.nexoracrm.service.LeadService;
import project.jennie.nexoracrm.service.OpptyService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/oppty")
@RequiredArgsConstructor
public class OpptyController {

    private final OpptyService opptyService;

    @GetMapping("/view")
    public String view(Model m, String id) {

        m.addAttribute("op", opptyService.readOndOppty(id));

        return "views/oppty/view";
    }
    @PostMapping("/updatestts")
    public ResponseEntity<?> updatestts(String id, String status) {

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("상태변경 위한 인자 확인 : id={}, status={}", id, status);

        try {
            if (opptyService.updateLeadstts(id,status)) {
                response = ResponseEntity.ok().build();
            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/updateClosed")
    public ResponseEntity<?> updateClosed(String id, String status) {

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        try {
            if (opptyService.updateClosed(id)) {
                response = ResponseEntity.ok().build();

            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }
    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg,
                       HttpSession session) {

        if (session.getAttribute("loginUser")== null) {
            log.info("세션 없다!!!!!!!!" +
                    "!!!! acc 콘트롤러에!!!!!!!!!");
        }

        Member loginUser=  (Member)session.getAttribute("loginUser");
        String userid = loginUser.getUserid();
        String managedId = loginUser.getManagedId();
        String role = loginUser.getRole();

        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨

        OpptyListDTO dto = opptyService.readOpptys(cpg,userid,managedId,role);
        log.info("company name 확인 : ",dto.getOpptylist().get(1).getAccountName());

        m.addAttribute("oppsdto", opptyService.readOpptys(cpg,userid,managedId,role));
        m.addAttribute("totalCount", opptyService.countOpptys(userid, managedId,role));

        return "views/oppty/list";
    }

}
