package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class ContactPassDTO {

    private String name;
    private String phone;
    private String email;

    private String ownerName;

    private int conId;

    private int accId;


}
