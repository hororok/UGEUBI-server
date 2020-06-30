package duksung.backend.hororok.ugeubi.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@RedisHash(value = "find_password_code", timeToLive = 300L)
public class FindPasswordCode implements Serializable {
    @Id
    private String id;

    private String email;

    private String userId;

    private String findPasswordCode;

    @Builder
    public FindPasswordCode(String id, String email, String userId, String findPasswordCode){
        this.id=id;
        this.email=email;
        this.userId=userId;
        this.findPasswordCode=findPasswordCode;
    }

    public void changePasswordCode(String findPasswordCode){
        this.findPasswordCode=findPasswordCode;
    }
}
