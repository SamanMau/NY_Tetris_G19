package Control;

import java.sql.*;

public class DatabaseController {
    private String userName;
    private String userPassword;

    public int validateUserLoginInfo(String userName, String userPassword){
        int number = 0;
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call loginUser(?, ?)}")) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, userName);
            callableStatement.setString(3, userPassword);

            callableStatement.executeUpdate();
            number = callableStatement.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public String validateUserRegisterInfo(String userName, String userPassword){
        String namee;
        String status = "Beginner";
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call registerUser(?, ?, ?)}")) {
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, userName);
            callableStatement.setString(3, userPassword);
            callableStatement.setString(4, status);

            callableStatement.executeUpdate();
            namee = callableStatement.getString(1);
            return namee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getUserPoints(int id){
        int points;
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call getPoints(?)}")) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, id);

            callableStatement.executeUpdate();
            points = callableStatement.getInt(1);
            return points;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getUserID(String name){
        int userID;
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call getUserID(?)}")) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, name);

            callableStatement.executeUpdate();
            userID = callableStatement.getInt(1);
            return userID;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getStatus(int id){
        String status;
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call getStatus(?)}")) {
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2, id);

            callableStatement.executeUpdate();
            status = callableStatement.getString(1);
            return status;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getTotalChallenges(int id){
        int totalChallenges;
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

             CallableStatement callableStatement = con.prepareCall("{ ? = call getAmountChallenges(?)}")) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, id);

            callableStatement.executeUpdate();
            totalChallenges = callableStatement.getInt(1);
            return totalChallenges;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getTotalGames(int id){
        int totalGames;

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");
             PreparedStatement pstmt = con.prepareStatement("SELECT amount_games FROM users WHERE user_id = ?")) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalGames = rs.getInt("amount_games");
                    return totalGames;
                }
            }
        } catch (SQLException e) {
        }

        return 0;
    }

    public void updatePoints(int id, int points){
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

            CallableStatement callableStatement = con.prepareCall("call updatePoints(?, ?)");
            callableStatement.setInt(1, id);
            callableStatement.setInt(2, points);
            callableStatement.executeUpdate();

            callableStatement.close();
            con.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateAmountGames(int id){
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

            CallableStatement callableStatement = con.prepareCall("call updateAmountGames(?)");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();

            callableStatement.close();
            con.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateStatus(String status, int id){
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://pgserver.mau.se:5432/am7210", "am7210", "96xpm6t2");

            CallableStatement callableStatement = con.prepareCall("call updateStatus(?, ?)");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, status);
            callableStatement.executeUpdate();

            callableStatement.close();
            con.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



}
