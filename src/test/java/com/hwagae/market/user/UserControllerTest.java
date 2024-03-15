package com.hwagae.market.user;

import com.hwagae.market.email.EmailController;
import com.hwagae.market.user.UserController;
import com.hwagae.market.user.UserService;
import com.hwagae.market.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private EmailController emailController;

    @Mock
    private Model model;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, emailController);
    }

    @Test
    void testJoin_Success() {
        // GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id("test123");
        userDTO.setUser_pw("password");

        when(emailController.getEmailAuthResult()).thenReturn("ok");

        // WHEN
        String viewName = userController.join(userDTO);

        // THEN
        assertEquals("redirect:/user/login?success=true", viewName);
        verify(userService, times(1)).save(userDTO);
    }

    @Test
    void testJoin_Failure() {
        // GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id("test123");
        userDTO.setUser_pw("password");

        when(emailController.getEmailAuthResult()).thenReturn("not_ok");

        // WHEN
        String viewName = userController.join(userDTO);

        // THEN
        assertEquals("redirect:/user/join?success=false", viewName);
        verify(userService, never()).save(userDTO);
    }

    @Test
    void testLogin_Success() {
        // GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id("test123");
        userDTO.setUser_pw("password");

        HttpSession session = new MockHttpSession();

        when(userService.login(userDTO)).thenReturn(userDTO);

        // WHEN
        String viewName = userController.Login(userDTO, session);

        // THEN
        assertEquals("redirect:/views/myPage/myPage", viewName);
        assertEquals(userDTO, session.getAttribute("user"));
    }

    @Test
    void testLogin_Failure() {
        // GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id("test123");
        userDTO.setUser_pw("password");

        HttpSession session = new MockHttpSession();

        when(userService.login(userDTO)).thenReturn(null);

        // WHEN
        String viewName = userController.Login(userDTO, session);

        // THEN
        assertEquals("redirect:/user/login?loginFailed=true", viewName);
        assertEquals(null, session.getAttribute("user"));
    }

    // Add tests for other methods as needed
}