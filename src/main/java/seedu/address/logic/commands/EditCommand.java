package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

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
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing internship in findMyIntern.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_LINK + "LINK] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_APPLIED_DATE + "APPLIED_DATE] "
            + "[" + PREFIX_INTERVIEW_DATE_TIME + "INTERVIEW_DATE_TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LINK + "https://careers.tiktok.com/position "
            + PREFIX_DESCRIPTION + "Global e-Commerce";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);
        assert index.getOneBased() > 0 : "index should be a positive integer";

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

        if (editedInternship.getInterviewDateTime() != null
                && editedInternship.getAppliedDate().getLocalDate()
                        .compareTo(editedInternship.getInterviewDateTime().getLocalDate()) > 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DATE);
        }

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(model.getCurrentPredicate());
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                                     EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Company updatedCompany = editInternshipDescriptor.getCompany().orElse(internshipToEdit.getCompany());
        Link updatedLink = editInternshipDescriptor.getLink().orElse(internshipToEdit.getLink());
        Description updatedDescription = editInternshipDescriptor.getDescription()
                .orElse(internshipToEdit.getDescription());
        AppliedDate updatedAppliedDate = editInternshipDescriptor.getAppliedDate()
                .orElse(internshipToEdit.getAppliedDate());
        InterviewDateTime updatedInterviewDateTime = editInternshipDescriptor.getInterviewDateTime()
                .orElse(internshipToEdit.getInterviewDateTime());
        ApplicationStatus updatedApplicationStatus =
                editInternshipDescriptor.getApplicationStatus()
                        .orElse(internshipToEdit.getApplicationStatus());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags()
                .orElse(internshipToEdit.getTags());

        return new Internship(updatedCompany, updatedLink, updatedDescription,
                updatedApplicationStatus, updatedAppliedDate, updatedInterviewDateTime, updatedTags);
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
        private Company company;
        private Link link;
        private Description description;
        private AppliedDate appliedDate;
        private InterviewDateTime interviewDateTime;
        private ApplicationStatus applicationStatus;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompany(toCopy.company);
            setLink(toCopy.link);
            setDescription(toCopy.description);
            setAppliedDate(toCopy.appliedDate);
            setInterviewDateTime(toCopy.interviewDateTime);
            setApplicationStatus(toCopy.applicationStatus);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, link, description, appliedDate, interviewDateTime, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setLink(Link link) {
            this.link = link;
        }

        public Optional<Link> getLink() {
            return Optional.ofNullable(link);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAppliedDate(AppliedDate appliedDate) {
            this.appliedDate = appliedDate;
        }

        public Optional<AppliedDate> getAppliedDate() {
            return Optional.ofNullable(appliedDate);
        }

        public void setInterviewDateTime(InterviewDateTime interviewDateTime) {
            this.interviewDateTime = interviewDateTime;
        }

        public Optional<InterviewDateTime> getInterviewDateTime() {
            return Optional.ofNullable(interviewDateTime);
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
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getCompany().equals(e.getCompany())
                    && getLink().equals(e.getLink())
                    && getDescription().equals(e.getDescription())
                    && getAppliedDate().equals(e.getAppliedDate())
                    && getInterviewDateTime().equals(e.getInterviewDateTime())
                    && getApplicationStatus().equals(e.getApplicationStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
