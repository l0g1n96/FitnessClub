package datamanage;

import dto.MemberDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemberDataImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void addMemberTestNullForMember() {

        MemberDataImpl m = new MemberDataImpl();
        MemberDTO member = null;

        m.addMember(member);
    }

    @Test
    public void addMemberTest() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        MemberDataImpl data = new MemberDataImpl();
        List<MemberDTO> list = new ArrayList<>();

        list.add(member);

        data.addMember(member);

        Assert.assertEquals(list, data.getAllMembers());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMemberTestNullForMember() {

        MemberDataImpl m = new MemberDataImpl();
        MemberDTO member = null;

        m.deleteMember(member);
    }

    @Test
    public void deleteMemberTest() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        List<MemberDTO> membersList = new ArrayList<>();
        MemberDataImpl data = new MemberDataImpl();

        data.deleteMember(member);

        Assert.assertEquals(membersList, data.getAllMembers());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateMemberTestForMemberNull() {

        MemberDataImpl m = new MemberDataImpl();
        MemberDTO member = null;

        m.updateMember(member);
    }

    @Test
    public void updateMemberTest() {

        MemberDataImpl m = new MemberDataImpl();
        MemberDTO oldMember = new MemberDTO("a", "b", 1);

        MemberDTO newMember = m.updateMember(oldMember);

        Assert.assertEquals(oldMember, newMember);
    }
}