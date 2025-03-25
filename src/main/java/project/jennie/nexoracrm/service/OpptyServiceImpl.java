
package project.jennie.nexoracrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jennie.nexoracrm.domain.*;
import project.jennie.nexoracrm.repository.OpptyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpptyServiceImpl implements OpptyService  {
   private final OpptyRepository opptyRepo;
   @Value( "${board.page-size}") private  int pageSize;

    @Override
    public OpptyViewDTO readOndOppty(String id) {

        return opptyRepo.readOneOppty(id);
    }

    @Transactional
    @Override
    public boolean updateLeadstts(String id, String status) {

        return opptyRepo.updateLeadstts(id,status) > 0;
    }

    @Transactional
    @Override
    public boolean updateClosed(String id) {
        return opptyRepo.updateClosed(id) > 0;
    }

    @Override
    public OpptyListDTO readOpptys(int cpg, String userid, String managedId, String role) {
        int stnum = (cpg-1) * pageSize;
        int totalItems = opptyRepo.countOppty(userid, managedId, role);
        List<Oppty> opptys = opptyRepo.selecOppty(stnum, pageSize, userid, managedId, role);

        return new OpptyListDTO(cpg, totalItems, pageSize, opptys);
    }

    @Override
    public int countOpptys(String userid, String managedId, String role) {
        return opptyRepo.countOppty(userid,managedId,role);
    }
}
