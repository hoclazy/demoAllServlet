package service;

import java.util.List;

public interface CustomerService <Customer>{
    public List<model.Customer> findAll();

    public void add(model.Customer customer);

    public boolean update(model.Customer id);

    public boolean delete(int id);

    public model.Customer findById(int id);

    public List<model.Customer> findByName(String name);
}
