package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.core.index.UniqueIdGenerator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Buyer;

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
    private final String uniqueId;

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
                            @JsonProperty("status") String status,
                            @JsonProperty("uniqueId") String uniqueId) {
        this.buyer = buyer;
        this.requestedPriceRange = range;
        this.request = request;
        if (additional != null) {
            this.additionalRequests.addAll(additional);
        }
        this.byDate = byDate;
        this.settledPrice = settledPrice;
        this.status = status;
        this.uniqueId = uniqueId;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.buyer = new JsonAdaptedBuyer(order.getBuyer());
        this.requestedPriceRange = new JsonAdaptedPriceRange(order.getRequestedPriceRange());
        this.request = new JsonAdaptedRequest(order.getRequest());
        this.additionalRequests.addAll(order.getAdditionalRequests().getAdditionalRequestsToString());
        this.byDate = order.getByDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.settledPrice = order.getSettledPrice().getPrice();
        this.status = order.getOrderStatus().getStatus();
        this.uniqueId = order.getId().getIdToString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (buyer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Buyer.class.getSimpleName()));
        }
        Buyer modelBuyer = buyer.toModelType();

        if (requestedPriceRange == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PriceRange.class.getSimpleName()));
        }
        PriceRange modelPriceRange = requestedPriceRange.toModelType();

        if (request == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Request.class.getSimpleName()));
        }
        Request modelRequest = request.toModelType();

        if (additionalRequests == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AdditionalRequests.class.getSimpleName()));
        }
        AdditionalRequests modelAdditionalRequest = new AdditionalRequests(additionalRequests.toArray(new String[0]));

        if (byDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        LocalDate modelByDate;
        try {
            modelByDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        if (settledPrice == null || (!Price.isNotApplicablePrice(new Price(settledPrice))
                && settledPrice < 0)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        Price modelPrice = new Price(settledPrice);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderStatus.class.getSimpleName()));
        }
        OrderStatus modelOrderStatus = Arrays.stream(OrderStatus.class.getEnumConstants())
                .filter(x -> x.toString().equals(status))
                .findFirst()
                .orElse(OrderStatus.PENDING);

        if (uniqueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UniqueId.class.getSimpleName()));
        }
        UniqueId modelUniqueId = new UniqueId(uniqueId);
        if (UniqueIdGenerator.storedIdOrderContains(modelUniqueId)) {
            throw new IllegalValueException("Repeated unique id for pet");
        }
        return new Order(modelBuyer, modelPriceRange, modelRequest, modelAdditionalRequest, modelByDate, modelPrice,
                modelOrderStatus, modelUniqueId);
    }

}
