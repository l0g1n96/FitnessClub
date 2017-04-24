package datamanage;

import dataprocess.MemberData;
import dto.MemberDTO;

import java.util.ArrayList;
import java.util.List;

public class MemberDataImpl implements MemberData {

    private List<MemberDTO> members = new ArrayList<>();

    @Override
    public List<MemberDTO> getAllMembers() {
        return members;
    }

    @Override
    public void addMember(MemberDTO member) {

        if (member == null) {
            throw new IllegalArgumentException("Member or hours cannot be empty!");
        }

        if (members.contains(member)) {
            return;
        }

        members.add(member);
    }

    @Override
    public void deleteMember(MemberDTO member) {

        if (member == null) {
            throw new IllegalArgumentException("Member or hours cannot be empty!");
        }

        if (members.contains(member)) {
            members.remove(member);
        }
    }

    @Override
    public MemberDTO updateMember(MemberDTO member) {

        if (member == null) {
            throw new IllegalArgumentException("Member cannot be empty!");
        }

        int id = member.getId();
        String name = member.getName();
        String lastname = member.getLastname();

        return new MemberDTO(name, lastname, id);
    }

}