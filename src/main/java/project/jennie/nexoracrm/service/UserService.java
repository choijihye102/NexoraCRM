
package project.jennie.nexoracrm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.jennie.nexoracrm.domain.Member;
import project.jennie.nexoracrm.domain.MemberDTO;
import project.jennie.nexoracrm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userMapper;

    public Member loginMember(MemberDTO user) {

        Member findMember = userMapper.findByUserid(user.getUserid());

        if (findMember == null || !findMember.getPw().equals(user.getPw())) {
            throw new IllegalStateException ("Service에서 호출 => 아이디나 비번이 일치하지 않습니다. ");
        }

        return findMember;

    }


}
