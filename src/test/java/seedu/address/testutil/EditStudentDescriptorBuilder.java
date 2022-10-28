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
public class EditStudentDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    /**
     * Constructs a EditPersonDescriptionBuilder object with descriptor initialised.
     * By default, descriptor field Class will have fields date and time to be null.
     */
    public EditStudentDescriptorBuilder() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setClass(new Class());
        descriptor = editPersonDescriptor;
    }

    public EditStudentDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
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
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Phone} next of kin phone of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withNokPhone(String nokPhone) {
        descriptor.setNokPhone(new Phone(nokPhone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withClass(String classDateTime) throws ParseException {
        descriptor.setClass(ParserUtil.parseClass(classDateTime));
        return this;
    }

    /**
     * Sets the {@code moneyOwed} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMoneyOwed(Integer moneyOwed) {
        descriptor.setMoneyOwed(new Money(moneyOwed));
        return this;
    }

    /**
     * Sets the {@code moneyPaid} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMoneyPaid(Integer moneyPaid) {
        descriptor.setMoneyPaid(new Money(moneyPaid));
        return this;
    }

    /**
     * Sets the {@code ratesPerClass} of the {@code EditStudentDescriptorBuilder} that we are building.
     */
    public EditStudentDescriptorBuilder withRatesPerClass(Integer ratesPerClass) {
        descriptor.setRatesPerClass(new Money(ratesPerClass));
        return this;
    }

    /**
     * Sets the {@code AdditionalNotes} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAdditionalNotes(String additionalNotes) {
        descriptor.setAdditionalNotes(new AdditionalNotes(additionalNotes));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
