package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.student.AdditionalNotes;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Mark;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String nokPhone;
    private final String email;
    private final String address;
    private final String classDateTime;
    private final Integer moneyOwed;
    private final Integer moneyPaid;
    private final Integer ratesPerClass;
    private final String additionalNotes;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Boolean isPresent;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("nokPhone") String nokPhone, @JsonProperty("email") String email,
                              @JsonProperty("address") String address,
                              @JsonProperty("classDateTime") String classDateTime,
                              @JsonProperty("moneyOwed") Integer moneyOwed,
                              @JsonProperty("moneyPaid") Integer moneyPaid,
                              @JsonProperty("ratesPerClass") Integer ratesPerClass,
                              @JsonProperty("additionalNotes") String additionalNotes,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("isPresent") Boolean isPresent) {
        this.name = name;
        this.phone = phone;
        this.nokPhone = nokPhone;
        this.email = email;
        this.address = address;
        this.classDateTime = classDateTime;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.ratesPerClass = ratesPerClass;
        this.additionalNotes = additionalNotes;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isPresent = isPresent;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        nokPhone = source.getNokPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        classDateTime = source.getAClass().classDateTime;
        moneyOwed = source.getMoneyOwed().value;
        moneyPaid = source.getMoneyPaid().value;
        ratesPerClass = source.getRatesPerClass().value;
        additionalNotes = source.getAdditionalNotes().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isPresent = source.getMarkStatus().isMarked();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
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

        if (nokPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(nokPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelNokPhone = new Phone(nokPhone);

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

        final Class modelClassDateTime;
        if (classDateTime != null && !classDateTime.equals("")) {
            if (!Class.isValidClassString(classDateTime)) {
                throw new IllegalValueException(Class.MESSAGE_CONSTRAINTS);
            }
            modelClassDateTime = ParserUtil.parseClass(classDateTime);
        } else {
            modelClassDateTime = new Class();
        }

        final Money modelMoneyOwed;
        if (moneyOwed != null) {
            if (!Money.isValidMoney(moneyOwed)) {
                throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
            }
            modelMoneyOwed = new Money(moneyOwed);
        } else {
            modelMoneyOwed = new Money(0);
        }

        final Money modelMoneyPaid;
        if (moneyPaid != null) {
            if (!Money.isValidMoney(moneyPaid)) {
                throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
            }
            modelMoneyPaid = new Money(moneyPaid);
        } else {
            modelMoneyPaid = new Money(0);
        }

        final Money modelRatesPerClass;
        if (ratesPerClass != null) {
            if (!Money.isValidMoney(ratesPerClass)) {
                throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
            }
            modelRatesPerClass = new Money(ratesPerClass);
        } else {
            modelRatesPerClass = new Money(Student.DEFAULT_RATES_PER_CLASS);
        }

        final AdditionalNotes modelAdditionalNotes;
        if (additionalNotes != null) {
            modelAdditionalNotes = new AdditionalNotes(additionalNotes);
        } else {
            modelAdditionalNotes = new AdditionalNotes("");
        }

        final Mark modelIsPresent;
        if (isPresent != null) {
            modelIsPresent = new Mark(isPresent);
        } else {
            modelIsPresent = new Mark(Boolean.FALSE);
        }

        final Set<Tag> modelTags = new HashSet<>(studentTags);

        return new Student(modelName, modelPhone, modelNokPhone, modelEmail, modelAddress, modelClassDateTime,
                modelMoneyOwed, modelMoneyPaid, modelRatesPerClass, modelAdditionalNotes, modelTags, modelIsPresent);
    }

}
