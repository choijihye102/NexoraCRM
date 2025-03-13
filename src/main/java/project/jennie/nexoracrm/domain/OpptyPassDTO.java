
package project.jennie.nexoracrm.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class OpptyPassDTO {

    private String opportunityName;
    private Integer amount;
    private String source;
    private LocalDate targetClose;

    private String ownerName;

    private int opptyId;

    private int conId;

    private int accId;

}
