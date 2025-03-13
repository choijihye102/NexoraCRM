package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Member {

    private String userid;  // 사번번호 양식 : 중복검사 필요
    private String name;
    private String img;   // 프로필 사진 파일명
    private String email;
    private String pw;
    private String role;    // 팀원, 팀장, C레벨
    private boolean isAdmin;    // 유저 추가, 수정 권한
    private String managedId ;    // 상위직급 id - 외래키
    private LocalDateTime createAt;  // 등록일
    private LocalDateTime updateAt;  // 수정일

}
