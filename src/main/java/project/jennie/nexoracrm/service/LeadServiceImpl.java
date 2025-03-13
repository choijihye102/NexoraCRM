
package project.jennie.nexoracrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jennie.nexoracrm.domain.*;
import project.jennie.nexoracrm.repository.LeadRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService  {

    private final LeadRepository leadMapper;
    @Value( "${board.page-size}") private  int pageSize;

    @Transactional
    @Override
    public LeadListDTO readLead(int cpg, String userid, String managerid, String role) {

        int stnum = (cpg-1) * pageSize;
        int totalItems = leadMapper.countLead(userid, managerid, role);
        List<LeadDTO> leads = leadMapper.selectLeads(stnum, pageSize, userid, managerid, role);

        return new LeadListDTO(cpg, totalItems, pageSize, leads );
    }

    public int countLead(String userid, String managerid,String role){

        return leadMapper.countLead(userid,managerid,role);

    }

    @Override
    public Lead readOndLead(String id) {

        return leadMapper.selectOneLead(id);
    }

    @Transactional
    @Override
    public boolean updateLeadstts(String id, String status) {

        return leadMapper.updateLeadstts(id,status) > 0;
    }


    @Transactional
    @Override
    public Map<String, Integer> convertFull(AccPassDTO acc, ContactPassDTO cont, OpptyPassDTO oppty) {
//        Map<String, Integer> map = new HashMap<>();
//
//        // 1. account 삽입.
//        try {
//            if (leadMapper.convertAcc(acc) > 0)
//                cont.setAccId(acc.getAccId());
//                log.info("accid !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1: " + cont.getAccId());
//
//        } catch ( Exception ex) {
//            throw new IllegalStateException("convertFull 오류 발생 !! ");
//        }
//
//        //2. accont 삽입한 id 값을 인자로 넘겨 contact 삽입.
//        try {
//            if (leadMapper.convertCont(cont) > 0)
//                oppty.setConId(cont.getConId());
//                oppty.setAccId(cont.getAccId());
//
//        } catch ( Exception ex) {
//            throw new IllegalStateException("convertFull 오류 발생 !! ");
//        }
//
//        //3. contact 삽입한 id 값과 accont 삽입한 id 값을 인자로 넘겨 opportunity 삽입.
//        try {
//            leadMapper.convertOppty(oppty);
//
//        } catch ( Exception ex) {
//            throw new IllegalStateException("convertFull 오류 발생 !! ");
//        }
//
//        map.put("accid", acc.getAccId());
//        map.put("contid", cont.getConId());
//        map.put("opptyid", oppty.getOpptyId());
//
//        return map;

        Map<String, Integer> map = new HashMap<>();

        // 1. account 삽입
        leadMapper.convertAcc(acc);
        log.info("accid !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1: " + acc.getAccId()); // acc.getAccId()가 정상적으로 할당되었는지 확인

        // accId가 MyBatis에서 자동으로 설정되지 않을 경우, 수동으로 가져오는 코드 추가 가능
        cont.setAccId(acc.getAccId());

        // 2. contact 삽입
        leadMapper.convertCont(cont);
        log.info("conid !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1: " + cont.getConId()); // conId가 정상적으로 할당되었는지 확인

        // conId와 accId가 정상적으로 할당되었는지 확인 후 oppty에 설정
        oppty.setConId(cont.getConId());
        oppty.setAccId(cont.getAccId());

        // 3. opportunity 삽입
        leadMapper.convertOppty(oppty);
        log.info("opptyId !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1: " + oppty.getOpptyId());

        map.put("accid", acc.getAccId());
        map.put("contid", cont.getConId());
        map.put("opptyid", oppty.getOpptyId());

        return map;


    }


}
