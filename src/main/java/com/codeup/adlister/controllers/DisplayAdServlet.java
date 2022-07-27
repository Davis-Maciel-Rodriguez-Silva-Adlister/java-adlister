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

@WebServlet(name="DisplayAdServlet", urlPatterns = "/view-ad")
public class DisplayAdServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        String ad = request.getParameter("adName");
        request.setAttribute("ads", DaoFactory.getAdsDao().search(ad));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/ads/view-ad.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        User user = (User) request.getSession().getAttribute("user");
    }
}
