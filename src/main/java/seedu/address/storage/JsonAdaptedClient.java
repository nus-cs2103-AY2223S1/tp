package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;

/**
 * Jackson-friendly version of {@link Client}.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();
    private final String birthday;
    private final List<JsonAdaptedProduct> products = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("birthday") String birthday,
                             @JsonProperty("meetings") List<JsonAdaptedMeeting> meetings,
                             @JsonProperty("products") List<JsonAdaptedProduct> products) {
        if (meetings != null) {
            this.meetings.addAll(meetings);
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        if (products != null) {
            this.products.addAll(products);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().isEmpty()
            ? ""
            : source.getEmail().get().toString();
        address = source.getAddress().isEmpty()
            ? ""
            : source.getAddress().get().toString();
        birthday = source.getBirthday().isEmpty()
            ? ""
            : source.getBirthday().get().toString();
        meetings.addAll(source.getMeetings().stream()
                .map(meeting -> new JsonAdaptedMeeting(meeting))
                .collect(Collectors.toList()));
        products.addAll(source.getProducts().stream()
                .map(JsonAdaptedProduct::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final Set<Product> modelProducts = validateAndGetProducts();
        final Name modelName = validateAndGetName();
        final Phone modelPhone = validateAndGetPhone();
        final Optional<Email> modelEmail = validateAndGetEmail();
        final Optional<Address> modelAddress = validateAndGetAddress();
        final Optional<Birthday> modelBirthday = validateAndGetBirthday();

        Client client = new Client(modelName, modelPhone, modelEmail, modelAddress, modelBirthday, modelProducts);
        addMeetingsToClient(client);

        return client;
    }

    private Name validateAndGetName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private Phone validateAndGetPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    private Optional<Email> validateAndGetEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return email.equals("") ? Optional.empty() : Optional.of(new Email(email));
    }

    private Optional<Address> validateAndGetAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return address.equals("") ? Optional.empty() : Optional.of(new Address(address));
    }

    private Optional<Birthday> validateAndGetBirthday() throws IllegalValueException {
        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthday(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return birthday.equals("")
                ? Optional.empty()
                : Optional.of(new Birthday(ParserUtil.parseDate(birthday, "birthday")));
    }

    private Set<Product> validateAndGetProducts() throws IllegalValueException {
        final Set<Product> clientProducts = new HashSet<>();
        for (JsonAdaptedProduct product : products) {
            clientProducts.add(product.toModelType());
        }
        return clientProducts;
    }

    private void addMeetingsToClient(Client client) throws IllegalValueException {
        for (JsonAdaptedMeeting jsonMeeting : meetings) {
            Meeting meeting = jsonMeeting.toModelType(client);
            client.addMeeting(meeting);
        }
    }

}
