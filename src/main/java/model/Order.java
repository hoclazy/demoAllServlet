package model;

public class Order {
    private int id;
    private String time;
    private int total;
    private Customer customerd;

    public Order() {
    }


    public Order(int id, String time, int total, Customer customerd) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.customerd = customerd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Customer getCustomerd() {
        return customerd;
    }

    public void setCustomerd(Customer customerd) {
        this.customerd = customerd;
    }
}
