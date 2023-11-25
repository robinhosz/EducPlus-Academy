package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.dto.UserDTO;
import br.com.unifg.educplus.domain.entity.Role;
import br.com.unifg.educplus.domain.entity.User;
import br.com.unifg.educplus.infra.repositories.RoleRepository;
import br.com.unifg.educplus.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {

        Role role = roleRepository.findByName(userDTO.getRoleName().toString());

        List<Role> roles = new ArrayList<>();

        roles.add(role);

        return userRepository.save(builderUser(userDTO, roles));
    }

    public User builderUser(UserDTO obj, List<Role> roles){
        return User.builder()
                .password(passwordEncoder.encode(obj.getPassword()))
                .username(obj.getUsername())
                .roles(roles)
                .build();
    }
}
