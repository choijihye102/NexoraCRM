
package project.jennie.nexoracrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.jennie.nexoracrm.domain.Acc;
import project.jennie.nexoracrm.domain.AccListDTO;
import project.jennie.nexoracrm.domain.Contact;
import project.jennie.nexoracrm.repository.AccRepository;
import project.jennie.nexoracrm.repository.ContactRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService  {
    private final ContactRepository conMapper;
    @Value( "${board.page-size}") private  int pageSize;

    @Override
    public AccListDTO readCon(int cpg, String userid, String managedId, String role) {
        int stnum = (cpg-1) * pageSize;
        int totalItems = conMapper.countCon(userid, managedId, role);
        List<Contact> cons = conMapper.selecCons(stnum, pageSize, userid, managedId, role);

        return new AccListDTO(cpg, totalItems, pageSize, cons );
    }

    @Override
    public int countCon(String userid, String managedId, String role) {
        return conMapper.countCon(userid,managedId,role);
    }

}
