package pjatk.thesis.botapi.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotApiUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BotApiUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "users",allowSetters = true)
    private Collection<CBotApiRole> roles = new HashSet<>();


}
