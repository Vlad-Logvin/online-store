package by.logvin.onlinestore.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public interface UserExistence {
    boolean isUserExist(HttpServletRequest request, HttpServletResponse response) throws IOException;
    boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
