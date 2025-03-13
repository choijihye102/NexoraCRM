package project.jennie.nexoracrm.repository;

import org.apache.ibatis.annotations.*;
import project.jennie.nexoracrm.domain.*;

import java.util.List;

@Mapper
public interface LeadRepository {

    List<LeadDTO> selectLeads(int stnum, int pageSize,String userid, String managerid, String role);

    int countLead(String userid, String managerid,String role);   //selectBoard시 활용

    @Select("select * from leads where id = #{id}")
    Lead selectOneLead(String id);

    @Update("update leads set status = #{status} where id = #{id}")
    int updateLeadstts(String id, String status);

    int convertAcc(AccPassDTO acc);
    int convertCont(ContactPassDTO cont);
    int convertOppty(OpptyPassDTO acc);
}
