package seedu.pennywise.testutil;

import seedu.pennywise.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Tag;

/**
 * A utility class to help with building EditEntryDescriptor objects.
 */
public class EditEntryDescriptorBuilder {

    private EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditEntryDescriptor();
    }

    public EditEntryDescriptorBuilder(EditEntryDescriptor descriptor) {
        this.descriptor = new EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntryDescriptor} with fields containing {@code entry}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry, EntryType entryType) {
        descriptor = new EditEntryDescriptor();
        descriptor.setDescription(entry.getDescription());
        descriptor.setType(entryType);
        descriptor.setAmount(entry.getAmount());
        descriptor.setDate(entry.getDate());
        descriptor.setTag(entry.getTag());
    }

    /**
     * Sets the {@code Description} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code EntryType} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withType(String entryType) {
        descriptor.setType(new EntryType(entryType));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Set<Tag>} and set it to the {@code EditEntryDescriptor}
     * that we are building.
     */

    public EditEntryDescriptorBuilder withTag(String tag) {
        Tag newTag = new Tag(descriptor.getType().get(), tag);
        descriptor.setTag(newTag);
        return this;
    }

    public EditEntryDescriptor build() {
        return descriptor;
    }
}
