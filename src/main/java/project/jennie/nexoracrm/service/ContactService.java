

package project.jennie.nexoracrm.service;

import project.jennie.nexoracrm.domain.AccListDTO;

public interface ContactService {

    AccListDTO readCon(int cpg, String userid, String managedId, String role);

    int countCon(String userid, String managedId, String role);

}
