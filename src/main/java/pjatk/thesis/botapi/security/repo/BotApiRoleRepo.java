package pjatk.thesis.botapi.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.thesis.botapi.security.domain.BotApiRole;

public interface BotApiRoleRepo extends JpaRepository<BotApiRole, Long> {

    BotApiRole findByName(String name);

}
