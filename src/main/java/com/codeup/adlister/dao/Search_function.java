import com.mysql.cj.jdbc.PreparedStatement;
import com.mysql.jdbc.Driver;

import javax.servlet.jsp.jstl.core.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Searches by Ad strings
public class MySQLAdsDao implements Ads{
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
                        throw new RuntimeException("error connecting to database", e);
                }
        }
@Override
public List<Ad> searchAds(String searchTerm) {
        String query = "SELECT * FROM ads WHERE ads.title LIKE ? OR ads.description LIKE ?";
        try {
        PreparedStatement stmt = connection.prepareStatement(query);
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
        public List<Ad> searchAdsByCategory(Long id) {
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
        return createAdsWithUsersCategories(rs);
        } catch (SQLException e) {
        throw new RuntimeException("No user found in search", e);
       {

@Override
public void update(Ad ad) {
        String query = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setString(1, ad.getTitle());
        stmt.setString(2, ad.getDescription());
        stmt.setLong(3, ad.getId());
        stmt.setLong(3, ad.getCategoryId());
        stmt.setLong(4, ad.getId());
        stmt.executeUpdate();
        } catch (SQLException e) {
        throw new RuntimeE