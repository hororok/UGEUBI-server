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
    private String userId;

    private String email;

    private String password;

    private String userName;

    @Builder
    private User(String userId, String email, String password, String userName){
        this.userId=userId;
        this.email=email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.userName=userName;
    }

    public void modifyPassword(String password){ this.password= BCrypt.hashpw(password,BCrypt.gensalt()); }
    public boolean isNotEqualToPassword(String password){ return !BCrypt.checkpw(password, this.password); }

}
