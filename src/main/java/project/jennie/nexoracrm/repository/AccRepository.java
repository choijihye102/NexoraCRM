package project.jennie.nexoracrm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.jennie.nexoracrm.domain.Acc;
import project.jennie.nexoracrm.domain.Contact;
import project.jennie.nexoracrm.domain.Oppty;

import java.util.List;

@Mapper
public interface AccRepository {

    @Select("select * from accounts where id = #{id}")
    Acc selectAcc(String id);

    @Select("select * from contacts where account_id = #{id}")
    List<Contact> selectContList(String id);

    @Select("select * from opptys where account_id = #{id}")
    List<Oppty> selectOpptyList(String id);

    int countAcc(String userid, String managedId, String role);

    List<Acc> selectAccs(int stnum, int pageSize, String userid, String managedId, String role);
}
