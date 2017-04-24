package dataprocess;

import dto.MemberDTO;

import java.util.List;

public interface MemberData {

    List<MemberDTO> getAllMembers();

    void addMember(MemberDTO member);

    void deleteMember(MemberDTO member);

    MemberDTO updateMember(MemberDTO member);

}
