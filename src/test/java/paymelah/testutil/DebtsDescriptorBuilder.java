package paymelah.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import paymelah.logic.commands.FindCommand.DebtsDescriptor;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * A utility class to help with building PersonDescriptor objects.
 */
public class DebtsDescriptorBuilder {

    private DebtsDescriptor descriptor;

    public DebtsDescriptorBuilder() {
        descriptor = new DebtsDescriptor();
    }

    public DebtsDescriptorBuilder(DebtsDescriptor descriptor) {
        this.descriptor = new DebtsDescriptor(descriptor);
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>} and set it to the {@code DebtsDescriptor}
     * that we are building.
     */
    public DebtsDescriptorBuilder withDescriptions(String... descriptions) {
        Set<Description> descriptionSet = Stream.of(descriptions).map(Description::new).collect(Collectors.toSet());
        descriptor.setDescriptions(descriptionSet);
        return this;
    }

    /**
     * Parses the {@code monies} into a {@code Set<Money>} and set it to the {@code DebtsDescriptor}
     * that we are building.
     */
    public DebtsDescriptorBuilder withMonies(String... monies) {
        Set<Money> moneySet = Stream.of(monies).map(Money::new).collect(Collectors.toSet());
        descriptor.setMonies(moneySet);
        return this;
    }

    /**
     * Sets the {@code above} of the {@code DebtsDescriptor} that we are building.
     */
    public DebtsDescriptorBuilder withAbove(String above) {
        descriptor.setAbove(new Money(above));
        return this;
    }

    /**
     * Sets the {@code below} of the {@code DebtsDescriptor} that we are building.
     */
    public DebtsDescriptorBuilder withBelow(String below) {
        descriptor.setBelow(new Money(below));
        return this;
    }

    /**
     * Parses the {@code dates} into a {@code Set<DebtDate>} and set it to the {@code DebtsDescriptor}
     * that we are building.
     */
    public DebtsDescriptorBuilder withDates(String... dates) {
        Set<DebtDate> dateSet = Stream.of(dates).map(DebtDate::new).collect(Collectors.toSet());
        descriptor.setDates(dateSet);
        return this;
    }

    /**
     * Sets the {@code before} of the {@code DebtsDescriptor} that we are building.
     */
    public DebtsDescriptorBuilder withBefore(String before) {
        descriptor.setBefore(new DebtDate(before));
        return this;
    }

    /**
     * Sets the {@code after} of the {@code DebtsDescriptor} that we are building.
     */
    public DebtsDescriptorBuilder withAfter(String after) {
        descriptor.setAfter(new DebtDate(after));
        return this;
    }

    /**
     * Parses the {@code times} into a {@code Set<DebtTime>} and set it to the {@code DebtsDescriptor}
     * that we are building.
     */
    public DebtsDescriptorBuilder withTimes(String... times) {
        Set<DebtTime> timeSet = Stream.of(times).map(DebtTime::new).collect(Collectors.toSet());
        descriptor.setTimes(timeSet);
        return this;
    }

    public DebtsDescriptor build() {
        return descriptor;
    }
}
