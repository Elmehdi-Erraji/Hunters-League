package com.spring.huntersleague;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.repository.AuthRepository;
import com.spring.huntersleague.service.AuthService;
import com.spring.huntersleague.web.errors.user.InvalidCredentialsException;
import com.spring.huntersleague.web.errors.user.InvalidUserException;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldThrowInvalidUserException_WhenUserIsNull() {
        assertThrows(InvalidUserException.class, () -> authService.register(null));
    }

    @Test
    void register_ShouldThrowInvalidUserException_WhenUserHasInvalidData() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("short");
        user.setEmail(null);

        assertThrows(InvalidUserException.class, () -> authService.register(user));
    }

    @Test
    void register_ShouldThrowInvalidUserException_WhenUserAlreadyExists() {
        User user = new User();
        user.setUsername("existingUser");
        user.setPassword("validPassword123");
        user.setEmail("test@example.com");

        when(authRepository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(InvalidUserException.class, () -> authService.register(user));
    }

    @Test
    void register_ShouldSaveUser_WhenUserIsValidAndUnique() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("validPassword123");
        user.setEmail("newuser@example.com");

        when(authRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(authRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(authRepository.save(user)).thenReturn(user);

        User registeredUser = authService.register(user);

        assertNotNull(registeredUser);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        verify(authRepository, times(1)).save(user);
    }

    @Test
    void login_ShouldThrowUserNotFoundException_WhenUserNotFound() {
        User userLogin = new User();
        userLogin.setUsername("nonExistentUser");
        userLogin.setPassword("password");

        when(authRepository.findByUsername(userLogin.getUsername())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.login(userLogin));
    }

    @Test
    void login_ShouldThrowInvalidCredentialsException_WhenPasswordIsIncorrect() {
        User userLogin = new User();
        userLogin.setUsername("existingUser");
        userLogin.setPassword("wrongPassword");

        User foundUser = new User();
        foundUser.setUsername("existingUser");
        foundUser.setPassword(BCrypt.hashpw("correctPassword", BCrypt.gensalt()));

        when(authRepository.findByUsername(userLogin.getUsername())).thenReturn(Optional.of(foundUser));

        assertThrows(InvalidCredentialsException.class, () -> authService.login(userLogin));
    }

    @Test
    void login_ShouldReturnTrue_WhenCredentialsAreCorrect() {
        User userLogin = new User();
        userLogin.setUsername("validUser");
        userLogin.setPassword("validPassword");

        User foundUser = new User();
        foundUser.setUsername("validUser");
        foundUser.setPassword(BCrypt.hashpw("validPassword", BCrypt.gensalt()));

        when(authRepository.findByUsername(userLogin.getUsername())).thenReturn(Optional.of(foundUser));

        assertTrue(authService.login(userLogin));
    }
}
