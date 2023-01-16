package com.example.api02.domain.member;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name="tbl_member")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {


    @Id
    private String mid;

    private String mpw;

    private String mname;

    private String nickname;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> memberRoleSet = new HashSet<>();

    public void addRole(MemberRole memberRole ) {

        memberRoleSet.add(memberRole);

    }

    public void clearRoles() {
        memberRoleSet.clear();
    }

}
