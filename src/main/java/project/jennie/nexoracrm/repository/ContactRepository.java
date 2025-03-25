package project.jennie.nexoracrm.repository;

import org.apache.ibatis.annotations.Mapper;
import project.jennie.nexoracrm.domain.Acc;
import project.jennie.nexoracrm.domain.Contact;

import java.util.List;

@Mapper
public interface ContactRepository {

    int countCon(String userid, String managedId, String role);

    List<Contact> selecCons(int stnum, int pageSize, String userid, String managedId, String role);

}
