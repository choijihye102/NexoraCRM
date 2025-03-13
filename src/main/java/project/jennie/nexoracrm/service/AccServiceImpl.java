
package project.jennie.nexoracrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.jennie.nexoracrm.domain.*;
import project.jennie.nexoracrm.repository.AccRepository;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccServiceImpl implements AccService  {
    private final AccRepository accMapper;
    @Value( "${board.page-size}") private  int pageSize;

    @Override
    public AccOneDTO readOndAcc(String id) {

        Acc acc =  accMapper.selectAcc(id);
        List<Contact> contlist = accMapper.selectContList(id);
        List<Oppty> opptlist=  accMapper.selectOpptyList(id);


        return new AccOneDTO(acc,contlist, opptlist);
    }

    @Override
    public AccListDTO readAcc(int cpg, String userid, String managedId, String role) {
        int stnum = (cpg-1) * pageSize;
        int totalItems = accMapper.countAcc(userid, managedId, role);
        List<Acc> accs = accMapper.selectAccs(stnum, pageSize, userid, managedId, role);

        return new AccListDTO(cpg, totalItems, pageSize, accs );
    }

    @Override
    public int countAcc(String userid, String managedId, String role) {
        return accMapper.countAcc(userid,managedId,role);
    }
}
