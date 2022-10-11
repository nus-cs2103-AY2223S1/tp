package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.*;
import seedu.address.model.person.Buyer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final JsonAdaptedBuyer buyer;
    private final JsonAdaptedPriceRange requestedPriceRange;
    private final JsonAdaptedRequest request;
    private final List<String> additionalRequests = new ArrayList<>();
    private final String byDate;
    private final Double settledPrice;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order detail}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("buyer") JsonAdaptedBuyer buyer,
                            @JsonProperty("range") JsonAdaptedPriceRange range,
                            @JsonProperty("request") JsonAdaptedRequest request,
                            @JsonProperty("additional") List<String> additional,
                            @JsonProperty("byDate") String byDate,
                            @JsonProperty("settledPrice") Double settledPrice,
                            @JsonProperty("status") String status) {
       this.buyer = buyer;
       this.requestedPriceRange = range;
       this.request = request;
       if (additional != null) {
           this.additionalRequests.addAll(additional);
       }
       this.byDate = byDate;
       this.settledPrice = settledPrice;
       this.status = status;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.buyer = new JsonAdaptedBuyer(order.getBuyer());
        this.requestedPriceRange = new JsonAdaptedPriceRange(order.getRequestedPriceRange());
        this.request = new JsonAdaptedRequest(order.getRequest());
        this.additionalRequests.addAll(order.getAdditionalRequests().getAdditionalRequests());
        this.byDate = order.getByDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.settledPrice = order.getSettledPrice().getPrice();
        this.status = order.getOrderStatus().getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        //TODO validate the data before converting
        Buyer modelBuyer = buyer.toModelType();
        PriceRange modelPriceRange = requestedPriceRange.toModelType();
        Request modelRequest = request.toModelType();
        AdditionalRequests modelAdditionalRequest = new AdditionalRequests(additionalRequests.toArray(new String[0]));
        LocalDate modelByDate = LocalDate.parse(byDate);
        Price modelPrice = new Price(settledPrice);
        OrderStatus modelOrderStatus = new OrderStatus(status);
        return new Order(modelBuyer, modelPriceRange, modelRequest, modelAdditionalRequest, modelByDate, modelPrice,
                modelOrderStatus);
    }

}
