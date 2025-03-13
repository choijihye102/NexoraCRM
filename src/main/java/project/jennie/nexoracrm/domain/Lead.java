package project.jennie.nexoracrm.domain;

import lombok.Data;

@Data
public class Lead {

    private String id;                // 리드 ID

    private String name;              // 이름
    private String email;             // 이메일
    private String phone;             // 전화번호
    private String customerResponse;  // 고객 반응
    private String company;           // 회사명
    private String companySize;       // 회사 규모
    private String country;           // 국가명
    private String postalCode;        // 우편번호
    private String address;           // 주소
    private String source;            // 유입 경로

    private String status;            // 상태

    private String ownerName;          // 소유자명 (FK)

    private String creatAt;         // 생성일
    private String updateAt;         // 수정일


}
