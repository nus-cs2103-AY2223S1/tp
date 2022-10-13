package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_BUYER = "Buyers list contains duplicate buyer(s).";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "Suppliers list contains duplicate supplier(s).";
    public static final String MESSAGE_DUPLICATE_DELIEVER = "Deliverers list contains duplicate deliverer(s).";
    public static final String MESSAGE_DUPLICATE_PET = "Pets list contains duplicate pet(s).";
    public static final String MESSAGE_DUPLICATE_ORDER = "Orders list contains duplicate order(s).";

    private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();
    private final List<JsonAdaptedSupplier> suppliers = new ArrayList<>();
    private final List<JsonAdaptedDeliverer> deliverers = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("buyers") List<JsonAdaptedBuyer> buyers,
                                       @JsonProperty("suppliers") List<JsonAdaptedSupplier> suppliers,
                                       @JsonProperty("deliverers") List<JsonAdaptedDeliverer> deliverers,
                                       @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.buyers.addAll(buyers);
        this.suppliers.addAll(suppliers);
        this.deliverers.addAll(deliverers);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        buyers.addAll(source.getBuyerList().stream().map(JsonAdaptedBuyer::new).collect(Collectors.toList()));
        suppliers.addAll(source.getSupplierList().stream().map(JsonAdaptedSupplier::new).collect(Collectors.toList()));
        deliverers.addAll(source.getDelivererList().stream().map(JsonAdaptedDeliverer::new)
                .collect(Collectors.toList()));
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            if (addressBook.hasBuyer(buyer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BUYER);
            }
            addressBook.addBuyer(buyer);
        }
        for (JsonAdaptedSupplier jsonAdaptedSupplier : suppliers) {
            Supplier supplier = jsonAdaptedSupplier.toModelType();
            if (addressBook.hasSupplier(supplier)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SUPPLIER);
            }
            addressBook.addSupplier(supplier);
        }
        for (JsonAdaptedDeliverer jsonAdaptedDeliverer: deliverers) {
            Deliverer deliverer = jsonAdaptedDeliverer.toModelType();
            if (addressBook.hasDeliverer(deliverer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DELIEVER);
            }
            addressBook.addDeliverer(deliverer);
        }
        for (JsonAdaptedOrder jsonAdaptedOrder: orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (addressBook.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DELIEVER);
            }
            addressBook.addOrder(order);
        }
        return addressBook;
    }

}
