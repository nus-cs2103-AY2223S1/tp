package seedu.address.model.order;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.model.person.Buyer;

public class Order {

    private String description; //TODO Remove this temp stub
    private final Buyer buyer;
    private final PriceRange requestedPriceRange;
    private final Request request;
    private final AdditionalRequests additionalRequests;
    private final LocalDate byDate;
    private final Price settledPrice;
    private final OrderStatus status;

    public Order(String description) { // TODO Remove this stub
        this.buyer = null;
        this.requestedPriceRange = null;
        this.request = null;
        this.additionalRequests = null;
        this.byDate = null;
        this.settledPrice = null;
        this.status = null;
        this.description = description;
    }

    public Order(Buyer buyer, PriceRange requestedPriceRange,
                 Request request, AdditionalRequests additionalRequests,
                 LocalDate byDate, Price settledPrice, OrderStatus status) {
        this.buyer = buyer;
        this.requestedPriceRange = requestedPriceRange;
        this.request = request;
        this.additionalRequests = additionalRequests;
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        this.status = status;
    }

    public Order(Buyer buyer, PriceRange requestedPriceRange,
                 Request request, AdditionalRequests additionalRequests,
                 LocalDate byDate, Price settledPrice) {
        this.buyer = buyer;
        this.requestedPriceRange = requestedPriceRange;
        this.request = request;
        this.additionalRequests = additionalRequests;
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        status = OrderStatus.getPendingStatus();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public PriceRange getRequestedPriceRange() {
        return requestedPriceRange;
    }

    public Request getRequest() {
        return request;
    }

    public AdditionalRequests getAdditionalRequests() {
        return additionalRequests;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public Price getSettledPrice() {
        return settledPrice;
    }

    public OrderStatus getOrderStatus() {
        return status;
    }

    public void updateRequestedPriceRange(Price upperBound, Price lowerBound) {
        requestedPriceRange.updatePriceRange(upperBound, lowerBound);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Order) {
            Order otherOrder = (Order) other;
            return buyer.equals(otherOrder.getBuyer())
                    && requestedPriceRange.equals(otherOrder.getRequestedPriceRange())
                    && request.equals(otherOrder.getRequest())
                    && additionalRequests.equals(otherOrder.getAdditionalRequests())
                    && byDate.equals(otherOrder.getByDate())
                    && settledPrice.equals(otherOrder.getSettledPrice())
                    && status.equals(otherOrder.getOrderStatus());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer, requestedPriceRange, request,
                additionalRequests, byDate, settledPrice, status);
    }

    @Override
    public String toString() {
        return description;

        //TODO Uncomment this
//        StringBuilder builder = new StringBuilder();
//        builder.append("RequestedPriceRange: ").append(getRequestedPriceRange())
//                .append(System.lineSeparator())
//                .append("Process order by: ").append(getByDate())
//                .append(System.lineSeparator())
//                .append("=== Request ===").append(System.lineSeparator())
//                .append(request.toString()).append(System.lineSeparator())
//                .append("==========").append(System.lineSeparator())
//                .append("=== Additional Requests ===").append(System.lineSeparator())
//                .append(additionalRequests.toString()).append(System.lineSeparator())
//                .append("==========").append(System.lineSeparator())
//                .append("Settled price: ").append(getSettledPrice())
//                .append("Status: ").append(getOrderStatus());
//        return builder.toString();
    }

    public int compareTo(Order order) {
        return this.byDate.compareTo(order.byDate);
    }
}
