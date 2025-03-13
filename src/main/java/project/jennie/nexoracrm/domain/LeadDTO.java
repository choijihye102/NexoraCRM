package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;


@Data
public class LeadDTO {

    private String id;                // 리드 ID
    private String name;              // 이름
    private String email;             // 이메일
    private String phone;             // 전화번호
    private String company;           // 회사명
    private String source;            // 유입 경로
    private String status;            // 상태

    private String ownerName;          // 소유자명 (FK)



}
