package pl.camp.it;

import pl.camp.it.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        readData();

    }


    public static void saveData(Customer customer) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://79.96.53.155:3306/18942511_itcamp?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "18942511_itcamp","passitcamp2");

            Statement statement = con.createStatement();

            statement.executeUpdate("insert into tcustomer (name, surname, pesel) values ('" + customer.getName() + "', '" + customer.getSurname() + "', " + customer.getPesel() + ")");
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static List<Customer> readData() {

        List<Customer> resultList = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://79.96.53.155:3306/18942511_itcamp?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "18942511_itcamp","passitcamp2");

            Statement stmt = con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tcustomer");
            while(rs.next()) {


                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPesel(rs.getString("pesel"));

                resultList.add(customer);

                System.out.println("-----------------");
                System.out.println("Klient: ");
                System.out.println(customer.getId());
                System.out.println(customer.getName());
                System.out.println(customer.getSurname());
                System.out.println(customer.getPesel());
            }

            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }
}