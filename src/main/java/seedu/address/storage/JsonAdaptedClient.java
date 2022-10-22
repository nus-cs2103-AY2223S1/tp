package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";
    private static final String MESSAGE_DUPLICATE_REMARK = "Client contains duplicate remark(s).";

    private final String name;
    private final String address;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedRemark> remarks = new ArrayList<>();
    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("address") String address,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("remarks") List<JsonAdaptedRemark> remarks,
                             @JsonProperty("transactions") List<JsonAdaptedTransaction> transactions) {
        this.name = name;
        this.remarks.addAll(remarks);
        this.transactions.addAll(transactions);
        this.address = address;
        this.phone = phone;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        remarks.addAll(source.getRemarkList().stream().map(JsonAdaptedRemark::new).collect(Collectors.toList()));
        transactions.addAll(source.getTransactionList().stream()
                .map(JsonAdaptedTransaction::new).collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientPhone.class.getSimpleName()));
        }
        if (!ClientPhone.isValidPhone(phone)) {
            throw new IllegalValueException(ClientPhone.MESSAGE_CONSTRAINTS);
        }
        final ClientPhone modelPhone = new ClientPhone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientEmail.class.getSimpleName()));
        }
        if (!ClientEmail.isValidEmail(email)) {
            throw new IllegalValueException(ClientEmail.MESSAGE_CONSTRAINTS);
        }
        final ClientEmail modelEmail = new ClientEmail(email);

        final Set<Tag> modelTags = new HashSet<>(clientTags);

        Client client = new Client(modelName, modelAddress, modelPhone, modelEmail, modelTags);

        for (JsonAdaptedRemark jsonAdaptedRemark : remarks) {
            Remark remark = jsonAdaptedRemark.toModelType();
            if (client.hasRemark(remark)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMARK);
            }
            client.addRemark(remark);
        }

        for (JsonAdaptedTransaction jsonAdaptedTransaction : transactions) {
            Transaction transaction = jsonAdaptedTransaction.toModelType();
            client.addTransaction(transaction);
        }

        return client;
    }

}
