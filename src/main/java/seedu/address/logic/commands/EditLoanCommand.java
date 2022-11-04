package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_REASON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.LoanOutOfBoundsException;
import seedu.address.model.tag.Tag;

/**
 * Edits the loan value of an existing person in the address book.
 */
public class EditLoanCommand extends Command {

    public static final String COMMAND_WORD = "editLoan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the loan value of the person identified "
            + "by the index number used in the displayed person list. "
            + "The existing loan value will be added to the input value.\n"
            + "Parameters: INDEX (can be a positive or negative integer) <OR> NAME (must be valid) "
            + PREFIX_LOAN_AMOUNT + "AMOUNT "
            + PREFIX_LOAN_REASON + "REASON\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LOAN_AMOUNT + "20 "
            + PREFIX_LOAN_REASON + "Buy logistics";

    public static final String MESSAGE_EDIT_LOAN_SUCCESS = "Edited loan of person: %1$s";
    public static final String OUT_OF_BOUNDS_NOTIFICATION =
            Messages.TOTAL_LOAN_OUT_OF_BOUNDS + "\n" + Loan.MESSAGE_CONSTRAINTS;

    private final Index index;
    private final EditLoanDescriptor editLoanDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editLoanDescriptor details to edit the person with
     */
    public EditLoanCommand(Index index, EditLoanDescriptor editLoanDescriptor) {
        requireNonNull(index);
        requireNonNull(editLoanDescriptor);

        this.index = index;
        this.editLoanDescriptor = editLoanDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson;
        try {
            editedPerson = createEditedPerson(personToEdit, editLoanDescriptor);
        } catch (LoanOutOfBoundsException e) {
            throw new CommandException(OUT_OF_BOUNDS_NOTIFICATION);
        }

        model.setPerson(personToEdit, editedPerson);

        // Remove personToEdit from its tags, and remove unused tags from UniqueTagMapping
        personToEdit.getTags().forEach(tag -> {
            tag.removePerson(personToEdit);
            if (tag.isPersonListEmpty() && !model.notebookContainsTag(tag)) {
                model.removeTag(tag);
            }
        });

        // Add editedPerson to its tags
        editedPerson.getTags().forEach(tag -> tag.addPerson(editedPerson));

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int index = model.getFilteredPersonList().indexOf(editedPerson);

        return new CommandResult(String.format(MESSAGE_EDIT_LOAN_SUCCESS, editedPerson),
                CommandResult.UiState.Inspect, String.format("%d", index + 1));
    }


    private static Person createEditedPerson(Person personToEdit, EditLoanDescriptor editLoanDescriptor)
            throws LoanOutOfBoundsException {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Birthday updatedBirthday = personToEdit.getBirthday();
        Set<Tag> updatedTags = personToEdit.getTags();

        Loan updatedLoan = personToEdit.getLoan().addBy(editLoanDescriptor.getLoan()
                    .orElse(new Loan(0)));

        List<LoanHistory> updatedLoanHistory = new ArrayList<>(personToEdit.getHistory());
        editLoanDescriptor.getHistory().ifPresent(updatedLoanHistory::add);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                          updatedBirthday, updatedTags, updatedLoan, updatedLoanHistory);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLoanCommand)) {
            return false;
        }

        // state check
        EditLoanCommand e = (EditLoanCommand) other;
        return index.equals(e.index)
                && editLoanDescriptor.equals(e.editLoanDescriptor);
    }

    /**
     * Stores the loan details to edit the person with
     */
    public static class EditLoanDescriptor {
        private final Loan loan;
        private final LoanHistory history;

        /**
         * Constructs a new EditLoanDescriptor
         * @param loan the new total loan
         * @param history the new history to carry, included the increment loan and the reason
         */
        public EditLoanDescriptor(Loan loan, LoanHistory history) {
            this.loan = loan;
            this.history = history;
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLoanDescriptor(EditLoanDescriptor toCopy) {
            this.loan = toCopy.loan;
            this.history = toCopy.history;
        }

        public Optional<Loan> getLoan() {
            return Optional.ofNullable(loan);
        }

        public Optional<LoanHistory> getHistory() {
            return Optional.ofNullable(history);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLoanDescriptor)) {
                return false;
            }

            // state check
            EditLoanDescriptor e = (EditLoanDescriptor) other;

            return getLoan().equals(e.getLoan())
                    && getHistory().equals((e.getHistory()));
        }
    }

}
