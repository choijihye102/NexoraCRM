package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Data
public class AccPassDTO {

    private String companyName;
    private String country;
    private String address;
    private String postalCode;
    private String companySize;

    private String ownerName;

    private int accId;

}
