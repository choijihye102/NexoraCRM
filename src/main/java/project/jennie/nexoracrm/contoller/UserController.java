package project.jennie.nexoracrm.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.jennie.nexoracrm.domain.Member;
import project.jennie.nexoracrm.domain.MemberDTO;
import project.jennie.nexoracrm.service.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/join")
    public String join() {

        return "views/member/join";

    }


    @PostMapping("/login")
    public ResponseEntity<?> loginok(MemberDTO member , HttpSession session ) {
        // 로그인 처리시 기타오류 발생에 대한 응답코드
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("로그인된 회원 정보 :  {}", member);

        try{
            // 정상 처리시 상태코드 200으로 응답
            Member loginUser = userService.loginMember(member);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(1200); // 세션 유지 : 10분

            response = ResponseEntity.ok().build();
        } catch(IllegalStateException e){
            //  비정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못으로 인한 오류 일때
            //  아이디나 비밀번호 잘못 입력시.
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        } catch ( Exception e){
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못으로 인한 오류 일때
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        }
        return response;

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 제거.

        return "redirect:/";
    }


}
