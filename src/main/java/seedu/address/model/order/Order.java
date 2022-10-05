package seedu.address.model.order;

import java.time.LocalDate;

import seedu.address.model.person.Buyer;

public class Order {

    private enum Status {
        PENDING,
        NEGOTIATING,
        DELIVERING
    }
    private Buyer buyer;
    private PriceRange requestedPriceRange;
    private LocalDate byDate;
    private Price settledPrice;
    private Status status;


}
