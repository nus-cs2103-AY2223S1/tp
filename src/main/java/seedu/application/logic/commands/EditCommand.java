package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.application.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.application.Status;
import seedu.application.model.tag.Tag;

/**
 * Edits the details of an existing application in the application book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of application identified "
            + "by the index number used in the displayed application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_CONTACT + "CONTACT] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_DATE + "DATE_APPLIED] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CONTACT + "91234567 "
            + PREFIX_EMAIL + "internships@shopee.com";

    public static final String MESSAGE_EDIT_APPLICATION_SUCCESS = "Edited Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already "
            + "exists in the application book.";

    private final Index index;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * Constructs EditCommand object.
     * @param index of the application in the filtered application list to edit
     * @param editApplicationDescriptor details to edit the application with
     */
    public EditCommand(Index index, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicationDescriptor);

        this.index = index;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        Application editedApplication = createEditedApplication(applicationToEdit, editApplicationDescriptor);

        if (!applicationToEdit.isSameApplication(editedApplication) && model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.setApplication(applicationToEdit, editedApplication);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication));

    }

    /**
     * Creates and returns a {@code Application} with the details of {@code applicationToEdit}
     * edited with {@code editApplicationDescriptor}.
     */
    private static Application createEditedApplication(Application applicationToEdit,
                                                       EditApplicationDescriptor editApplicationDescriptor) {
        assert applicationToEdit != null;

        Company updatedCompany = editApplicationDescriptor.getCompany().orElse(applicationToEdit.getCompany());
        Contact updatedContact = editApplicationDescriptor.getContact().orElse(applicationToEdit.getContact());
        Email updatedEmail = editApplicationDescriptor.getEmail().orElse(applicationToEdit.getEmail());
        Position updatedPosition = editApplicationDescriptor.getPosition().orElse(applicationToEdit.getPosition());
        Date updatedDate = editApplicationDescriptor.getDate().orElse(applicationToEdit.getDate());
        Status updatedStatus = editApplicationDescriptor.getStatus().orElse(applicationToEdit.getStatus());
        Set<Tag> updatedTags = editApplicationDescriptor.getTags().orElse(applicationToEdit.getTags());

        Application editedApplication = new Application(updatedCompany, updatedContact, updatedEmail, updatedPosition,
                updatedDate, updatedStatus, updatedTags);
        if (applicationToEdit.hasInterview()) {
            editedApplication = new Application(editedApplication, applicationToEdit.getInterview().get());
        }
        if (applicationToEdit.isArchived()) {
            editedApplication = editedApplication.setToArchive();
        }
        return editedApplication;
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
                && editApplicationDescriptor.equals(e.editApplicationDescriptor);
    }

    /**
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class EditApplicationDescriptor {
        private Company company;
        private Contact contact;
        private Email email;
        private Position position;
        private Date date;
        private Status status;
        private Set<Tag> tags;

        public EditApplicationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicationDescriptor(EditApplicationDescriptor toCopy) {
            setCompany(toCopy.company);
            setContact(toCopy.contact);
            setEmail(toCopy.email);
            setPosition(toCopy.position);
            setDate(toCopy.date);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, contact, email, position, date, status, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public Optional<Contact> getContact() {
            return Optional.ofNullable(contact);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
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
            if (!(other instanceof EditApplicationDescriptor)) {
                return false;
            }

            // state check
            EditApplicationDescriptor e = (EditApplicationDescriptor) other;

            return getCompany().equals(e.getCompany())
                    && getContact().equals(e.getContact())
                    && getEmail().equals(e.getEmail())
                    && getPosition().equals(e.getPosition())
                    && getDate().equals(e.getDate())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
