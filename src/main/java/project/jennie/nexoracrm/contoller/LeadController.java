package project.jennie.nexoracrm.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jennie.nexoracrm.domain.*;
import project.jennie.nexoracrm.service.LeadService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/lead")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;
    @Value("${board.page-size}")
    private int pageSize;

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg,
                       HttpServletResponse response, HttpSession session) {

        if (session.getAttribute("loginUser")== null) {
            log.info("세션 없다!!!!!!!!!!!! 콘트롤러에!!!!!!!!!");
        }

        Member loginUser=  (Member)session.getAttribute("loginUser");
        String userid = loginUser.getUserid();
        String managedId = loginUser.getManagedId();
        String role = loginUser.getRole();

        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
        log.info("lead/list 호출 !! ");

        m.addAttribute("ldsdto", leadService.readLead(cpg,userid,managedId,role));
        m.addAttribute("totalCount", leadService.countLead(userid, managedId,role));

        return "views/lead/list";
    }

    @GetMapping("/view")
    public String view(Model m, String id) {

        m.addAttribute("ld", leadService.readOndLead(id));


        return "views/lead/view";
    }

    @PostMapping("/updatestts")
    public ResponseEntity<?> updatestts(String id, String status) {

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("상태변경 위한 인자 확인 : id={}, status={}", id, status);

        try {
            if (leadService.updateLeadstts(id,status)) {
                response = ResponseEntity.ok().build();
            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/convert")
    public ResponseEntity<Map<String, Integer>> convert(@RequestBody LeadTransDTO dto) {

        // dto를 acc, cont, oppty 객체로 나누기
        AccPassDTO acc = AccPassDTO.builder()
                .companyName(dto.getCompanyName())
                .country(dto.getCountry())
                .address(dto.getAddress())
                .postalCode(dto.getPostalCode())
                .companySize(dto.getCompanySize())
                .ownerName(dto.getOwnerName())
                .build();

        log.info("애드워드를 찾아라  : {}", acc.getOwnerName());

        ContactPassDTO cont = ContactPassDTO.builder()
                .name(dto.getContactName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .ownerName(dto.getOwnerName())
                .build();

        OpptyPassDTO oppty = OpptyPassDTO.builder()
                .opportunityName(dto.getSalesName())
                .amount(dto.getRevenue())
                .source(dto.getLeadSource())
                .targetClose(dto.getTargetCloseDate())
                .ownerName(dto.getOwnerName())
                .build();

        log.info("전환할 필드들  : {}", dto.toString());

        try {
            Map<String, Integer> result = leadService.convertFull(acc, cont, oppty);
            if (result != null) {
                return ResponseEntity.ok(result);  // ✅ JSON 형태로 반환
            }
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", -1));
        }

        return ResponseEntity.internalServerError().body(Collections.singletonMap("error", -2));
    }

}