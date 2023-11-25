package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.entity.Role;
import br.com.unifg.educplus.domain.entity.User;
import br.com.unifg.educplus.infra.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Mocking user data
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");

        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ALUNO");
        mockUser.setRoles(Collections.singletonList(role));

        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        // Test loadUserByUsername when user is found
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals("ROLE_ALUNO", authorities.iterator().next().getAuthority());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Mocking scenario where user is not found
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        // Test loadUserByUsername when user is not found
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonExistentUser");
        });
    }
}