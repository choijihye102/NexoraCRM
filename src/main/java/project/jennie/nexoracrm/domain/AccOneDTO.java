package project.jennie.nexoracrm.domain;

import lombok.Data;

import java.util.List;

@Data
public class AccOneDTO {
    public AccOneDTO(Acc acc, List<Contact>  contlist, List<Oppty> opptlist) {
    this.acc = acc;
    this.contlist = contlist;
    this.opptlist = opptlist;
}
    private Acc acc;
    private List<Contact> contlist;
    private List<Oppty> opptlist;

}
