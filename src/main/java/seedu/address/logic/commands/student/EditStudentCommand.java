package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNCHANGED_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "edit student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "STUDENT_ID] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM_HANDLE] "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in ModQuik.";
    public static final String MESSAGE_NON_EXISTING_MODULE = "The module does not exist in Modquik";
    public static final String MESSAGE_NON_EXISTING_TUTORIAL = "The tutorial does not exist in Modquik";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditStudentCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedPerson(studentToEdit, editPersonDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!model.hasModuleCode(editedStudent.getModuleCode())) {
            throw new CommandException(MESSAGE_NON_EXISTING_MODULE);
        }

        if (!model.hasTutorialName(editedStudent.getTutorialName())) {
            throw new CommandException(MESSAGE_NON_EXISTING_TUTORIAL);
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent), ModelType.STUDENT);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedPerson(Student studentToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert studentToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(studentToEdit.getName());
        StudentId updatedId = editPersonDescriptor.getId().orElse(studentToEdit.getId());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(studentToEdit.getEmail());
        TelegramHandle updatedTelegramHandle = editPersonDescriptor.getTelegram().orElse(studentToEdit.getTelegram());
        ModuleCode updatedModuleCode = editPersonDescriptor.getTutorialModule()
                .orElse(studentToEdit.getModuleCode());
        TutorialName updatedTutorialName = editPersonDescriptor.getTutorialName()
                .orElse(studentToEdit.getTutorialName());
        Attendance updatedAttendance = editPersonDescriptor.getAttendance()
                .orElse(studentToEdit.getAttendance());
        Participation updatedParticipation = editPersonDescriptor.getParticipation()
                .orElse(studentToEdit.getParticipation());
        Grade updatedGrade = editPersonDescriptor.getGrade()
                .orElse(studentToEdit.getGrade());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(studentToEdit.getTags());

        Student editedStudent = new Student(updatedName, updatedId, updatedPhone,
                updatedEmail, updatedTelegramHandle, updatedModuleCode,
                updatedTutorialName, updatedAttendance, updatedParticipation,
                updatedGrade, updatedTags);

        if (editedStudent.equals(studentToEdit)) {
            throw new CommandException(MESSAGE_UNCHANGED_FIELD);
        }

        return editedStudent;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private StudentId id;
        private Phone phone;
        private Email email;
        private TelegramHandle telegramHandle;
        private ModuleCode moduleCode;
        private TutorialName tutorialName;
        private Attendance attendance;
        private Participation participation;
        private Grade grade;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegram(toCopy.telegramHandle);
            setTutorialModule(toCopy.moduleCode);
            setTutorialName(toCopy.tutorialName);
            setAttendance(toCopy.attendance);
            setParticipation(toCopy.participation);
            setGrade(toCopy.grade);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, phone, email,
                    telegramHandle, moduleCode, tutorialName, attendance, participation, grade, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(StudentId id) {
            this.id = id;
        }

        public Optional<StudentId> getId() {
            return Optional.ofNullable(id);
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

        public void setTelegram(TelegramHandle telegramHandle) {
            this.telegramHandle = telegramHandle;
        }

        public Optional<TelegramHandle> getTelegram() {
            return Optional.ofNullable(telegramHandle);
        }

        public void setTutorialModule(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getTutorialModule() {
            return Optional.ofNullable(moduleCode);
        }

        public void setTutorialName(TutorialName tutorialName) {
            this.tutorialName = tutorialName;
        }

        public Optional<TutorialName> getTutorialName() {
            return Optional.ofNullable(tutorialName);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setParticipation(Participation participation) {
            this.participation = participation;
        }

        public Optional<Participation> getParticipation() {
            return Optional.ofNullable(participation);
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getId().equals(e.getId())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getTelegram().equals(e.getTelegram())
                    && getTutorialModule().equals(e.getTutorialModule())
                    && getTutorialName().equals(e.getTutorialName())
                    && getAttendance().equals(e.getAttendance())
                    && getParticipation().equals(e.getParticipation())
                    && getGrade().equals(e.getGrade())
                    && getTags().equals(e.getTags());
        }

    }
}
