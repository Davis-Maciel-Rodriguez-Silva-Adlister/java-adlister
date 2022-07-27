package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLUsersDao;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("ads", DaoFactory.getAdsDao().filterByUser(user.getId()));
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Getting current user info from session
        User user = (User) request.getSession().getAttribute("user");

        // Getting info that user wants changed
        String changeUsernameTo = request.getParameter("changeUsernameTo");
        String changeEmailTo = request.getParameter("changeEmailTo");
        String changePasswordTo = request.getParameter("changePasswordTo");
        String confirmPassword = request.getParameter("confirmPassword");

        // Method for changing username by user id
        if(request.getParameter("changeUsernameTo") != null) {
            DaoFactory.getUsersDao().changeUsername(user.getId(), changeUsernameTo);
            response.sendRedirect("/profile");
        }
        // Method for changing email by user id
        if(request.getParameter("changeEmailTo") != null) {
            DaoFactory.getUsersDao().changeEmail(user.getId(), changeEmailTo);
            response.sendRedirect("/profile");
        }
        // Method for changing password by user id
        if(request.getParameter("changePasswordTo") != null) {
            if (changePasswordTo.equals(confirmPassword)) {
                String hash = Password.hash(changePasswordTo);
                DaoFactory.getUsersDao().changePassword(user.getId(), hash);
                response.sendRedirect("/profile");
            }
        }

    }




}
