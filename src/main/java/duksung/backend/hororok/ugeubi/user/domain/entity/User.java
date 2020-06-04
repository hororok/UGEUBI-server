package duksung.backend.hororok.ugeubi.user.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String username;

    @Builder
    private User(String email, String password, String phoneNumber, String username){
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.username=username;
    }

    public void modifyPassword(String password){ this.password= BCrypt.hashpw(password,BCrypt.gensalt()); }
    public boolean isNotEqualToPassword(String password){ return BCrypt.checkpw(password, this.password); }

}
