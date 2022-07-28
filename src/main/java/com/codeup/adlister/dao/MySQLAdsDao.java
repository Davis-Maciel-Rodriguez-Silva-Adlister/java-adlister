package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    public List<Ad> search(String searchTerm) {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM ads WHERE ads.title LIKE ? OR ads.description LIKE ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error in search");
        }
    }

    @Override
    public List<Ad> filterByUser(long id) {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM ads WHERE ads.user_id = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error in search");
        }
    }

    @Override
    public Ad getAd(long id) {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Ad (
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad");
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    public List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    public void deleteAd(long id) {
        PreparedStatement stmt = null;
        String query = "DELETE FROM ads WHERE ads.id = ?";
        try {
           stmt = connection.prepareStatement(query);
           stmt.setLong(1, id);
           stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error in deletion");
        }
    }

    public void changeTitle(long id,  String newTitle) {
        PreparedStatement stmt = null;
        String query = "UPDATE ads SET title = ? WHERE id= ?";
                try{
                    stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    stmt.setLong(2, id);
                    stmt.setString(1, newTitle);
                    stmt.executeUpdate();
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException("Error updating Title");
                }
    }

    public void changeDescription(long id,  String newDescription) {
        PreparedStatement stmt = null;
        String query = "UPDATE ads SET description = ? WHERE id= ?";
                try{
                    stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    stmt.setLong(2, id);
                    stmt.setString(1, newDescription);
                    stmt.executeUpdate();
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException("Error updating Description");
                }
    }
}
