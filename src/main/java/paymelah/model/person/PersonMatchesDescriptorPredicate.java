package paymelah.model.person;

import java.util.function.Predicate;

import paymelah.commons.util.StringUtil;
import paymelah.logic.commands.FindCommand.DebtsDescriptor;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.Money;

/**
 * Tests that a {@code Person} matches the descriptor given.
 */
public class PersonMatchesDescriptorPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS = "Keyword should not be blank";

    private final PersonDescriptor personDescriptor;
    private final DebtsDescriptor debtsDescriptor;

    /**
     * Creates a predicate for {@code Person}s that match the given descriptors.
     * @param personDescriptor a {@code PersonDescriptor} describing a {@code Person}
     * @param debtsDescriptor a {@code DebtsDescriptor} describing a {@code Person}'s debts
     */
    public PersonMatchesDescriptorPredicate(PersonDescriptor personDescriptor, DebtsDescriptor debtsDescriptor) {
        this.personDescriptor = personDescriptor;
        this.debtsDescriptor = debtsDescriptor;
    }

    public PersonMatchesDescriptorPredicate(PersonDescriptor personDescriptor) {
        this(personDescriptor, new DebtsDescriptor());
    }

    public PersonMatchesDescriptorPredicate(DebtsDescriptor debtsDescriptor) {
        this(new PersonDescriptor(), debtsDescriptor);
    }

    private boolean matchesName(Person person) {
        if (personDescriptor.getName().isPresent()) {
            return StringUtil.containsStringIgnoreCase(person.getName().fullName,
                    personDescriptor.getName().get().fullName);
        }
        return true;
    }

    private boolean matchesPhone(Person person) {
        if (personDescriptor.getPhone().isPresent()) {
            return person.getPhone().equals(personDescriptor.getPhone().get());
        }
        return true;
    }

    private boolean matchesTelegram(Person person) {
        if (personDescriptor.getTelegram().isPresent()) {
            return person.getTelegram().equals(personDescriptor.getTelegram().get());
        }
        return true;
    }

    private boolean matchesAddress(Person person) {
        if (personDescriptor.getAddress().isPresent()) {
            return StringUtil.containsStringIgnoreCase(person.getAddress().value,
                    personDescriptor.getAddress().get().value);
        }
        return true;
    }

    private boolean matchesTags(Person person) {
        if (personDescriptor.getTags().isPresent()) {
            return personDescriptor.getTags().get().stream().allMatch(matchTag ->
                    person.getTags().stream().anyMatch(tag -> tag.equals(matchTag)));
        }
        return true;
    }

    private boolean matchesDescriptions(Person person) {
        if (debtsDescriptor.getDescriptions().isPresent()) {
            return debtsDescriptor.getDescriptions().get().stream().allMatch(description ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getDescription().equals(description)));
        }
        return true;
    }

    private boolean matchesMonies(Person person) {
        if (debtsDescriptor.getMonies().isPresent()) {
            return debtsDescriptor.getMonies().get().stream().allMatch(money ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getMoney().equals(money)));
        }
        return true;
    }

    private boolean matchesAboveBelow(Person person) {
        if (debtsDescriptor.getAbove().isPresent() && debtsDescriptor.getBelow().isPresent()) {
            Money above = debtsDescriptor.getAbove().get();
            Money below = debtsDescriptor.getBelow().get();
            return person.getDebts().asList().stream().anyMatch(debt ->
                    debt.getMoney().compareTo(above) >= 0
                    && debt.getMoney().compareTo(below) <= 0);
        }
        return true;
    }

    private boolean matchesAbove(Person person) {
        if (debtsDescriptor.getAbove().isPresent()) {
            Money above = debtsDescriptor.getAbove().get();
            return person.getDebts().asList().stream().anyMatch(debt -> debt.getMoney().compareTo(above) >= 0);
        }
        return true;
    }

    private boolean matchesBelow(Person person) {
        if (debtsDescriptor.getBelow().isPresent()) {
            Money below = debtsDescriptor.getBelow().get();
            return person.getDebts().asList().stream().anyMatch(debt -> debt.getMoney().compareTo(below) <= 0);
        }
        return true;
    }

    private boolean matchesDates(Person person) {
        if (debtsDescriptor.getDates().isPresent()) {
            return debtsDescriptor.getDates().get().stream().allMatch(date ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getDate().equals(date)));
        }
        return true;
    }

    private boolean matchesBefore(Person person) {
        if (debtsDescriptor.getBefore().isPresent()) {
            DebtDate before = debtsDescriptor.getBefore().get();
            return person.getDebts().asList().stream().anyMatch(debt -> debt.getDate().compareTo(before) <= 0);
        }
        return true;
    }

    private boolean matchesAfter(Person person) {
        if (debtsDescriptor.getAfter().isPresent()) {
            DebtDate after = debtsDescriptor.getAfter().get();
            return person.getDebts().asList().stream().anyMatch(debt -> debt.getDate().compareTo(after) >= 0);
        }
        return true;
    }

    private boolean matchesBeforeAfter(Person person) {
        if (debtsDescriptor.getBefore().isPresent() && debtsDescriptor.getAfter().isPresent()) {
            DebtDate before = debtsDescriptor.getBefore().get();
            DebtDate after = debtsDescriptor.getAfter().get();
            return person.getDebts().asList().stream().anyMatch(debt ->
                    debt.getDate().compareTo(before) <= 0
                            && debt.getDate().compareTo(after) >= 0);
        }
        return true;
    }

    private boolean matchesTimes(Person person) {
        if (debtsDescriptor.getTimes().isPresent()) {
            return debtsDescriptor.getTimes().get().stream().allMatch(time ->
                    person.getDebts().asList().stream().anyMatch(debt -> debt.getTime().equals(time)));
        }
        return true;
    }

    @Override
    public boolean test(Person person) {
        return matchesName(person)
                && matchesPhone(person)
                && matchesTelegram(person)
                && matchesAddress(person)
                && matchesTags(person)
                && matchesDescriptions(person)
                && matchesMonies(person)
                && matchesAboveBelow(person)
                && matchesAbove(person)
                && matchesBelow(person)
                && matchesDates(person)
                && matchesBeforeAfter(person)
                && matchesBefore(person)
                && matchesAfter(person)
                && matchesTimes(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // if same object
            return true;
        }

        if (!(other instanceof PersonMatchesDescriptorPredicate)) { // instanceof handles nulls
            return false;
        }

        return personDescriptor.equals(((PersonMatchesDescriptorPredicate) other).personDescriptor)
                && debtsDescriptor.equals(((PersonMatchesDescriptorPredicate) other).debtsDescriptor);
    }

}
