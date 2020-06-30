package duksung.backend.hororok.ugeubi.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@RedisHash(value = "sign_up_number", timeToLive = 300L)
public class SignUpNumber implements Serializable {
    @Id
    private String id;

    private String email;

    private String authenticateNumber;

    @Builder
    public SignUpNumber(String id, String email, String authenticateNumber){
        this.id=id;
        this.email=email;
        this.authenticateNumber=authenticateNumber;
    }

    public void changeNumber(String authenticateNumber){
        this.authenticateNumber=authenticateNumber;
    }
}
