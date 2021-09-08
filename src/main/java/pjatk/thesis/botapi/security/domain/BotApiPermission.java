package pjatk.thesis.botapi.security.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BotApiPermission {
    API_READ("api:read"),
    API_WRITE("api:write");

    private final String permission;


}
