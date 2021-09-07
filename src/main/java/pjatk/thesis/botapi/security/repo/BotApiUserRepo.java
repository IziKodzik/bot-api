package pjatk.thesis.botapi.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.thesis.botapi.security.domain.BotApiUser;

public interface BotApiUserRepo extends JpaRepository<BotApiUser, Long> {

    BotApiUser findByEmail(String email);

}
