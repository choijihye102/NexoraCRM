package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class OpptyViewDTO {

    private String id;  // 자동증감 , pk

    private String opportunityName;
    private int amount;
    private String source;
    private LocalDate targetClose;

    private String stage;  // 기본값 : 요구분석
    private String notes;
    private LocalDate closeDate;

    private String contatId; // contacts 테이블 id - fk키
    private String accountId; // accounts 테이블 id - fk키

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String ownerName;

    private String contactName; // mapper에서 받아올때 id로 조인해서 매핑
    private String accountName; //  mapper에서 받아올때 id로 조인해서 매핑
    private String address;
    private String website;
    private String email;
    private String phone;




}
