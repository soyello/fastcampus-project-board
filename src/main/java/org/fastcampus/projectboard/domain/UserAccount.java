package org.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email",unique=true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Setter
    private String name;

    protected UserAccount(){}

    private UserAccount(String userId, String userPassword, String email, String nickname, String name){
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String name){
        return new UserAccount(userId, userPassword, email, nickname, nickname);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserAccount userAccount)) return false;
        return id != null && id.equals(userAccount.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
