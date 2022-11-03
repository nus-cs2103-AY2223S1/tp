package seedu.boba.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.boba.model.BobaBotModel.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import seedu.boba.commons.core.Messages;
import seedu.boba.commons.core.index.Index;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate<Customer> predicate;

    private Index targetIndex;

    private FindPersonDescriptor findPersonDescriptor;

    public FindCommand(Predicate<Customer> predicate) {
        this.predicate = predicate;
    }

    /**
     * Constructor for FindCommand.
     *
     * @param findPersonDescriptor contains details for the customer we are finding.
     */
    public FindCommand(FindPersonDescriptor findPersonDescriptor) {
        this.predicate = null;
        this.findPersonDescriptor = findPersonDescriptor;
    }

    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws CommandException {

        requireNonNull(bobaBotModel);
        bobaBotModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (!isNull(predicate)) {
            bobaBotModel.updateFilteredPersonList(predicate);
        } else {
            try {
                if (findPersonDescriptor.isPhoneEmpty) {
                    this.targetIndex = Index.fromZeroBased(bobaBotModel.findEmail(findPersonDescriptor.getEmail()));
                } else {
                    this.targetIndex = Index.fromZeroBased(bobaBotModel.findNum(findPersonDescriptor.getPhone()));
                }
            } catch (PersonNotFoundException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
            }

            List<Customer> lastShownList = bobaBotModel.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
            }

            Customer customerToFind = lastShownList.get(targetIndex.getZeroBased());
            Predicate<Customer> filterPersonToFind = p -> p.equals(customerToFind);
            bobaBotModel.updateFilteredPersonList(filterPersonToFind);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, bobaBotModel.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (isNull(predicate) && isNull(findPersonDescriptor.getEmail())) {
            return other == this
                    || (other instanceof FindCommand
                    && findPersonDescriptor.getPhone().equals(((FindCommand) other).findPersonDescriptor.getPhone()));
        } else if (isNull(predicate) && isNull(findPersonDescriptor.getPhone())) {
            return other == this
                    || (other instanceof FindCommand
                    && findPersonDescriptor.getEmail().equals(((FindCommand) other).findPersonDescriptor.getEmail()));
        }
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    /**
     * Stores the details of the Customer to find (either the phone number or email).
     */
    public static class FindPersonDescriptor {
        private Email email;
        private Phone phone;
        private boolean isPhoneEmpty = true;
        private boolean isEmailEmpty = true;

        public FindPersonDescriptor() {
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
            this.isPhoneEmpty = false;
        }

        public void setEmail(Email email) {
            this.email = email;
            this.isEmailEmpty = false;
        }

        public Phone getPhone() {
            return this.phone;
        }

        public Email getEmail() {
            return this.email;
        }

        public boolean isAnyFilled() {
            return this.isPhoneEmpty || this.isEmailEmpty;
        }
    }
}
