package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_APPLICATION_PROCESS;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.phu.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.util.CollectionUtil;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;
import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.internship.Position;
import seedu.phu.model.internship.Remark;
import seedu.phu.model.internship.Website;
import seedu.phu.model.tag.Tag;

/**
 * Edits the details of an existing internship in the internship book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_APPLICATION_PROCESS + "APPLICATION PROCESS] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_WEBSITE + "WEBSITE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POSITION + "Quantitative Trader "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_APPLICATION_PROCESS + "INTERVIEW "
            + PREFIX_TAG + "money "
            + PREFIX_TAG + "trading ";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the internship book.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
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
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                                     EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Name updatedName = editInternshipDescriptor.getName().orElse(internshipToEdit.getName());
        Phone updatedPhone = editInternshipDescriptor.getPhone().orElse(internshipToEdit.getPhone());
        Email updatedEmail = editInternshipDescriptor.getEmail().orElse(internshipToEdit.getEmail());
        Remark updatedRemark = editInternshipDescriptor.getRemark().orElse(internshipToEdit.getRemark());
        Position updatedPosition = editInternshipDescriptor.getPosition().orElse(internshipToEdit.getPosition());
        ApplicationProcess updatedApplicationProcess = editInternshipDescriptor.getApplicationProcess()
                .orElse(internshipToEdit.getApplicationProcess());
        Date updatedDate = editInternshipDescriptor.getDate().orElse(internshipToEdit.getDate());
        Website updatedWebsite = editInternshipDescriptor.getWebsite().orElse(internshipToEdit.getWebsite());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(internshipToEdit.getTags());

        return new Internship(updatedName, updatedPhone, updatedEmail, updatedRemark, updatedPosition,
                updatedApplicationProcess, updatedDate, updatedWebsite, updatedTags);
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
     * Stores the details to edit the internship with. Each non-empty field value will replace the
     * corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Remark remark;
        private Position position;
        private ApplicationProcess applicationProcess;
        private Date date;
        private Website website;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRemark(toCopy.remark);
            setPosition(toCopy.position);
            setApplicationProcess(toCopy.applicationProcess);
            setDate(toCopy.date);
            setWebsite(toCopy.website);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, remark, position,
                    applicationProcess, website, date, tags);
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

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setApplicationProcess(ApplicationProcess applicationProcess) {
            this.applicationProcess = applicationProcess;
        }

        public Optional<ApplicationProcess> getApplicationProcess() {
            return Optional.ofNullable(applicationProcess);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setWebsite(Website website) {
            this.website = website;
        }

        public Optional<Website> getWebsite() {
            return Optional.ofNullable(website);
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
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getRemark().equals(e.getRemark())
                    && getPosition().equals(e.getPosition())
                    && getApplicationProcess().equals(e.getApplicationProcess())
                    && getDate().equals(e.getDate())
                    && getWebsite().equals(e.getWebsite())
                    && getTags().equals(e.getTags());
        }
    }
}
