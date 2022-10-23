package seedu.address.testutil;

import seedu.address.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;
import seedu.address.model.entry.Month;

/**
 * A utility class to help with building ViewEntriesDescriptor objects.
 */
public class ViewEntriesDescriptorBuilder {

    private ViewEntriesDescriptor descriptor;

    public ViewEntriesDescriptorBuilder() {
        descriptor = new ViewEntriesDescriptor();
    }

    public ViewEntriesDescriptorBuilder(ViewEntriesDescriptor descriptor) {
        this.descriptor = new ViewEntriesDescriptor(descriptor);
    }

    /**
     * Returns a {@code ViewEntriesDescriptor} with fields containing {@code entry}'s details
     */
    public ViewEntriesDescriptorBuilder(EntryType entryType, GraphType graphType, Month month) {
        descriptor = new ViewEntriesDescriptor();
        descriptor.setEntryType(entryType);
        descriptor.setGraphType(graphType);
        descriptor.setMonth(month);
    }

    /**
     * Sets the {@code EntryType} of the {@code ViewEntriesDescriptor} that we are building.
     */
    public ViewEntriesDescriptorBuilder withEntryType(String entryType) {
        descriptor.setEntryType(new EntryType(entryType));
        return this;
    }

    /**
     * Sets the {@code GraphType} of the {@code ViewEntriesDescriptor} that we are building.
     */
    public ViewEntriesDescriptorBuilder withGraphType(String graphType) {
        descriptor.setGraphType(new GraphType(graphType));
        return this;
    }

    /**
     * Sets the {@code Month} of the {@code ViewEntriesDescriptor} that we are building.
     */
    public ViewEntriesDescriptorBuilder withMonth(String month) {
        descriptor.setMonth(new Month(month));
        return this;
    }

    public ViewEntriesDescriptor build() {
        return descriptor;
    }
}
