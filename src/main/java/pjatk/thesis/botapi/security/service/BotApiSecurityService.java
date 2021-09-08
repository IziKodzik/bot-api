package pjatk.thesis.botapi.security.service;

import pjatk.thesis.botapi.security.domain.CBotApiRole;
import pjatk.thesis.botapi.security.domain.BotApiUser;

import java.util.List;

public interface BotApiSecurityService {

    BotApiUser saveUser(BotApiUser user);
    CBotApiRole saveRole(CBotApiRole role);
    void addRoleToUser(String email, String roleName);
    BotApiUser getUserByEmail(String email);
    List<BotApiUser> getAllUsers();
    CBotApiRole getRoleByName(String name);
}
