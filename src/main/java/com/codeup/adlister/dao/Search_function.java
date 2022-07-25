@Override
public List<Ad> searchAds(String searchTerm) {
        String query = "SELECT * FROM ads JOIN users on ads.user_id = users.id WHERE ads.title LIKE ? OR ads.description LIKE ?";
        String query = "SELECT * FROM ads JOIN users ON ads.user_id = users.id WHERE ads.title LIKE ? OR ads.description LIKE ?";
        try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + searchTerm + "%");
        public List<Ad> searchAds(String searchTerm) {

@Override
public Ad findById(Long id) {
        String query = "SELECT * FROM ads JOIN users ON ads.user_id = users.id WHERE ads.id = ?";
        String query = "SELECT * FROM ads JOIN users ON ads.user_id = users.id JOIN categories ON ads.category_id = categories.id WHERE ads.id = ?";
        PreparedStatement stmt;
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

@Override
public List<Ad> SearchAdsByCategory(Long id) {
        String query = "SELECT * FROM ads JOIN categories ON ads.category_id = categories.id WHERE ads.id = ?";
        String query = "SELECT * FROM ads JOIN categories ON ads.category_id = categories.id JOIN users ON ads.user_id = users.id WHERE categories.id = ?";
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

@Override
public List<Ad> showUserAds(Long id) {
        String query = "SELECT * FROM ads JOIN users ON ads.user_id = users.id WHERE users.id = ?";
        String query = "SELECT * FROM ads JOIN categories ON ads.category_id = categories.id JOIN users ON ads.user_id = users.id WHERE users.id = ?";
        PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        return createAdsWithUsersFromResults(rs);
        return createAdsWithUsersCategories(rs);
        } catch (SQLException e) {
        throw new RuntimeException("No user found in search", e);

        @@ public Boolean delete(Long id) {

@Override
public void update(Ad ad) {
        String query = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
        String query = "UPDATE ads SET title = ?, description = ?, category_id = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
        stmt = connection.prepareStatement(query);
        stmt.setString(1, ad.getTitle());
        stmt.setString(2, ad.getDescription());
//            stmt.setLong(3, ad.getCategoryId());
        stmt.setLong(3, ad.getId());
        stmt.setLong(3, ad.getCategoryId());
        stmt.setLong(4, ad.getId());
        stmt.executeUpdate();
        } catch (SQLException e) {
        throw new RuntimeE