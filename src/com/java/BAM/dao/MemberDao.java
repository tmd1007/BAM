package com.java.BAM.dao;

import com.java.BAM.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDao extends Dao{
    private List<Member> members;

    public MemberDao() {
        this.members = new ArrayList<>();
    }

    public void add(Member member) {
        members.add(member);
        lastId++;
    }

    public Member getMemberByLoginId(String loginId) {
        for (Member m : members) {
            if (m.loginId.equals(loginId)) {
                return m;
            }
        }
        return null;
    }

    public boolean loginIdChk(String id) {

        Member member = getMemberByLoginId(id);

        if (member != null) {
            return false;
        }

        return true;
    }

    public String getWriterName(int memberId) {
        for (Member member: members) {
            if (memberId == member.id) {
                return member.name;
            }
        }

        return null;
    }
}
