package pjatk.thesis.botapi.security.api.dtos;

import lombok.Data;

@Data
public class AddRoleToUserDto {

    private String email;
    private String roleName;

}
