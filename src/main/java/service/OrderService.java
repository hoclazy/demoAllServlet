package service;

import model.Order;

import java.util.List;

public interface OrderService<Order> {
    public List<Order> findAll();

    public void add(model.Order order);

    public boolean update(int id);

    public boolean delete(model.Order order);

    public model.Order findById(int id);

    public List<model.Order> findByName(String name);
}
