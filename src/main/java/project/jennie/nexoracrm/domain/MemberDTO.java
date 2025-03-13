package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {

    private String userid;  // 사번번호 양식 : 중복검사 필요
    private String pw;

}
