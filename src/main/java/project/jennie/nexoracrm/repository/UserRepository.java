package project.jennie.nexoracrm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.jennie.nexoracrm.domain.Member;

@Mapper
public interface UserRepository {

    @Select("select * from member where userid = #{userid}")
    Member findByUserid(String userid);


}
