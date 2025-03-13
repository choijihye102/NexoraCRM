package project.jennie.nexoracrm.domain;


import lombok.Data;

import java.time.LocalDate;


@Data
public class LeadTransDTO {

    private String companyName; // con
    private String address; //acc
    private String companySize; //acc
    private String country; //acc
    private String contactName; //con
    private String email;   //con
    private String salesName;   // 기회명 oppty
    private String leadSource;  //oppty
    private Integer revenue; // 예상 매출금   oppty
    private LocalDate targetCloseDate; //예상 마감일    oppty
    private String phone;  //con
    private String ownerName;   //acc, con, oppty
    private String postalCode;   //acc

}
