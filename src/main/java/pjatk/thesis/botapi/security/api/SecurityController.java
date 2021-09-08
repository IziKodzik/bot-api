package pjatk.thesis.botapi.security.api;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pjatk.thesis.botapi.security.api.dtos.AddRoleToUserDto;
import pjatk.thesis.botapi.security.domain.CBotApiRole;
import pjatk.thesis.botapi.security.domain.BotApiUser;
import pjatk.thesis.botapi.security.service.BotApiSecurityService;

import java.net.URI;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/security")
public class SecurityController {
    private final BotApiSecurityService securityService;
    private Algorithm algorithm;

    @PreAuthorize(" securityConfig.hasUserId(authentication, userId) or hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<BotApiUser>> getUser(@PathVariable("userId") Long userId, Authentication authentication){
        return ResponseEntity.ok(securityService.getAllUsers());
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users")
    public ResponseEntity<List<BotApiUser>> getAllUsers(){
        return ResponseEntity.ok(securityService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody BotApiUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/").toUriString());
        return ResponseEntity.ok(securityService.saveUser(user).toString());
    }
    @PostMapping("/role")
    public ResponseEntity<String> saveRole(@RequestBody CBotApiRole role){
        return ResponseEntity.ok(securityService.saveRole(role).toString());
    }
    @PostMapping("/role-user")
    public ResponseEntity<String> addRoleToUser(@RequestBody AddRoleToUserDto roleToUser){
        securityService.addRoleToUser(roleToUser.getEmail(),roleToUser.getRoleName());
        return ResponseEntity.ok("Added");
    }



}
