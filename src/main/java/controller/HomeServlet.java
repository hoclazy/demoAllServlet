package controller;

import model.Customer;
import model.Order;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/homes")
public class HomeServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        List<Customer> customers = customerService.findAll();
        List<Order> orders = orderService.findAll();
        if (findName!=null) {
            customers = customerService.findByName(findName);
        }
        request.setAttribute("customers", customers);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
