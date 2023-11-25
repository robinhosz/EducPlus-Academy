package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.dto.UserDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Role;
import br.com.unifg.educplus.domain.entity.User;
import br.com.unifg.educplus.domain.enums.RoleNameEnum;
import br.com.unifg.educplus.infra.repositories.RoleRepository;
import br.com.unifg.educplus.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    Role role;
    User user;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startUser();
    }
    @Test
    void createUser() {

        when(roleRepository.findByName(any())).thenReturn(role);

        when(userRepository.save(any())).thenReturn(user);

        User response = userService.createUser(userDTO);

            assertNotNull(response);
            assertEquals(User.class, response.getClass());
            assertEquals(1L, response.getId());
            assertEquals("Robson", response.getUsername());
            assertEquals("123", response.getPassword());

    }

    private void startUser() {
        user = new User(1L, "Robson", "123", new ArrayList<>());
        userDTO = new UserDTO("Robson", "123", RoleNameEnum.ROLE_ALUNO);
    }

    private void startRole() {
        role = new Role(1L, "ROLE_ALUNO", new ArrayList<>());
    }
}