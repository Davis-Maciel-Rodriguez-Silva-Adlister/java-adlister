package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.PreparedStatement;
import com.mysql.jdbc.Driver;
import com.sun.tools.javac.util.List;

import javax.servlet.jsp.jstl.core.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Searches by Ad strings
public class MySQLAdsDao implements Ads {
        private Connection connection = null;

        public MySQLAdsDao(Config config) {
                try {
                        DriverManager.registerDriver(new Driver());
                        connection = DriverManager.getConnection(
                                config.getUrl(),
                                config.getUser(),
                                config.getPassword()
                        );
                } catch (SQLException e) {
                        System.out.println(e);
                        throw new RuntimeException("Error connecting to database", e);
                }
        }
@Override
public List<Ad> searchAds(String searchTerm) {
        String query = "SELECT * FROM ads WHERE ads.title LIKE ? OR ads.description LIKE ?";
        try {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);
        stmt.setString(1, "%" + searchTerm + "%");
      }catch(SQLException e){

        }
// Searches by ID
@Override
public Ad findById(Long id) {
        String query = "SELECT * FROM ads JOIN users ON ads.user_id = users.id WHERE ads.id = ?";
        java.sql.PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return extractAds(rs);
        return extractAdsWithUserCategories(rs);

        } catch (SQLException e) {
        e.printStackTrace();

        public Ad findById(Long id) {
// Searches from Category
@Override
public List<Ad> SearchAdsByCategory(Long id) {
        String query = "SELECT * FROM ads ON ads.category_id = categories.id WHERE ads.id = ?";
        PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return createAdsFromResults(rs);
        return createAdsWithUsersCategories(rs);

        } catch (SQLException e) {
                e.printStackTrace();
                public List<Ad> searchAdsByCategory (Long id)
        }
//Search for by user Id is:
@Override
public List<Ad> showUserAds(Long id) {
        String query = "SELECT * FROM ads ON ads.user_id = users.id WHERE users.id = ?";
        PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        return createAdsWithUsersFromResults(rs);
        } catch (SQLException e) {
        throw new RuntimeException("No user found in search", e);
       {

        private void createAdsFromResults() {
        }

        private void createAdsWithUsersCategories() {
        }