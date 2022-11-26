package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_LANGUAGE_TAG;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.commons.core.index.Index;
import seedu.workbook.commons.util.CollectionUtil;
import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.DateTime;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.model.tag.Tag;

/**
 * Edits the details of an existing internship in the WorkBook.
 */
public class EditCommand extends Command {

    /** Command word to execute the edit command */
    public static final String COMMAND_WORD = "edit";

    /** Help message to execute the edit command */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_STAGE + "STAGE] "
            + "[" + PREFIX_DATETIME + "DATE AND TIME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_LANGUAGE_TAG + "PROGRAMMING LANGUAGE]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "johndoe@example.com";

    /** Message string displaying successful execution of the edit command */
    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";

    /**
     * Message string displaying error message for unsuccessful execution of the edit command
     * for invalid input field
     */
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    /**
     * Message string displaying error message for unsuccessful execution of the edit command
     * for a duplicate internship
     */
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the work book.";

    /** Index of the internship to be edited */
    private final Index index;

    /** Description of edit */
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * Updates an indexed internship in the filtered internship list.
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);

        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        // Doesnt make sense to me to reset the filtering after editing an internship
        // its not that hard to just type list

        // model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        model.commitWorkBook();
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of
     * {@code internshipToEdit} edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
            EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Company updatedCompany = editInternshipDescriptor.getCompany().orElse(internshipToEdit.getCompany());
        Role updatedRole = editInternshipDescriptor.getRole().orElse(internshipToEdit.getRole());
        Email updatedEmail = editInternshipDescriptor.getEmail().orElse(internshipToEdit.getEmail());
        Stage updatedStage = editInternshipDescriptor.getStage().orElse(internshipToEdit.getStage());
        DateTime updatedDateTime = editInternshipDescriptor.getDate().orElse(internshipToEdit.getDateTime());
        Set<Tag> updatedLanguageTags = editInternshipDescriptor.getLanguageTags()
            .orElse(internshipToEdit.getLanguageTags());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(internshipToEdit.getTags());

        return new Internship(updatedCompany, updatedRole,
                updatedEmail, updatedStage, updatedDateTime, updatedLanguageTags, updatedTags);
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
                && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value
     * will replace the corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private Company company;
        private Role role;
        private Email email;
        private Stage stage;
        private DateTime dateTime;
        private Set<Tag> languageTags;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompany(toCopy.company);
            setRole(toCopy.role);
            setEmail(toCopy.email);
            setStage(toCopy.stage);
            setDate(toCopy.dateTime);
            setLanguageTags(toCopy.languageTags);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, role, email, stage, dateTime, languageTags, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }
        public void setStage(Stage stage) {
            this.stage = stage;
        }

        public Optional<Stage> getStage() {
            return Optional.ofNullable(stage);
        }

        public void setDate(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDate() {
            return Optional.ofNullable(dateTime);
        }

        /**
         * Sets {@code languageTags} to this object's {@code languageTags}.
         * A defensive copy of {@code languageTags} is used internally.
         */
        public void setLanguageTags(Set<Tag> languageTags) {
            this.languageTags = (languageTags != null) ? new HashSet<>(languageTags) : null;
        }

        /**
         * Returns an unmodifiable language tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getLanguageTags() {
            return (languageTags != null) ? Optional.of(Collections.unmodifiableSet(languageTags)) : Optional.empty();
        }


        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
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
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getCompany().equals(e.getCompany())
                    && getRole().equals(e.getRole())
                    && getEmail().equals(e.getEmail())
                    && getStage().equals(e.getStage())
                    && getDate().equals(e.getDate())
                    && getLanguageTags().equals(e.getLanguageTags())
                    && getTags().equals(e.getTags());
        }
    }
}
