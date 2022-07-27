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
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Getting current user info from session
        User user = (User) request.getSession().getAttribute("user");

        // Getting info that user wants changed
        String changeUsernameTo = request.getParameter("changeUsernameTo");
        String changeEmailTo = request.getParameter("changeEmailTo");
        String changePasswordTo = request.getParameter("changePasswordTo");
        String confirmPassword = request.getParameter("confirmPasswordTo");

        // Method for changing username by user id
        if(request.getParameter("changeUsernameTo") != null) {
            if(!changeUsernameTo.isEmpty()) {
                DaoFactory.getUsersDao().changeUsername(user.getId(), changeUsernameTo);
                response.sendRedirect("/profile");
            }else{
                response.sendRedirect("/profile");
            }
        }
        // Method for changing email by user id
        if(request.getParameter("changeEmailTo") != null) {
            if(!changeEmailTo.isEmpty()) {
                DaoFactory.getUsersDao().changeEmail(user.getId(), changeEmailTo);
                response.sendRedirect("/profile");
            }else{
                response.sendRedirect("/profile");
            }
        }
        // Method for changing password by user id
        if(request.getParameter("changePasswordTo") != null) {
            if (changePasswordTo.equals(confirmPassword) && !changePasswordTo.isEmpty()) {
                DaoFactory.getUsersDao().changePassword(user, changePasswordTo);
                response.sendRedirect("/profile");
            }else{
                response.sendRedirect("/profile");
            }
        }



    }




}
