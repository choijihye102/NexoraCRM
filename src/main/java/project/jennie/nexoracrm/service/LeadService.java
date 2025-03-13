package project.jennie.nexoracrm.service;

import org.springframework.transaction.annotation.Transactional;
import project.jennie.nexoracrm.domain.*;

import java.util.List;
import java.util.Map;

public interface LeadService {

    LeadListDTO readLead(int cpg, String userid, String managerid,String role);

    int countLead(String userid, String managerid,String role);

    Lead readOndLead(String id);

    boolean updateLeadstts(String id, String status);  // lead 상태 변경

    Map<String, Integer> convertFull(AccPassDTO acc, ContactPassDTO cont, OpptyPassDTO oppty);   // lead 마감때 full전환

}
