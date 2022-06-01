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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                showCreateForm(request,response);
                break;
            default:
                showList(request,response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> customers = customerService.findAll();
        request.setAttribute("customerse", customers);
        request.getRequestDispatcher("order/create.jsp").forward(request,response);

    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("order/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                try {
                    create(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":

            default:
                showList(request,response);
        }

    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String time = request.getParameter("time");
        int total = Integer.parseInt(request.getParameter("total"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customerd = customerService.findById(customerId);
        orderService.add(new Order(id, time, total, customerd));
        response.sendRedirect("/homes");
    }
}
