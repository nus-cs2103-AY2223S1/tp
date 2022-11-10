package seedu.address.testutil;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommissionCommand.EditCommissionDescriptor;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Description;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditCustomerDescriptor objects.
 */
public class EditCommissionDescriptorBuilder {

    private EditCommissionDescriptor descriptor;

    public EditCommissionDescriptorBuilder() {
        descriptor = new EditCommissionDescriptor();
    }

    public EditCommissionDescriptorBuilder(EditCommissionDescriptor descriptor) {
        this.descriptor = new EditCommissionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCustomerDescriptor} with fields containing {@code customer}'s details
     */
    public EditCommissionDescriptorBuilder(Commission commission) {
        descriptor = new EditCommissionDescriptor();
        descriptor.setTitle(commission.getTitle());
        commission.getDescription().ifPresent(description -> descriptor.setDescription(description));
        descriptor.setDeadline(commission.getDeadline());
        descriptor.setCompletionStatus(commission.getCompletionStatus());
        descriptor.setFee(commission.getFee());
        descriptor.setTags(commission.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditCommissionDescriptor} that we are building.
     */
    public EditCommissionDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditCommissionDescriptor} that we are building.
     */
    public EditCommissionDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditCommissionDescriptor} that we are building.
     */
    public EditCommissionDescriptorBuilder withDeadline(LocalDate deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code EditCommissionDescriptor} that we are building.
     */
    public EditCommissionDescriptorBuilder withStatus(boolean status) {
        descriptor.setCompletionStatus(new CompletionStatus(status));
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code EditCommissionDescriptor} that we are building.
     */
    public EditCommissionDescriptorBuilder withFee(double fee) {
        descriptor.setFee(new Fee(fee));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCommissionDescriptor}
     * that we are building.
     */
    public EditCommissionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommissionDescriptor build() {
        return descriptor;
    }
}
