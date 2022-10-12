package longtimenosee.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import longtimenosee.commons.exceptions.IllegalValueException;
import longtimenosee.model.client.Birthday;
import longtimenosee.model.client.Client;
import longtimenosee.model.client.Income;
import longtimenosee.model.client.RiskAppetite;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Phone;
import longtimenosee.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Client}.
 */
public class JsonAdaptedClient {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    //Add on for Managing Clients;
    private final String birthday; //TODO: JsonAdapted Birthday

    private final String income;

    private final String riskAppetite;

    // TODO: JsonAdaptedNotes (To be implemented)

    /**
     * Constructs a {@code JsonAdaptedClient} with the given participant details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                  @JsonProperty("email") String email, @JsonProperty("address") String address,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                  @JsonProperty("birthday") String birthday,
                                  @JsonProperty("income") String income,
                                  @JsonProperty ("riskAppetite") String riskAppetite
                             ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.birthday = birthday;
        this.income = income;
        this.riskAppetite = riskAppetite;

        // TODO: PersonNotes
    }

    /**
     * Converts a given {@code Client} into this class for Json use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        birthday = source.getBirthday().value;
        income = source.getIncome().value;
        riskAppetite = source.getRiskAppetite().value;
        // TODO: Notes to be implemented
    }

    /**
     * Converts this Jackson-friendly adapted participant object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participant.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        //TODO: Some level of input validation (null / unsupported values) Current code alr does this
        //TODO: IllegalClientException

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthday.class.getSimpleName()));
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (income == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName()));
        }

        final Income modelIncome = new Income(income);

        if (riskAppetite == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RiskAppetite.class.getSimpleName()));
        }

        final RiskAppetite modelRiskAppetite = new RiskAppetite(riskAppetite);


        return new Client(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelBirthday, modelIncome, modelRiskAppetite);
    }
}
