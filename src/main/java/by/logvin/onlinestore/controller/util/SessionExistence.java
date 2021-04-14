package by.logvin.onlinestore.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface SessionExistence {
    boolean isSessionExist(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
