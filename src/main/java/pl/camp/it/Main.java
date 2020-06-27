package pl.camp.it;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        readData();

    }


    public static void saveData() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://79.96.53.155:3306/18942511_itcamp?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "18942511_itcamp","passitcamp2");

            Statement statement = con.createStatement();

            statement.executeUpdate("insert into tcustomer (name, surname, pesel) values ('Marian', 'Paździoch', 4848451)");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void readData() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection( //tworzy się obiekt odpowidzialny za połączenie z bazą
                    "jdbc:mysql://79.96.53.155:3306/18942511_itcamp?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "18942511_itcamp","passitcamp2"); //protokół://ip:port//nazwa, user, pass

            Statement stmt = con.createStatement(); //zapytanie do bazy

            ResultSet rs=stmt.executeQuery("select * from tcustomer"); //ResultSet to to co baza nam zwraca
            while(rs.next()) {//sprawdza czy jest kolejny wiersz, jeżeli tak to staje się tym kolejnym wierszem
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String pesel = rs.getString("pesel");

                System.out.println("-----------------");
                System.out.println("Klient: ");
                System.out.println(id);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(pesel);
            }

            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}