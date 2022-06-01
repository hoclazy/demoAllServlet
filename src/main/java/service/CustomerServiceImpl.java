package service;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSl=false", "root", "Dinhhoc8");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select*from customer");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                customers.add(new Customer(id, name, age));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    private void printSQLException(SQLException e) {
    }

    @Override
    public void add(Customer customer) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id, name, age) values (?, ?, ?)");) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public boolean update(Customer c) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("update customer set name = ?,age= ? where id = ?;");) {
            statement.setString(1, c.getName());
            statement.setInt(2, c.getAge());
            statement.setInt(3, c.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) {
boolean rowdelete = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where id = ?");) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement); //in ra câu truy vấn.
           rowdelete= preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
        }
        return rowdelete;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = new Customer();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from customer where id=?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCustomer = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                customer = new Customer(idCustomer, name, age);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List findByName(String findName) {
        List<Customer> customers = new ArrayList<>();
        try {Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select *from customer where name like ?");
            preparedStatement.setString(1, "%" +findName+ "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                customers.add(new Customer(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}