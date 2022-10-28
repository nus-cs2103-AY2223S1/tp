package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_DATE;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static paymelah.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.util.CollectionUtil;
import paymelah.model.Model;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.PersonMatchesDescriptorPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons containing all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "<name>] "
            + "[" + PREFIX_PHONE + "<phone number>] "
            + "[" + PREFIX_TELEGRAM + "<telegram>] "
            + "[" + PREFIX_ADDRESS + "<address>] "
            + "[" + PREFIX_TAG + "<tag>]…"
            + "[" + PREFIX_DESCRIPTION + "<debt description>]…"
            + "[" + PREFIX_MONEY + "<debt money>]…"
            + "[" + PREFIX_DATE + "<debt date>]…"
            + "[" + PREFIX_TIME + "<debt time>]…"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "john "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney"
            + PREFIX_DESCRIPTION + "burger ";

    public static final String MESSAGE_NO_KEYWORDS = "At least one field to search for must be provided.";

    private final PersonMatchesDescriptorPredicate predicate;

    /**
     * Creates a FindCommand to find {@code Person}s matching the given predicates
     * @param predicate predicate matching a person based on personal details and debts
     */
    public FindCommand(PersonMatchesDescriptorPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    /**
     * Stores a descriptor of a {@code Person}'s debts; all fields are optional.
     */
    public static class DebtsDescriptor {
        private Set<Description> descriptions;
        private Set<Money> monies;
        private Money above;
        private Money below;
        private Set<DebtDate> dates;
        private DebtDate before;
        private DebtDate after;
        private Set<DebtTime> times;

        public DebtsDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code descriptions}, {@code monies},
         * {@code dates}, and {@code times} are used internally.
         */
        public DebtsDescriptor(DebtsDescriptor toCopy) {
            setDescriptions(toCopy.descriptions);
            setMonies(toCopy.monies);
            setAbove(toCopy.above);
            setBelow(toCopy.below);
            setDates(toCopy.dates);
            setBefore(toCopy.before);
            setAfter(toCopy.after);
            setTimes(toCopy.times);
        }

        /**
         * Returns true if at least one field is set.
         */
        public boolean isAnyFieldSet() {
            return CollectionUtil.isAnyNonNull(descriptions, monies, above, below, dates, before, after, times);
        }

        /**
         * Sets {@code descriptions} to this object's {@code descriptions}.
         * A defensive copy of {@code descriptions} is used internally.
         */
        public void setDescriptions(Set<Description> descriptions) {
            this.descriptions = (descriptions != null) ? new HashSet<>(descriptions) : null;
        }

        /**
         * Returns an unmodifiable description set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code descriptions} is null.
         */
        public Optional<Set<Description>> getDescriptions() {
            return (descriptions != null) ? Optional.of(Collections.unmodifiableSet(descriptions)) : Optional.empty();
        }

        /**
         * Sets {@code monies} to this object's {@code monies}.
         * A defensive copy of {@code monies} is used internally.
         */
        public void setMonies(Set<Money> monies) {
            this.monies = (monies != null) ? new HashSet<>(monies) : null;
        }

        /**
         * Returns an unmodifiable money set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code monies} is null.
         */
        public Optional<Set<Money>> getMonies() {
            return (monies != null) ? Optional.of(Collections.unmodifiableSet(monies)) : Optional.empty();
        }

        public void setAbove(Money above) {
            this.above = above;
        }

        public Optional<Money> getAbove() {
            return Optional.ofNullable(above);
        }

        public void setBelow(Money below) {
            this.below = below;
        }

        public Optional<Money> getBelow() {
            return Optional.ofNullable(below);
        }

        /**
         * Sets {@code dates} to this object's {@code dates}.
         * A defensive copy of {@code dates} is used internally.
         */
        public void setDates(Set<DebtDate> dates) {
            this.dates = (dates != null) ? new HashSet<>(dates) : null;
        }

        public void setBefore(DebtDate before) {
            this.before = before;
        }

        public Optional<DebtDate> getBefore() {
            return Optional.ofNullable(before);
        }

        public void setAfter(DebtDate after) {
            this.after = after;
        }

        public Optional<DebtDate> getAfter() {
            return Optional.ofNullable(after);
        }

        /**
         * Returns an unmodifiable date set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code dates} is null.
         */
        public Optional<Set<DebtDate>> getDates() {
            return (dates != null) ? Optional.of(Collections.unmodifiableSet(dates)) : Optional.empty();
        }

        /**
         * Sets {@code times} to this object's {@code times}.
         * A defensive copy of {@code times} is used internally.
         */
        public void setTimes(Set<DebtTime> times) {
            this.times = (times != null) ? new HashSet<>(times) : null;
        }

        /**
         * Returns an unmodifiable time set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code times} is null.
         */
        public Optional<Set<DebtTime>> getTimes() {
            return (times != null) ? Optional.of(Collections.unmodifiableSet(times)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DebtsDescriptor)) {
                return false;
            }

            // state check
            DebtsDescriptor pd = (DebtsDescriptor) other;

            return getDescriptions().equals(pd.getDescriptions())
                    && getMonies().equals(pd.getMonies())
                    && getDates().equals(pd.getDates())
                    && getTimes().equals(pd.getTimes());
        }
    }
}
