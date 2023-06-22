package com.java.BAM.service;

import com.java.BAM.container.Container;
import com.java.BAM.dao.MemberDao;
import com.java.BAM.dto.Member;

public class MemberService {

    private MemberDao memberDao;

    public MemberService() {
        this.memberDao = Container.memberDao;
    }

    public int getLastId() {
        return memberDao.getLastId();
    }

    public void add(Member member) {
        memberDao.add(member);
    }

    public boolean loginIdChk(String loginId) {
        return memberDao.loginIdChk(loginId);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public String getWriterName(int memberId) {
        return memberDao.getWriterName(memberId);
    }
}
