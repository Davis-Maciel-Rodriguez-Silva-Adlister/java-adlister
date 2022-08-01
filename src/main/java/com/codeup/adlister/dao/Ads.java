package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> search(String searchTerm);

    List<Ad> filterByUser(long id);

    Ad getAd(long id);

    void deleteAd(long id);

    void changeDescription(long id, String newDescription);

    void changeTitle(long id, String newTitle);
}
