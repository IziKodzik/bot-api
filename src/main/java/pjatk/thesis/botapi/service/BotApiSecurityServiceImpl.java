package pjatk.thesis.botapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pjatk.thesis.botapi.security.domain.BotApiRole;
import pjatk.thesis.botapi.security.domain.BotApiUser;
import pjatk.thesis.botapi.security.repo.BotApiRoleRepo;
import pjatk.thesis.botapi.security.repo.BotApiUserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotApiSecurityServiceImpl implements BotApiSecurityService, UserDetailsService {

    private final BotApiUserRepo userRepo;
    private final BotApiRoleRepo roleRepo;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BotApiUser user = userRepo.findByEmail(email);
        if(user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found.");
        log.info("User found in db {}",user.getEmail());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public BotApiUser saveUser(BotApiUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("Saving user {}",user.getEmail());
        return userRepo.save(user);
    }

    @Override
    public BotApiRole saveRole(BotApiRole role) {
        log.info("Saving role {}",role.getName());
        return roleRepo.save(role);
    }

    @Override
    @Transactional
    public void addRoleToUser(String email, String roleName) {
        log.info("Saving user {} role {}",email,roleName);
        BotApiUser user = userRepo.findByEmail(email);
        BotApiRole role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        role.getUsers().add(user);
    }

    @Override
    public BotApiUser getUserByEmail(String email) {
        log.info("Searching user {}",email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<BotApiUser> getAllUsers() {
        log.info("fetching users users");
        return userRepo.findAll();
    }

    @Override
    public BotApiRole getRoleByName(String name) {
        log.info("Sarching rolee {}",name);
        return roleRepo.findByName(name);
    }
}
