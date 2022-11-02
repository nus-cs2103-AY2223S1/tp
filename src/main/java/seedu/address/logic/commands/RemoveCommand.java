package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
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
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.SessionList;
import seedu.address.model.tag.Tag;

/**
 * Removes the details of an existing person in the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the details of the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: "
            + "[" + PREFIX_HOMEWORK + "INDEX HOMEWORK]"
            + "[" + PREFIX_ATTENDANCE + "INDEX ATTENDANCE]"
            + "[" + PREFIX_SESSION + "INDEX SESSION]"
            + "[" + PREFIX_GRADE_PROGRESS + "INDEX GRADE PROGRESS]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_HOMEWORK + " 1 ";

    public static final String MESSAGE_REMOVED_PERSON_SUCCESS = "Removed Person Detail: %1$s";
    public static final String MESSAGE_NOT_REMOVED = "At least one field to remove must be provided.";
    public static final String MESSAGE_NOT_VIEW_MODE =
            "You need to be in full view mode to remove a person's details.";

    private final RemovePersonDescriptor removePersonDescriptor;

    /**
     * @param removePersonDescriptor details to remove person details with
     */
    public RemoveCommand(RemovePersonDescriptor removePersonDescriptor) {
        requireNonNull(removePersonDescriptor);

        this.removePersonDescriptor = removePersonDescriptor;
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
        Person editedPerson = createRemovedPerson(personToEdit, removePersonDescriptor);

        editedPerson.setFullView();
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_REMOVED_PERSON_SUCCESS, editedPerson));

    }

    private static Person createRemovedPerson(Person personToEdit, RemovePersonDescriptor removePersonDescriptor)
            throws CommandException {
        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        LessonPlan lessonPlan = personToEdit.getLessonPlan();
        HomeworkList updatedHomeworkList = getUpdatedHomeworkList(personToEdit, removePersonDescriptor);
        AttendanceList updatedAttendanceList = getUpdatedAttendanceList(personToEdit, removePersonDescriptor);
        GradeProgressList updatedGradeProgressList = getUpdatedGradeProgressList(personToEdit, removePersonDescriptor);
        SessionList updatedSessionList = getUpdatedSessionList(personToEdit, removePersonDescriptor);
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, lessonPlan, updatedHomeworkList,
                updatedAttendanceList, updatedSessionList, updatedGradeProgressList, tags);
    }

    public static HomeworkList getUpdatedHomeworkList(Person personToEdit,
            RemovePersonDescriptor removePersonDescriptor) throws CommandException {
        HomeworkList updatedHomeworkList = personToEdit.getHomeworkList();
        Optional<Index> homeworkIndex = removePersonDescriptor.getHomeworkIndex();
        if (homeworkIndex.isEmpty()) {
            return updatedHomeworkList;
        }
        if (!updatedHomeworkList.isValidIndex(homeworkIndex.get())) {
            throw new CommandException(HomeworkList.MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        updatedHomeworkList.removeAtIndex(homeworkIndex.get());
        return updatedHomeworkList;
    }

    public static AttendanceList getUpdatedAttendanceList(Person personToEdit,
            RemovePersonDescriptor removePersonDescriptor) throws CommandException {
        AttendanceList updatedAttendanceList = personToEdit.getAttendanceList();
        Optional<Index> attendanceIndex = removePersonDescriptor.getAttendanceIndex();
        if (attendanceIndex.isEmpty()) {
            return updatedAttendanceList;
        }
        if (!updatedAttendanceList.isValidIndex(attendanceIndex.get())) {
            throw new CommandException(AttendanceList.MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        updatedAttendanceList.removeAtIndex(attendanceIndex.get());
        return updatedAttendanceList;
    }

    public static SessionList getUpdatedSessionList(Person personToEdit,
            RemovePersonDescriptor removePersonDescriptor) throws CommandException {
        SessionList updatedSessionList = personToEdit.getSessionList();
        Optional<Index> sessionIndex = removePersonDescriptor.getSessionIndex();
        if (sessionIndex.isEmpty()) {
            return updatedSessionList;
        }
        if (!updatedSessionList.isValidIndex(sessionIndex.get())) {
            throw new CommandException(SessionList.MESSAGE_INVALID_SESSION_INDEX);
        }
        updatedSessionList.removeAtIndex(sessionIndex.get());
        return updatedSessionList;
    }

    public static GradeProgressList getUpdatedGradeProgressList(Person personToEdit,
            RemovePersonDescriptor removePersonDescriptor) throws CommandException {
        GradeProgressList updatedGradeProgressList = personToEdit.getGradeProgressList();
        Optional<Index> gradeProgressIndex = removePersonDescriptor.getGradeProgressIndex();
        if (gradeProgressIndex.isEmpty()) {
            return updatedGradeProgressList;
        }
        if (!updatedGradeProgressList.isValidIndex(gradeProgressIndex.get())) {
            throw new CommandException(GradeProgressList.MESSAGE_INVALID_GRADE_PROGRESS_INDEX);
        }
        updatedGradeProgressList.removeAtIndex(gradeProgressIndex.get());
        return updatedGradeProgressList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        // state check
        RemoveCommand r = (RemoveCommand) other;
        return removePersonDescriptor.equals(r.removePersonDescriptor);
    }

    /**
     * Stores the details to edit the person with.
     */
    public static class RemovePersonDescriptor {
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

        public RemovePersonDescriptor() { }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public RemovePersonDescriptor(RemovePersonDescriptor toCopy) {
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
         * Returns true if at least one field is removed.
         */
        public boolean isAnyFieldRemoved() {
            return CollectionUtil.isAnyNonNull(homeworkIndex, gradeProgressIndex,
                    attendanceIndex, sessionIndex);
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
            if (!(other instanceof RemovePersonDescriptor)) {
                return false;
            }

            // state check
            RemovePersonDescriptor r = (RemovePersonDescriptor) other;

            return getHomework().equals(r.getHomework())
                    && getHomeworkIndex().equals(r.getHomeworkIndex())
                    && getAttendance().equals(r.getAttendance())
                    && getAttendanceIndex().equals(r.getAttendanceIndex())
                    && getSession().equals(r.getSession())
                    && getSessionIndex().equals(r.getSessionIndex())
                    && getGradeProgress().equals(r.getGradeProgress())
                    && getGradeProgressIndex().equals(r.getGradeProgressIndex());
        }
    }
}
