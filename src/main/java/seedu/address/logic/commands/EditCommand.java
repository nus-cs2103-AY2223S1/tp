package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMMATES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;
import seedu.address.model.teammate.Address;
import seedu.address.model.teammate.Email;
import seedu.address.model.teammate.Name;
import seedu.address.model.teammate.Phone;
import seedu.address.model.teammate.Teammate;

/**
 * Edits the details of an existing teammate in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the teammate identified "
            + "by the index number used in the displayed teammate list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_TEAMMATE_SUCCESS = "Edited Teammate: %1$s"
            + "\nThe following tasks' assigned contacts have been modified:";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TEAMMATE = "This teammate already exists in the address book.";

    private final Index index;
    private final EditTeammateDescriptor editTeammateDescriptor;

    /**
     * @param index of the teammate in the filtered teammate list to edit
     * @param editTeammateDescriptor details to edit the teammate with
     */
    public EditCommand(Index index, EditTeammateDescriptor editTeammateDescriptor) {
        requireNonNull(index);
        requireNonNull(editTeammateDescriptor);

        this.index = index;
        this.editTeammateDescriptor = new EditTeammateDescriptor(editTeammateDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Teammate> lastShownList = model.getFilteredTeammateList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
        }

        Teammate teammateToEdit = lastShownList.get(index.getZeroBased());
        Teammate editedTeammate = createEditedTeammate(teammateToEdit, editTeammateDescriptor);

        if (!teammateToEdit.isSameTeammate(editedTeammate) && model.hasTeammate(editedTeammate)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAMMATE);
        }

        updateTasksAssignedContacts(model, teammateToEdit, editedTeammate);
        model.setTeammate(teammateToEdit, editedTeammate);

        model.updateFilteredTeammateList(PREDICATE_SHOW_ALL_TEAMMATES);
        return new CommandResult(String.format(MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate));
    }

    private void updateTasksAssignedContacts(Model model, Teammate teammateToEdit,
                                             Teammate editedTeammate) throws CommandException {
        Contact contactToEdit = new Contact(teammateToEdit.getName().fullName);
        Contact editedContact = new Contact(editedTeammate.getName().fullName);
        model.updateFilteredTaskList(new AssignedToContactsPredicate(contactToEdit));
        List<Task> lastShownTaskList = new ArrayList<>(model.getFilteredTaskList());
        for (Task task : lastShownTaskList) {
            Set<Contact> newAssignedContactList = new HashSet<>(task.getAssignedContacts());
            newAssignedContactList.remove(contactToEdit);
            newAssignedContactList.add(editedContact);
            model.setTask(task, new Task(task.getTitle(), task.getCompleted(), task.getDeadline(), task.getProject(),
                    newAssignedContactList));
        }
        model.updateFilteredTaskList(new AssignedToContactsPredicate(editedContact));
    }

    /**
     * Creates and returns a {@code Teammate} with the details of {@code teammateToEdit}
     * edited with {@code editTeammateDescriptor}.
     */
    private static Teammate createEditedTeammate(
            Teammate teammateToEdit, EditTeammateDescriptor editTeammateDescriptor) {
        assert teammateToEdit != null;

        Name updatedName = editTeammateDescriptor.getName().orElse(teammateToEdit.getName());
        Phone updatedPhone = editTeammateDescriptor.getPhone().orElse(teammateToEdit.getPhone());
        Email updatedEmail = editTeammateDescriptor.getEmail().orElse(teammateToEdit.getEmail());
        Address updatedAddress = editTeammateDescriptor.getAddress().orElse(teammateToEdit.getAddress());
        Set<Tag> updatedTags = editTeammateDescriptor.getTags().orElse(teammateToEdit.getTags());

        return new Teammate(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTeammateDescriptor.equals(e.editTeammateDescriptor);
    }

    /**
     * Stores the details to edit the teammate with. Each non-empty field value will replace the
     * corresponding field value of the teammate.
     */
    public static class EditTeammateDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditTeammateDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTeammateDescriptor(EditTeammateDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTeammateDescriptor)) {
                return false;
            }

            // state check
            EditTeammateDescriptor e = (EditTeammateDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
