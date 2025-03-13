
package project.jennie.nexoracrm.service;

import project.jennie.nexoracrm.domain.AccListDTO;
import project.jennie.nexoracrm.domain.AccOneDTO;
import project.jennie.nexoracrm.domain.LeadListDTO;

public interface AccService {

    AccOneDTO readOndAcc(String id);

    AccListDTO readAcc(int cpg, String userid, String managedId, String role);

    int countAcc(String userid, String managedId, String role);
}
