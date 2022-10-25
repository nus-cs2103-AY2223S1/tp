package seedu.address.testutil;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;

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
    public ViewEntriesDescriptorBuilder(EntryType entryType, GraphType graphType, YearMonth yearMonth) {
        descriptor = new ViewEntriesDescriptor();
        descriptor.setEntryType(entryType);
        descriptor.setGraphType(graphType);
        descriptor.setYearMonth(yearMonth);
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
        descriptor.setYearMonth(YearMonth.parse(month, DateTimeFormatter.ofPattern(Date.YEAR_MONTH_PATTERN)));
        return this;
    }

    public ViewEntriesDescriptor build() {
        return descriptor;
    }
}
