package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.trackascholar.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.commons.util.CollectionUtil;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.tag.Tag;

/**
 * Edits the details of an existing applicant in TrackAScholar.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SCHOLARSHIP + "SCHOLARSHIP] "
            + "[" + PREFIX_APPLICATION_STATUS + "APPLICATION_STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in TrackAScholar.";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * @param index of the applicant in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditCommand(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

        if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.setApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant));
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    private static Applicant createEditedApplicant(
            Applicant applicantToEdit, EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = editApplicantDescriptor.getEmail().orElse(applicantToEdit.getEmail());
        Scholarship updatedScholarship = editApplicantDescriptor.getScholarship()
                .orElse(applicantToEdit.getScholarship());
        ApplicationStatus updatedApplicationStatus = editApplicantDescriptor.getApplicationStatus()
                .orElse(applicantToEdit.getApplicationStatus());
        Set<Tag> updatedTags = editApplicantDescriptor.getTags().orElse(applicantToEdit.getTags());

        return new Applicant(updatedName, updatedPhone, updatedEmail, updatedScholarship,
                updatedApplicationStatus, updatedTags);
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
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

    /**
     * Stores the details to edit the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Scholarship scholarship;
        private ApplicationStatus applicationStatus;
        private Set<Tag> tags;

        public EditApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setScholarship(toCopy.scholarship);
            setApplicationStatus(toCopy.applicationStatus);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, scholarship, applicationStatus, tags);
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

        public void setScholarship(Scholarship scholarship) {
            this.scholarship = scholarship;
        }

        public Optional<Scholarship> getScholarship() {
            return Optional.ofNullable(scholarship);
        }

        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
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
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            // state check
            EditApplicantDescriptor e = (EditApplicantDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getScholarship().equals(e.getScholarship())
                    && getApplicationStatus().equals(e.getApplicationStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
