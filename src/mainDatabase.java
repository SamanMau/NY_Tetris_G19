import java.sql.*;

public class mainDatabase {
    public static void main(String[] args) {
        mainDatabase mainDatabase = new mainDatabase();
    }

    public mainDatabase(){

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

            Statement statement = conn.createStatement();    //lagt till denna under funktioner.
            String view = "select * from users";
            ResultSet rs = statement.executeQuery(view);

            while (rs.next()) {
                int code = rs.getInt("user_id");
                String product_name = rs.getString("name");
                int quantity = rs.getInt("password");
                int basePrice = rs.getInt("picture_id");
                int discounted_price = rs.getInt("all_points");
                String supplier_of_product = rs.getString("status");
                int id_of_supplier = rs.getInt("amount_challenges");
                int discount_id = rs.getInt("amount_games");
                System.out.println("Product code: " + code + " | Product name" + product_name + " | Quantity_left" + quantity + " | basePrice: " + basePrice +
                        " | Discounted_price: " + discounted_price + " | Supplied by: " + supplier_of_product + " | Supplier ID: " + id_of_supplier +
                        " | " + discount_id);

                System.out.println("----------------------------------------------------------");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se:5432/am7210";
        String user = "am7210";
        String password = "96xpm6t2";
        //String user1 = "admin";
        //String user2 = "customer";

        //String url = "jdbc:postgresql://localhost/hr_ht23";
        //Properties props = new Properties();
        //props.setProperty("user", "johan_ht23");
        //props.setProperty("password", "12345");

        try {
            //Class.forName("org.postgresql.Driver");    // kanske inte behöver.
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established");
            return con;

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }
}
