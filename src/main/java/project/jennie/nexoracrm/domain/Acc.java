package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Acc {

    private String id;  // 자동증감 , pk

    private String companyName;
    private String country;
    private String address;
    private String postalCode;
    private String companySize;

    private String phone;
    private String website;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private String ownerName;

}
