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
import seedu.address.model.tag.Tag;

/**
 * Edits the loan value of an existing person in the address book.
 */
public class EditLoanCommand extends Command {

    public static final String COMMAND_WORD = "editLoan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the loan value of the person identified "
            + "by the index number used in the displayed person list. "
            + "The existing loan value will be added to the input value.\n"
            + "Parameters: INDEX (can be a positive or negative integer) "
            + PREFIX_LOAN_AMOUNT + "AMOUNT"
            + PREFIX_LOAN_REASON + "REASON\n"
            + "Example: " + COMMAND_WORD + "1 "
            + PREFIX_LOAN_AMOUNT + "20"
            + PREFIX_LOAN_REASON + "Buy logistics";

    public static final String MESSAGE_EDIT_LOAN_SUCCESS = "Edited loan of person: %1$s";

    private final Index index;
    private final EditLoanDescriptor editLoanDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editLoanDescriptor details to edit the person with
     * @param change LoanHistory of the loan change
     */
    public EditLoanCommand(Index index, EditLoanDescriptor editLoanDescriptor, LoanHistory change) {
        requireNonNull(index);
        requireNonNull(editLoanDescriptor);
        requireNonNull(change);

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
        Person editedPerson = createEditedPerson(personToEdit, editLoanDescriptor);

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
                CommandResult.UiState.Inspect, String.valueOf(index + 1));
    }


    private static Person createEditedPerson(Person personToEdit, EditLoanDescriptor editLoanDescriptor) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Birthday updatedBirthday = personToEdit.getBirthday();
        Set<Tag> updatedTags = personToEdit.getTags();

        double val = editLoanDescriptor.getLoan().get().getAmount() + personToEdit.getLoan().getAmount();

        Loan updatedLoan = new Loan(String.valueOf(val));
        List<LoanHistory> updatedLoanHistory = new ArrayList<>();

        for (LoanHistory his : personToEdit.getHistory()) {
            updatedLoanHistory.add(his);
        }

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
        private Loan loan;
        private LoanHistory history;

        public EditLoanDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLoanDescriptor(EditLoanDescriptor toCopy) {
            setLoan(toCopy.loan);
        }

        public void setLoan(Loan loan) {
            this.loan = loan;
        }

        public Optional<Loan> getLoan() {
            return Optional.ofNullable(loan);
        }

        public void setHistory(LoanHistory history) {
            this.history = history;
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
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditCommand.EditPersonDescriptor e = (EditCommand.EditPersonDescriptor) other;

            return getLoan().equals(e.getLoan())
                    && getHistory().equals((e.getHistory()));
        }
    }

}
