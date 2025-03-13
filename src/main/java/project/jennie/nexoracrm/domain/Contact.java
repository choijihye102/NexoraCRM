package project.jennie.nexoracrm.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Contact {

    private String id;  // 자동증감 , pk

    private String name;
    private String phone;
    private String email;

    private LocalDate birthday;
    private String department;
    private String title;

    private String accountId;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private String ownerName;

}
