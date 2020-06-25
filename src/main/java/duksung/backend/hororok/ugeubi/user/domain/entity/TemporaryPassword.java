package duksung.backend.hororok.ugeubi.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@RedisHash(value = "temporary_password", timeToLive = 60L)
public class TemporaryPassword implements Serializable {
    @Id
    private String id; //email

    private String userId;

    private String temporaryPassword;

    @Builder
    public TemporaryPassword(String email, String userId, String temporaryPassword){
        this.id=email;
        this.userId=userId;
        this.temporaryPassword=temporaryPassword;
    }

    public void changeTemporaryPassword(String temporaryPassword){
        this.temporaryPassword=temporaryPassword;
    }
}
