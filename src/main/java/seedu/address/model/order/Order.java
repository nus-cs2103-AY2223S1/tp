package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
import seedu.address.model.person.Buyer;

/**
 * Abstracts an order.
 */
public class Order {

    private static final UniqueIdGenerator ORDER_ID_GENERATOR = new UniqueIdGenerator();

    private final UniqueId id;
    private Buyer buyer;
    private final PriceRange requestedPriceRange;
    private final Request request;
    private final AdditionalRequests additionalRequests;
    private final LocalDate byDate;
    private final Price settledPrice;
    private final OrderStatus status;

    /**
     * Constructs an order object.
     *
     * @param buyer The buyer who initiates the request.
     * @param requestedPriceRange The acceptable price range during negotiation.
     * @param request The description of the request, that is, what kind of pet the buyer wants.
     * @param additionalRequests Some other requests in string.
     * @param byDate The date before which the order should be dealt.
     * @param settledPrice The settled final price.
     * @param status Whether this order is under negotiation, or finished, or is being delivered, etc.
     */
    public Order(Buyer buyer,
                 PriceRange requestedPriceRange,
                 Request request,
                 AdditionalRequests additionalRequests,
                 LocalDate byDate,
                 Price settledPrice,
                 OrderStatus status) {
        requireAllNonNull(status);
        UniqueId currId = ORDER_ID_GENERATOR.next();
        while (UniqueIdGenerator.storedIdOrderContains(currId)) {
            currId = ORDER_ID_GENERATOR.next();
        }
        this.id = currId;
        this.buyer = buyer;
        this.requestedPriceRange = requestedPriceRange;
        this.request = request;
        this.additionalRequests = additionalRequests;
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        this.status = status;
    }

    /**
     * Constructs an order object.
     * The status is by default Pending.
     *
     * @param buyer The buyer who initiates the request.
     * @param requestedPriceRange The acceptable price range during negotiation.
     * @param request The description of the request, that is, what kind of pet the buyer wants.
     * @param additionalRequests Some other requests in string.
     * @param byDate The date before which the order should be dealt.
     * @param settledPrice The settled final price.
     */
    public Order(Buyer buyer,
                 PriceRange requestedPriceRange,
                 Request request,
                 AdditionalRequests additionalRequests,
                 LocalDate byDate,
                 Price settledPrice) {
        UniqueId currId = ORDER_ID_GENERATOR.next();
        while (UniqueIdGenerator.storedIdOrderContains(currId)) {
            currId = ORDER_ID_GENERATOR.next();
        }
        this.id = currId;
        this.buyer = buyer;
        this.requestedPriceRange = requestedPriceRange;
        this.request = request;
        this.additionalRequests = additionalRequests;
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        status = OrderStatus.PENDING;
    }

    /**
     * Constructs an order object.
     *
     * @param buyer The buyer who initiates the request.
     * @param requestedPriceRange The acceptable price range during negotiation.
     * @param request The description of the request, that is, what kind of pet the buyer wants.
     * @param additionalRequests Some other requests in string.
     * @param byDate The date before which the order should be dealt.
     * @param settledPrice The settled final price.
     * @param status Whether this order is under negotiation, or finished, or is being delivered, etc.
     * @param uniqueId The assigned uniqueId for this order.
     */
    public Order(Buyer buyer,
                 PriceRange requestedPriceRange,
                 Request request,
                 AdditionalRequests additionalRequests,
                 LocalDate byDate,
                 Price settledPrice,
                 OrderStatus status,
                 UniqueId uniqueId) {
        requireAllNonNull(status);
        UniqueIdGenerator.addToStoredIdOrder(uniqueId);
        this.id = uniqueId;
        this.buyer = buyer;
        this.requestedPriceRange = requestedPriceRange;
        this.request = request;
        this.additionalRequests = additionalRequests;
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        this.status = status;
    }

    /**
     * Gets the buyer
     *
     * @return The buyer of this order.
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * Gets the price range of the order.
     *
     * @return The price range.
     */
    public PriceRange getRequestedPriceRange() {
        return requestedPriceRange;
    }

    /**
     * Gets the request of the order.
     *
     * @return The request.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Gets the additional requests of the order.
     *
     * @return The additional request.
     */
    public AdditionalRequests getAdditionalRequests() {
        return additionalRequests;
    }

    /**
     * Gets the date of the order.
     *
     * @return The date in {@code LocalDate}.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Gets the settled price of the order.
     *
     * @return The price.
     */
    public Price getSettledPrice() {
        return settledPrice;
    }

    /**
     * Gets the status of the order.
     *
     * @return The status.
     */
    public OrderStatus getOrderStatus() {
        return status;
    }

    public UniqueId getId() {
        return id;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Updates the price range changed during negotiation.
     *
     * @param upperBound The price is not greater than it.
     * @param lowerBound The price is not smaller than it.
     */
    public void updateRequestedPriceRange(Price upperBound, Price lowerBound) {
        requestedPriceRange.updatePriceRange(upperBound, lowerBound);
    }

    /**
     * Compares an order with another order in default way in terms of the due date.
     * @param order The other order being compared.
     * @return The method returns 0 if the order and the other order has the same due date.
     *      A value less than 0 is returned if the order has earlier due date than the other order,
     *      and a value greater than 0 if the order has later due date than the other order.
     */
    public int compareTo(Order order) {
        return this.byDate.compareTo(order.byDate);
    }

    /**
     * Returns true if the two uniqueId matches.
     */
    public boolean hasId(UniqueId id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Order) {
            Order otherOrder = (Order) other;
            return id.equals(otherOrder.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Requested Price Range: ").append(getRequestedPriceRange())
                .append(System.lineSeparator())
                .append("Settled price: ").append(getSettledPrice())
                .append(System.lineSeparator())
                .append("Status: ").append(getOrderStatus())
                .append(System.lineSeparator())
                .append("Process order by: ").append(getByDate())
                .append(System.lineSeparator())
                .append("=== Request ===").append(System.lineSeparator())
                .append(request.toString()).append(System.lineSeparator())
                .append("==========").append(System.lineSeparator())
                .append("=== Additional Requests ===").append(System.lineSeparator())
                .append(additionalRequests.toString()).append(System.lineSeparator())
                .append("==========");
        return builder.toString();

    }

}
