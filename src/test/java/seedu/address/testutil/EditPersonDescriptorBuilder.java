package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
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
     * Sets the {@code RiskTag} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRiskTag(String riskTag) {
        descriptor.setRiskTag(new RiskTag(riskTag));
        return this;
    }

    /**
     * Sets the {@code PlanTag} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPlanTag(String planTag) {
        descriptor.setPlanTag(new PlanTag(planTag));
        return this;
    }
    /**
     * Sets the {@code ClientTag} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withClientTag(String clientTag) {
        descriptor.setClientTag(new ClientTag(clientTag));
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withIncome(String income) {
        descriptor.setIncome(new Income(income));
        return this;
    }

    /**
     * Sets the {@code Monthly} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMonthly(String monthly) {
        descriptor.setMonthly(new Monthly(monthly));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<NormalTag> tagSet = Stream.of(tags).map(NormalTag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
