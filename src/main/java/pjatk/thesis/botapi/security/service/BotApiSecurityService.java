package pjatk.thesis.botapi.security.service;

import pjatk.thesis.botapi.security.domain.BotApiRole;
import pjatk.thesis.botapi.security.domain.BotApiUser;

import java.util.List;

public interface BotApiSecurityService {

    BotApiUser saveUser(BotApiUser user);
    BotApiRole saveRole(BotApiRole role);
    void addRoleToUser(String email, String roleName);
    BotApiUser getUserByEmail(String email);
    List<BotApiUser> getAllUsers();
    BotApiRole getRoleByName(String name);
}
