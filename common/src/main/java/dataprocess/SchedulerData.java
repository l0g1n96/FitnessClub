package dataprocess;

import dto.MemberDTO;

import java.util.Date;
import java.util.List;

public interface SchedulerData {

    boolean[] addToScheduler(MemberDTO member, int[] hours);

    void deleteScheduler(MemberDTO member, int[] hours);

    int longestPeriodAvailable();
}
