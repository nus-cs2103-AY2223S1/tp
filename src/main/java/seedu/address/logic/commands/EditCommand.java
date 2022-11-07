package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
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
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.SessionList;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person currently "
            + "being viewed. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters (where INDEX is a positive integer): "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_LESSON_PLAN + "LESSON PLAN] "
            + "[" + PREFIX_HOMEWORK + "INDEX HOMEWORK]"
            + "[" + PREFIX_ATTENDANCE + "INDEX ATTENDANCE]"
            + "[" + PREFIX_SESSION + "INDEX SESSION]"
            + "[" + PREFIX_GRADE_PROGRESS + "INDEX GRADE PROGRESS]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example 1: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 \n"
            + "Example 2: " + COMMAND_WORD + " "
            + PREFIX_GRADE_PROGRESS + "1 Math:A";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_NOT_VIEW_MODE = "You need to be in full view mode to edit a person.";

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Index index = Index.fromOneBased(1);
        requireNonNull(model);
        if (!model.isFullView() || model.isDayView()) {
            throw new CommandException(MESSAGE_NOT_VIEW_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        String newName = editedPerson.getName().fullName;

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        editedPerson.setFullView();
        String[] newNameKeywords = {newName};
        model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        LessonPlan updatedLessonPlan = editPersonDescriptor.getLessonPlan()
                .orElse(personToEdit.getLessonPlan());
        HomeworkList updatedHomeworkList = getUpdatedHomeworkList(personToEdit, editPersonDescriptor);
        AttendanceList updatedAttendanceList = getUpdatedAttendanceList(personToEdit, editPersonDescriptor);
        GradeProgressList updatedGradeProgressList = getUpdatedGradeProgressList(personToEdit, editPersonDescriptor);
        SessionList updatedSessionList = getUpdatedSessionList(personToEdit, editPersonDescriptor);
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedLessonPlan,
                updatedHomeworkList, updatedAttendanceList, updatedSessionList, updatedGradeProgressList, updatedTags);
    }

    /**
     * Returns the updated {@code HomeworkList} if edited, or the original list if not.
     */
    public static HomeworkList getUpdatedHomeworkList(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        HomeworkList updatedHomeworkList = personToEdit.getHomeworkList();
        Optional<Homework> homework = editPersonDescriptor.getHomework();
        Optional<Index> homeworkIndex = editPersonDescriptor.getHomeworkIndex();
        if (homeworkIndex.isEmpty() || homework.isEmpty()) {
            return updatedHomeworkList;
        }
        if (!updatedHomeworkList.isValidIndex(homeworkIndex.get())) {
            throw new CommandException(HomeworkList.MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        updatedHomeworkList.editAtIndex(homeworkIndex.get(), homework.get());
        return updatedHomeworkList;
    }

    /**
     * Returns the updated {@code AttendanceList} if edited, or the original list if not.
     */
    public static AttendanceList getUpdatedAttendanceList(
            Person personToEdit, EditPersonDescriptor editPersonDescriptor) throws CommandException {
        AttendanceList updatedAttendanceList = personToEdit.getAttendanceList();
        Optional<Attendance> attendance = editPersonDescriptor.getAttendance();
        Optional<Index> attendanceIndex = editPersonDescriptor.getAttendanceIndex();
        if (attendanceIndex.isEmpty() || attendance.isEmpty()) {
            return updatedAttendanceList;
        }
        if (!updatedAttendanceList.isValidIndex(attendanceIndex.get())) {
            throw new CommandException(AttendanceList.MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        updatedAttendanceList.editAtIndex(attendanceIndex.get(), attendance.get());
        return updatedAttendanceList;
    }

    /**
     * Returns the updated {@code SessionList} if edited, or the original list if not.
     */
    public static SessionList getUpdatedSessionList(Person personToEdit,
            EditPersonDescriptor editPersonDescriptor) throws CommandException {
        SessionList updatedSessionList = personToEdit.getSessionList();
        Optional<Session> session = editPersonDescriptor.getSession();
        Optional<Index> sessionIndex = editPersonDescriptor.getSessionIndex();
        if (sessionIndex.isEmpty() || session.isEmpty()) {
            return updatedSessionList;
        }
        if (!updatedSessionList.isValidIndex(sessionIndex.get())) {
            throw new CommandException(SessionList.MESSAGE_INVALID_SESSION_INDEX);
        }
        updatedSessionList.editAtIndex(sessionIndex.get(), session.get());
        return updatedSessionList;
    }

    /**
     * Returns the updated {@code GradeProgressList} if edited, or the original list if not.
     */
    public static GradeProgressList getUpdatedGradeProgressList(
            Person personToEdit, EditPersonDescriptor editPersonDescriptor) throws CommandException {
        GradeProgressList updatedGradeProgressList = personToEdit.getGradeProgressList();
        Optional<GradeProgress> gradeProgress = editPersonDescriptor.getGradeProgress();
        Optional<Index> gradeProgressIndex = editPersonDescriptor.getGradeProgressIndex();
        if (gradeProgressIndex.isEmpty() || gradeProgress.isEmpty()) {
            return updatedGradeProgressList;
        }
        if (!updatedGradeProgressList.isValidIndex(gradeProgressIndex.get())) {
            throw new CommandException(GradeProgressList.MESSAGE_INVALID_GRADE_PROGRESS_INDEX);
        }
        updatedGradeProgressList.editAtIndex(gradeProgressIndex.get(), gradeProgress.get());
        return updatedGradeProgressList;
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
        return editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private LessonPlan lessonPlan;
        private Index homeworkIndex;
        private Homework homework;
        private Index gradeProgressIndex;
        private GradeProgress gradeProgress;
        private Index attendanceIndex;
        private Attendance attendance;
        private Index sessionIndex;
        private Session session;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setLessonPlan(toCopy.lessonPlan);
            setHomeworkIndex(toCopy.homeworkIndex);
            setHomework(toCopy.homework);
            setGradeProgressIndex(toCopy.gradeProgressIndex);
            setGradeProgress(toCopy.gradeProgress);
            setAttendanceIndex(toCopy.attendanceIndex);
            setAttendance(toCopy.attendance);
            setSessionIndex(toCopy.sessionIndex);
            setSession(toCopy.session);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, lessonPlan, homework,
                    gradeProgress, attendance, session, tags);
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

        public void setLessonPlan(LessonPlan lessonPlan) {
            this.lessonPlan = lessonPlan;
        }

        public Optional<LessonPlan> getLessonPlan() {
            return Optional.ofNullable(lessonPlan);
        }

        public void setHomework(Homework homework) {
            this.homework = homework;
        }

        public Optional<Homework> getHomework() {
            return Optional.ofNullable(homework);
        }

        public void setHomeworkIndex(Index homeworkIndex) {
            this.homeworkIndex = homeworkIndex;
        }

        public Optional<Index> getHomeworkIndex() {
            return Optional.ofNullable(homeworkIndex);
        }

        public void setGradeProgress(GradeProgress gradeProgress) {
            this.gradeProgress = gradeProgress;
        }

        public Optional<GradeProgress> getGradeProgress() {
            return Optional.ofNullable(gradeProgress);
        }

        public void setGradeProgressIndex(Index gradeProgressIndex) {
            this.gradeProgressIndex = gradeProgressIndex;
        }

        public Optional<Index> getGradeProgressIndex() {
            return Optional.ofNullable(gradeProgressIndex);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setAttendanceIndex(Index attendanceIndex) {
            this.attendanceIndex = attendanceIndex;
        }

        public Optional<Index> getAttendanceIndex() {
            return Optional.ofNullable(attendanceIndex);
        }

        public Optional<Session> getSession() {
            return Optional.ofNullable(session);
        }

        public Optional<Index> getSessionIndex() {
            return Optional.ofNullable(sessionIndex);
        }

        public void setSessionIndex(Index sessionIndex) {
            this.sessionIndex = sessionIndex;
        }

        public void setSession(Session session) {
            this.session = session;
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
                    && getPhone().equals(e.getPhone())
                    && getLessonPlan().equals(e.getLessonPlan())
                    && getHomework().equals(e.getHomework())
                    && getHomeworkIndex().equals(e.getHomeworkIndex())
                    && getGradeProgress().equals(e.getGradeProgress())
                    && getGradeProgressIndex().equals(e.getGradeProgressIndex())
                    && getAttendance().equals(e.getAttendance())
                    && getAttendanceIndex().equals(e.getAttendanceIndex())
                    && getSession().equals(e.getSession())
                    && getSessionIndex().equals(e.getSessionIndex())
                    && getTags().equals(e.getTags());
        }
    }
}
