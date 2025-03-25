
package project.jennie.nexoracrm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import project.jennie.nexoracrm.domain.Acc;
import project.jennie.nexoracrm.domain.Oppty;
import project.jennie.nexoracrm.domain.OpptyViewDTO;

import java.util.List;

@Mapper
public interface OpptyRepository {

    OpptyViewDTO readOneOppty(String id);

    @Update("update opptys set stage = #{status} where id = #{id}")
    int updateLeadstts(String id, String status);

    @Update("update opptys set close_date = CURRENT_DATE WHERE id = #{id}")
    int updateClosed(String id);

    int countOppty(String userid, String managedId, String role);

    List<Oppty> selecOppty(int stnum, int pageSize, String userid, String managedId, String role);
}
