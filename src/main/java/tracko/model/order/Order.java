package tracko.model.order;

import java.time.LocalDateTime;

public class Order {
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final String item;
    private final Integer quantity;
    private final LocalDateTime timeCreated;

    public Order(Name name, Phone phone, Email email, Address address, String item, Integer quantity) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.item = item;
        this.quantity = quantity;
        this.timeCreated = LocalDateTime.now();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
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
