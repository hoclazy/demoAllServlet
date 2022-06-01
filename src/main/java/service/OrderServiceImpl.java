package service;

import model.Customer;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    CustomerService customerService = new CustomerServiceImpl();
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
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from demo2006.order");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String time = rs.getString("time");
                int total = rs.getInt("total");
                int idCustomer = rs.getInt("customerId");
                Customer customerd = (Customer) customerService.findById(idCustomer);
                orders.add(new Order(id, time, total, customerd));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }

    @Override
    public void add(Order order) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into demo2006.order (id,time,total,customerId) values (?,?,?,?)");) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getTime());
            preparedStatement.setInt(3, order.getTotal());
            preparedStatement.setInt(4, order.getCustomerd().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e);
        }

    }


    private void printSQLException(SQLException e) {
    }


    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }



    @Override
    public Order findById(int id) {
        Order order = new Order();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from order where id=?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt("id");
                String time = rs.getString("time");
                int total = rs.getInt("total");
                int idCustomer = rs.getInt("CustomerId");
                Customer customer1 = customerService.findById(idCustomer);
                order = new Order(idOrder, time, total, customer1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return order;
    }

    @Override
    public List findByName(String name) {
        return null;
    }
}
