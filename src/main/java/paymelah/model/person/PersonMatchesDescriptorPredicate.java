package paymelah.model.person;

import java.util.function.Predicate;

import paymelah.commons.util.StringUtil;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;

/**
 * Tests that a {@code Person} matches the descriptor given.
 */
public class PersonMatchesDescriptorPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS = "Keyword should not be blank";

    private final PersonDescriptor descriptor;

    public PersonMatchesDescriptorPredicate(PersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    private boolean matchesName(Person person) {
        if (descriptor.getName().isPresent()) {
            return StringUtil.containsStringIgnoreCase(person.getName().fullName, descriptor.getName().get().fullName);
        }
        return true;
    }

    private boolean matchesPhone(Person person) {
        if (descriptor.getPhone().isPresent()) {
            return person.getPhone().equals(descriptor.getPhone().get());
        }
        return true;
    }

    private boolean matchesEmail(Person person) {
        if (descriptor.getEmail().isPresent()) {
            return person.getEmail().equals(descriptor.getEmail().get());
        }
        return true;
    }

    private boolean matchesAddress(Person person) {
        if (descriptor.getAddress().isPresent()) {
            return StringUtil.containsStringIgnoreCase(person.getAddress().value, descriptor.getAddress().get().value);
        }
        return true;
    }

    private boolean matchesTags(Person person) {
        if (descriptor.getTags().isPresent()) {
            return descriptor.getTags().get().stream().allMatch(matchTag ->
                    person.getTags().stream().anyMatch(tag -> tag.equals(matchTag)));
        }
        return true;
    }

    private boolean matchesDescriptions(Person person) {
        if (descriptor.getDescriptions().isPresent()) {
            return descriptor.getDescriptions().get().stream().allMatch(description ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getDescription().equals(description)));
        }
        return true;
    }

    private boolean matchesMonies(Person person) {
        if (descriptor.getMonies().isPresent()) {
            return descriptor.getMonies().get().stream().allMatch(money ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getMoney().equals(money)));
        }
        return true;
    }

    private boolean matchesDates(Person person) {
        if (descriptor.getDates().isPresent()) {
            return descriptor.getDates().get().stream().allMatch(date ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getDate().equals(date)));
        }
        return true;
    }

    private boolean matchesTimes(Person person) {
        if (descriptor.getTimes().isPresent()) {
            return descriptor.getTimes().get().stream().allMatch(time ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getTime().equals(time)));
        }
        return true;
    }

    @Override
    public boolean test(Person person) {
        return matchesName(person)
                && matchesPhone(person)
                && matchesEmail(person)
                && matchesAddress(person)
                && matchesTags(person)
                && matchesDescriptions(person)
                && matchesMonies(person)
                && matchesDates(person)
                && matchesTimes(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesDescriptorPredicate // instanceof handles nulls
                && descriptor.equals(((PersonMatchesDescriptorPredicate) other).descriptor)); // state check
    }

}
