package seedu.pennywise.testutil;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import seedu.pennywise.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.EntryType;

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
    public ViewEntriesDescriptorBuilder(EntryType entryType, YearMonth yearMonth) {
        descriptor = new ViewEntriesDescriptor();
        descriptor.setEntryType(entryType);
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
