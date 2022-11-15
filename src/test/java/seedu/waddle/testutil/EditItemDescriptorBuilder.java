package seedu.waddle.testutil;

import java.time.LocalTime;

import seedu.waddle.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {

    private EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditItemDescriptor descriptor) {
        this.descriptor = new EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing an {@code item}'s details
     */
    public EditItemDescriptorBuilder(Item item) {
        descriptor = new EditItemDescriptor();
        descriptor.setDescription(item.getDescription());
        descriptor.setPriority(item.getPriority());
        descriptor.setCost(item.getCost());
        descriptor.setDuration(item.getDuration());
        descriptor.setStartTime(item.getStartTime());
    }

    /**
     * Sets the {@code Description} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withPriority(int priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withCost(String cost) {
        descriptor.setCost(new Cost(cost));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(LocalTime.parse(startTime));
        return this;
    }

    public EditItemDescriptor build() {
        return descriptor;
    }
}
