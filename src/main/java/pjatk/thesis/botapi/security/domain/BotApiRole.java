package pjatk.thesis.botapi.security.domain;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static pjatk.thesis.botapi.security.domain.BotApiPermission.*;

@RequiredArgsConstructor
@Getter
public enum BotApiRole {
    CLIENT(Sets.newHashSet(API_READ)),
    ADMIN(Sets.newHashSet(API_WRITE,API_READ)),
    MANAGER(Sets.newHashSet(API_WRITE));

    private final Set<BotApiPermission> permissions;
}
