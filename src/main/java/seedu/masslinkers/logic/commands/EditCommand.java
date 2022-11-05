package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_PHONE_WARNING;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.masslinkers.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.commons.util.CollectionUtil;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;

/**
 * Edits the details of an existing student in the mass linkers.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MODS_PASSED_TO_EDIT = "Use [mod] commands to edit mods.";
    public static final String MESSAGE_USAGE = "Edit a batchmate's information in this manner: "
            +
            "\nedit INDEX " + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_GITHUB + "GITHUB] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_INTEREST + "INTEREST]...";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS_WARN = MESSAGE_PHONE_WARNING + MESSAGE_EDIT_STUDENT_SUCCESS;
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "The Telegram handle/"
            + "GitHub username/email/phone number already exist(s) in Mass Linkers.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        Telegram editedTelegram = editStudentDescriptor.getTelegram().orElse(null);
        GitHub editedGitHub = editStudentDescriptor.getGitHub().orElse(null);
        Email editedEmail = editStudentDescriptor.getEmail().orElse(null);
        Phone editedPhone = editStudentDescriptor.getPhone().orElse(null);
        if ((editedTelegram != null && model.hasTelegram(editedTelegram))
            || (editedGitHub != null && model.hasGitHub(editedGitHub))
            || (editedEmail != null && model.hasEmail(editedEmail))
            || (editedPhone != null && model.hasPhone(editedPhone))) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        if (editedStudent.hasIncorrectPhone()) { //warns whenever the edited student has an incorrect phone
            return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS_WARN, editedStudent),
                    false, false, true, false);
        }
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, true, false);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Telegram updatedTelegram = editStudentDescriptor.getTelegram().orElse(studentToEdit.getTelegram());
        GitHub updatedGitHub = editStudentDescriptor.getGitHub().orElse(studentToEdit.getGitHub());
        Set<Interest> updatedInterests = editStudentDescriptor.getInterests().orElse(studentToEdit.getInterests());
        ObservableList<Mod> mods = studentToEdit.getMods();

        return new Student(updatedName, updatedPhone, updatedEmail, updatedTelegram, updatedGitHub,
                updatedInterests, mods);
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
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Telegram handle;
        private GitHub username;
        private Set<Interest> interests;
        private ObservableList<Mod> mods;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code interests} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegram(toCopy.handle);
            setGitHub(toCopy.username);
            setInterests(toCopy.interests);
        }

        /**
         * Returns true if at least one field is edited except the mods field.
         * Editing mods should be done on a separate command.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, handle, username, interests);
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

        public void setTelegram(Telegram handle) {
            this.handle = handle;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(handle);
        }

        public void setGitHub(GitHub username) {
            this.username = username;
        }

        public Optional<GitHub> getGitHub() {
            return Optional.ofNullable(username);
        }
        /**
         * Sets {@code interests} to this object's {@code interests}.
         * A defensive copy of {@code interests} is used internally.
         */
        public void setInterests(Set<Interest> interests) {
            this.interests = (interests != null) ? new HashSet<>(interests) : null;
        }

        /**
         * Returns an unmodifiable interest set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code interests} is null.
         */
        public Optional<Set<Interest>> getInterests() {
            return (interests != null) ? Optional.of(Collections.unmodifiableSet(interests)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable mod set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code mods} is null.
         */
        public Optional<ObservableList<Mod>> getMods() {
            return (mods != null) ? Optional.of(FXCollections.unmodifiableObservableList(mods)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getTelegram().equals(e.getTelegram())
                    && getInterests().equals(e.getInterests());
        }
    }
}
