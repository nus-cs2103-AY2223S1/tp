package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.PersonNotFoundException;


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

    private final Predicate<Person> predicate;

    private Index targetIndex;

    private FindPersonDescriptor findPersonDescriptor;

    public FindCommand(Predicate<Person> predicate) {
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (!isNull(predicate)) {
            model.updateFilteredPersonList(predicate);
        } else {
            try {
                if (findPersonDescriptor.isPhoneEmpty) {
                    this.targetIndex = Index.fromZeroBased(model.findEmail(findPersonDescriptor.getEmail()));
                } else {
                    this.targetIndex = Index.fromZeroBased(model.findNum(findPersonDescriptor.getPhone()));
                }
            } catch (PersonNotFoundException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
            }

            List<Person> lastShownList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToFind = lastShownList.get(targetIndex.getZeroBased());
            Predicate<Person> filterPersonToFind = p -> p.equals(personToFind);
            model.updateFilteredPersonList(filterPersonToFind);
        }
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
     * Stores the details of the Person to find (either the phone number or email).
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
