package br.com.unifg.educplus.domain.dto;

import br.com.unifg.educplus.domain.enums.RoleNameEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String password;
    private RoleNameEnum roleName;
}
