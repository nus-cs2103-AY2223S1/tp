package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Buyer;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

/**
 * A utility class to help with building Person objects.
 */
public class OrderBuilder {

    public static final Price DEFAULT_LOWER_PRICE = new Price(200.00);
    public static final Price DEFAULT_UPPER_PRICE = new Price(500.00);
    public static final Age DEFAULT_REQUESTED_AGE = new Age(1);
    public static final Color DEFAULT_REQUESTED_COLOR = new Color("Black");
    public static final ColorPattern DEFAULT_REQUESTED_COLOR_PATTERN = new ColorPattern("Striped");
    public static final Species DEFAULT_REQUESTED_SPECIES = new Species("European shorthair");
    public static final String DEFAULT_ADDITIONAL_REQUEST_1 = "Vaccinated";
    public static final String DEFAULT_ADDITIONAL_REQUEST_2 = "Kid-friendly";
    public static final LocalDate DEFAULT_BYDATE = LocalDate.of(2022, 12, 20);
    public static final double DEFAULT_SETTLED_PRICE = 400.00;
    public static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.PENDING;

    private Buyer buyer;
    private PriceRange requestedPriceRange;
    private Request request;
    private AdditionalRequests additionalRequests;
    private LocalDate byDate;
    private Price settledPrice;
    private OrderStatus status;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        buyer = TypicalBuyers.ALICE;
        requestedPriceRange = new PriceRange(DEFAULT_LOWER_PRICE, DEFAULT_UPPER_PRICE);
        request = new Request(DEFAULT_REQUESTED_AGE, DEFAULT_REQUESTED_COLOR, DEFAULT_REQUESTED_COLOR_PATTERN,
                DEFAULT_REQUESTED_SPECIES);
        additionalRequests = new AdditionalRequests(DEFAULT_ADDITIONAL_REQUEST_1,
                DEFAULT_ADDITIONAL_REQUEST_2);
        byDate = DEFAULT_BYDATE;
        settledPrice = new Price(DEFAULT_SETTLED_PRICE);
        status = DEFAULT_ORDER_STATUS;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        buyer = orderToCopy.getBuyer();
        requestedPriceRange = orderToCopy.getRequestedPriceRange();
        request = orderToCopy.getRequest();
        additionalRequests = orderToCopy.getAdditionalRequests();
        settledPrice = orderToCopy.getSettledPrice();
        status = orderToCopy.getOrderStatus();
    }

    /**
     * Sets the {@code Buyer} of the {@code Order} that we are building.
     */
    public OrderBuilder withBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    /**
     * Sets the {@code RequestedPriceRange} of the {@code Order} that we are building.
     */
    public OrderBuilder withRequestedPriceRange(double lowerBound, double upperBound) {
        this.requestedPriceRange = new PriceRange(new Price(lowerBound), new Price(upperBound));
        return this;
    }

    /**
     * Sets the {@code Request} of the {@code Order} that we are building.
     */
    public OrderBuilder withRequest(Age age, Color color, ColorPattern colorPattern, Species species) {
        this.request = new Request(age, color, colorPattern, species);
        return this;
    }

    /**
     * Sets the {@code AdditionalRequests} of the {@code Person} that we are building.
     */
    public OrderBuilder withAdditionalRequests(String... additionalRequests) {
        this.additionalRequests = new AdditionalRequests(additionalRequests);
        return this;
    }

    /**
     * Sets the {@code ByDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withByDate(int year, int month, int day) {
        this.byDate = LocalDate.of(year, month, day);
        return this;
    }

    /**
     * Sets the {@code SettledPrice} of the {@code Order} that we are building.
     */
    public OrderBuilder withSettledPrice(double settledPrice) {
        this.settledPrice = new Price(settledPrice);
        return this;
    }

    /**
     * Sets the {@code SettledPrice} of the {@code Order} that we are building.
     */
    public OrderBuilder withStatus(String status) {
        if (status.equals("Pending")) {
            this.status = OrderStatus.PENDING;
        } else if (status.equals("Negotiating")) {
            this.status = OrderStatus.NEGOTIATING;
        } else if (status.equals("Delivering")) {
            this.status = OrderStatus.DELIVERING;
        }
        return this;
    }

    public Order build() {
        return new Order(buyer, requestedPriceRange, request, additionalRequests, byDate, settledPrice, status);
    }
}
