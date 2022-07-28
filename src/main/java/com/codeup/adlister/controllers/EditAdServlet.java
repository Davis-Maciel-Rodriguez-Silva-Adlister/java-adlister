package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controllers.EditAdServlet", urlPatterns = "/ads/edit-ad")
public class EditAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        req.setAttribute("ad", DaoFactory.getAdsDao().getAd(Long.parseLong(req.getParameter("adId"))));
        req.getRequestDispatcher("/WEB-INF/ads/edit-ad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newTitle = req.getParameter("title");
        String newDescription = req.getParameter("description");
        boolean delete;
        if(req.getParameter("delete") != null) {
            delete = true;
        } else {
            delete = false;
        }
        System.out.println(user);
        System.out.println(newTitle);
        System.out.println(newDescription);
        System.out.println(delete);
        resp.sendRedirect("/ads");
    }
}
