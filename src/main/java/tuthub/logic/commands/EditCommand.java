package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;
import static tuthub.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.commons.util.CollectionUtil;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

/**
 * Edits the details of an existing tutor in tuthub.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the tutor identified "
            + "by the index number used in the displayed tutor list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MODULE + "MODULE]... "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_STUDENTID + "STUDENT ID] "
            + "[" + PREFIX_TEACHINGNOMINATION + "TEACHING NOMINATIONS] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "E1234567@u.nus.edu";

    public static final String MESSAGE_EDIT_TUTOR_SUCCESS = "Edited Tutor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TUTOR = "This tutor already exists in Tuthub.";

    private final Index index;
    private final EditTutorDescriptor editTutorDescriptor;

    /**
     * @param index of the tutor in the filtered tutor list to edit
     * @param editTutorDescriptor details to edit the tutor with
     */
    public EditCommand(Index index, EditTutorDescriptor editTutorDescriptor) {
        requireNonNull(index);
        requireNonNull(editTutorDescriptor);

        this.index = index;
        this.editTutorDescriptor = new EditTutorDescriptor(editTutorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());
        Tutor editedTutor = createEditedTutor(tutorToEdit, editTutorDescriptor);

        if (!tutorToEdit.isSameTutor(editedTutor) && model.hasTutor(editedTutor)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
        }

        model.setTutor(tutorToEdit, editedTutor);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor));
    }

    /**
     * Creates and returns a {@code Tutor} with the details of {@code tutorToEdit}
     * edited with {@code editTutorDescriptor}.
     */
    private static Tutor createEditedTutor(Tutor tutorToEdit, EditTutorDescriptor editTutorDescriptor) {
        assert tutorToEdit != null;

        Name updatedName = editTutorDescriptor.getName().orElse(tutorToEdit.getName());
        Phone updatedPhone = editTutorDescriptor.getPhone().orElse(tutorToEdit.getPhone());
        Email updatedEmail = editTutorDescriptor.getEmail().orElse(tutorToEdit.getEmail());
        Set<Module> updatedModules = editTutorDescriptor.getModules().orElse(tutorToEdit.getModules());
        Year updatedYear = editTutorDescriptor.getYear().orElse(tutorToEdit.getYear());
        StudentId updatedStudentId = editTutorDescriptor.getStudentId().orElse(tutorToEdit.getStudentId());
        TeachingNomination updatedTeachingNomination =
                editTutorDescriptor.getTeachingNomination().orElse(tutorToEdit.getTeachingNomination());
        Rating updatedRating =
                editTutorDescriptor.getRating().orElse(tutorToEdit.getRating());
        CommentList updatedComments = tutorToEdit.getComments();
        Set<Tag> updatedTags = editTutorDescriptor.getTags().orElse(tutorToEdit.getTags());

        return new Tutor(updatedName, updatedPhone, updatedEmail,
            updatedModules, updatedYear, updatedStudentId, updatedComments, updatedTeachingNomination,
            updatedRating, updatedTags);
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
                && editTutorDescriptor.equals(e.editTutorDescriptor);
    }

    /**
     * Stores the details to edit the Tutor with. Each non-empty field value will replace the
     * corresponding field value of the Tutor.
     */
    public static class EditTutorDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Module> modules;
        private Year year;
        private StudentId studentId;
        private TeachingNomination teachingNomination;
        private CommentList comments;
        private Rating rating;
        private Set<Tag> tags;

        public EditTutorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code modules} is used internally.
         */
        public EditTutorDescriptor(EditTutorDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setModules(toCopy.modules);
            setYear(toCopy.year);
            setStudentId(toCopy.studentId);
            setTeachingNomination(toCopy.teachingNomination);
            setComments(toCopy.comments);
            setRating(toCopy.rating);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, modules, year, studentId, teachingNomination,
                    comments, rating, tags);
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

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            this.modules = modules == null ? null : new HashSet<>(modules);
        }

        /**
         * Returns an unmodifiable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setTeachingNomination(TeachingNomination teachingNomination) {
            this.teachingNomination = teachingNomination;
        }

        public Optional<TeachingNomination> getTeachingNomination() {
            return Optional.ofNullable(teachingNomination);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        public void setComments(CommentList comments) {
            this.comments = comments;
        }

        public Optional<CommentList> getComments() {
            return Optional.ofNullable(comments);
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
            if (!(other instanceof EditTutorDescriptor)) {
                return false;
            }

            // state check
            EditTutorDescriptor e = (EditTutorDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getModules().equals(e.getModules())
                    && getYear().equals(e.getYear())
                    && getStudentId().equals(e.getStudentId())
                    && getTeachingNomination().equals(e.getTeachingNomination())
                    && getRating().equals(e.getRating())
                    && getTags().equals(e.getTags());
        }
    }
}
