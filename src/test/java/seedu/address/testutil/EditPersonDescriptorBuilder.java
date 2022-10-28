package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.AdditionalNotes;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    /**
     * Constructs a EditPersonDescriptionBuilder object with descriptor initialised.
     * By default, descriptor field Class will have fields date and time to be null.
     */
    public EditPersonDescriptorBuilder() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setClass(new Class());
        descriptor = editPersonDescriptor;
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code student}'s details
     */
    public EditPersonDescriptorBuilder(Student student) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setNokPhone(student.getNokPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setClass(student.getAClass());
        descriptor.setRatesPerClass(student.getRatesPerClass());
        descriptor.setMoneyOwed(student.getMoneyOwed());
        descriptor.setMoneyPaid(student.getMoneyPaid());
        descriptor.setAdditionalNotes(student.getAdditionalNotes());
        descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Phone} next of kin phone of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokPhone(String nokPhone) {
        descriptor.setNokPhone(new Phone(nokPhone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withClass(String classDateTime) throws ParseException {
        descriptor.setClass(ParserUtil.parseClass(classDateTime));
        return this;
    }

    /**
     * Sets the {@code moneyOwed} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMoneyOwed(Integer moneyOwed) {
        descriptor.setMoneyOwed(new Money(moneyOwed));
        return this;
    }

    /**
     * Sets the {@code moneyPaid} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMoneyPaid(Integer moneyPaid) {
        descriptor.setMoneyPaid(new Money(moneyPaid));
        return this;
    }

    /**
     * Sets the {@code ratesPerClass} of the {@code EditPersonDescriptorBuilder} that we are building.
     */
    public EditPersonDescriptorBuilder withRatesPerClass(Integer ratesPerClass) {
        descriptor.setRatesPerClass(new Money(ratesPerClass));
        return this;
    }

    /**
     * Sets the {@code AdditionalNotes} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAdditionalNotes(String additionalNotes) {
        descriptor.setAdditionalNotes(new AdditionalNotes(additionalNotes));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
