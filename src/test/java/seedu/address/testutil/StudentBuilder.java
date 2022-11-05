package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_NOK_PHONE = "87654321";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Integer DEFAULT_MONEY_OWED = 0;
    public static final Integer DEFAULT_MONEY_PAID = 0;
    public static final Integer DEFAULT_RATES_PER_CLASS = 40;
    public static final String DEFAULT_ADDITIONAL_NOTES = "";
    public static final Boolean DEFAULT_ATTENDANCE_STATUS = Boolean.FALSE;

    private Name name;
    private Phone phone;
    private Phone nokPhone;
    private Email email;
    private Address address;
    private Money moneyOwed;
    private Money moneyPaid;
    private Money ratesPerClass;
    private AdditionalNotes additionalNotes;
    private Class aClass;
    private Set<Tag> tags;
    private Mark mark;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        nokPhone = new Phone(DEFAULT_NOK_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        aClass = new Class();
        moneyOwed = new Money(DEFAULT_MONEY_OWED);
        moneyPaid = new Money(DEFAULT_MONEY_PAID);
        ratesPerClass = new Money(DEFAULT_RATES_PER_CLASS);
        additionalNotes = new AdditionalNotes(DEFAULT_ADDITIONAL_NOTES);
        tags = new HashSet<>();
        mark = new Mark(DEFAULT_ATTENDANCE_STATUS);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        nokPhone = studentToCopy.getNokPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        aClass = studentToCopy.getAClass();
        moneyOwed = studentToCopy.getMoneyOwed();
        moneyPaid = studentToCopy.getMoneyPaid();
        ratesPerClass = studentToCopy.getRatesPerClass();
        additionalNotes = studentToCopy.getAdditionalNotes();
        tags = new HashSet<>(studentToCopy.getTags());
        mark = studentToCopy.getMarkStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the next of kin {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withNokPhone(String nokPhone) {
        this.nokPhone = new Phone(nokPhone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withClass(String classDateTime) throws ParseException {
        this.aClass = ParserUtil.parseClass(classDateTime);
        return this;
    }

    /**
     * Sets the {@code MoneyOwed} of the {@code Student} that we are building.
     */
    public StudentBuilder withMoneyOwed(Integer moneyOwed) {
        this.moneyOwed = new Money(moneyOwed);
        return this;
    }

    /**
     * Sets the {@code MoneyPaid} of the {@code Student} that we are building.
     */
    public StudentBuilder withMoneyPaid(Integer moneyPaid) {
        this.moneyPaid = new Money(moneyPaid);
        return this;
    }

    /**
     * Sets the {@code ratesPerClass} of the {@code Student} that we are building.
     */
    public StudentBuilder withRatesPerClass(Integer ratesPerClass) {
        this.ratesPerClass = new Money(ratesPerClass);
        return this;
    }

    /**
     * Sets the {@code AdditionalNotes} of the {@code Student} that we are building.
     */
    public StudentBuilder withAdditionalNotes(String additionalNotes) {
        this.additionalNotes = new AdditionalNotes(additionalNotes);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code mark} of the {@code Student} that we are building.
     */
    public StudentBuilder withMark(Boolean attended) {
        this.mark = new Mark(attended);
        return this;
    }

    /**
     * Returns Student object with the fields initialised.
     * @return Student object.
     */
    public Student build() {
        return new
                Student(name, phone, nokPhone, email, address, aClass, moneyOwed, moneyPaid, ratesPerClass,
                additionalNotes, tags, mark);
    }
}
