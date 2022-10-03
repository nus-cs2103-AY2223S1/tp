package tracko.model.order;

import java.time.LocalDateTime;

public class Order {
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String item;
    private final Integer quantity;
    private final LocalDateTime timeCreated;

    public Order(String name, String phone, String email, String address, String item, Integer quantity) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.item = item;
        this.quantity = quantity;
        this.timeCreated = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }


}
