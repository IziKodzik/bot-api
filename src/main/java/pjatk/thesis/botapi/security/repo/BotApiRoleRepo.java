package pjatk.thesis.botapi.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.thesis.botapi.security.domain.CBotApiRole;

public interface BotApiRoleRepo extends JpaRepository<CBotApiRole, Long> {

    CBotApiRole findByName(String name);

}
