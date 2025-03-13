package project.jennie.nexoracrm.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Oppty {

    private String id;  // 자동증감 , pk

    private String opportunityName;
    private int amount;
    private String source;
    private LocalDate targetClose;

    private String stage;  // 기본값 : 요구분석
    private String notes;
    private LocalDate closeDate;

    private String ContatId; // contacts 테이블 id - fk키
    private String accountId; // accounts 테이블 id - fk키

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private String ownerName;

}
