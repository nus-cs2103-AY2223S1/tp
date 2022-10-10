package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the phone number or email used to register for membership.\n"
            + "Parameters: p/PHONE_NUMBER or e/EMAIL \n"
            + "Example: " + COMMAND_WORD + " p/81234567  or  " + COMMAND_WORD + " e/example@gmail.com";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private Index targetIndex;

    private final DeletePersonDescriptor deletePersonDescriptor;

    public DeleteCommand(DeletePersonDescriptor deletePersonDescriptor) {
        this.deletePersonDescriptor = deletePersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int index;
        try {
            if (deletePersonDescriptor.isPhoneEmpty) {
                index = model.findEmail(deletePersonDescriptor.getEmail());
            } else {
                index = model.findNum(deletePersonDescriptor.getPhone());
            }
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
        }

        this.targetIndex = Index.fromZeroBased(index);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        // state check
        DeleteCommand d = (DeleteCommand) other;
        return deletePersonDescriptor.equals(d.deletePersonDescriptor)
                && (targetIndex == null || targetIndex.equals(d.targetIndex));
    }

    /**
     * Stores the details of the Person to delete (either the phone number or email).
     */
    public static class DeletePersonDescriptor {
        private Email email;
        private Phone phone;
        private boolean isPhoneEmpty = true;
        private boolean isEmailEmpty = true;

        public DeletePersonDescriptor() {
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteCommand.DeletePersonDescriptor)) {
                return false;
            }

            // state check
            DeleteCommand.DeletePersonDescriptor d = (DeleteCommand.DeletePersonDescriptor) other;

            boolean isSamePhone = isPhoneEmpty || getPhone().equals(d.getPhone());
            boolean isSameEmail = isEmailEmpty || getEmail().equals(d.getEmail());

            return isPhoneEmpty == d.isPhoneEmpty
                    && isEmailEmpty == d.isEmailEmpty
                    && isAnyFilled() == d.isAnyFilled()
                    && isSamePhone
                    && isSameEmail;
        }
    }
}
