package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SessionList;
import seedu.address.model.tag.Tag;

/**
 * Unmarks the details of an existing person in the address book
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_HOMEWORK + "INDEX HOMEWORK]"
            + "[" + PREFIX_ATTENDANCE + "INDEX ATTENDANCE]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ATTENDANCE + " 1 ";

    public static final String MESSAGE_UNMARKED_PERSON_SUCCESS = "Unmarked Person Detail: %1$s";
    public static final String MESSAGE_NOT_VIEW_MODE =
            "You need to be in full view mode to unmark a person's details.";

    private final MarkPersonDescriptor markPersonDescriptor;

    /**
     * @param markPersonDescriptor details to edit the person with
     */
    public UnmarkCommand(MarkPersonDescriptor markPersonDescriptor) {
        requireNonNull(markPersonDescriptor);

        this.markPersonDescriptor = new MarkPersonDescriptor(markPersonDescriptor);
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

        Person personToUnmark = lastShownList.get(index.getZeroBased());
        Person unmarkedPerson = createUnmarkedPerson(personToUnmark, markPersonDescriptor);

        unmarkedPerson.setFullView();
        model.setPerson(personToUnmark, unmarkedPerson);
        return new CommandResult(String.format(MESSAGE_UNMARKED_PERSON_SUCCESS, unmarkedPerson));
    }

    private static Person createUnmarkedPerson(Person personToUnmark, MarkPersonDescriptor markPersonDescriptor)
            throws CommandException {
        Name name = personToUnmark.getName();
        Phone phone = personToUnmark.getPhone();
        LessonPlan lessonPlan = personToUnmark.getLessonPlan();
        HomeworkList updatedHomeworkList = getUpdatedHomeworkList(personToUnmark, markPersonDescriptor);
        AttendanceList updatedAttendanceList = getUpdatedAttendanceList(personToUnmark, markPersonDescriptor);
        SessionList sessionList = personToUnmark.getSessionList();
        GradeProgressList gradeProgressList = personToUnmark.getGradeProgressList();
        Set<Tag> tags = personToUnmark.getTags();

        return new Person(name, phone, lessonPlan, updatedHomeworkList,
                updatedAttendanceList, sessionList, gradeProgressList, tags);
    }

    public static HomeworkList getUpdatedHomeworkList(Person personToUnmark, MarkPersonDescriptor markPersonDescriptor)
            throws CommandException {
        HomeworkList updatedHomeworkList = personToUnmark.getHomeworkList();
        Optional<Index> homeworkIndex = markPersonDescriptor.getHomeworkIndex();
        if (homeworkIndex.isEmpty()) {
            return updatedHomeworkList;
        }
        if (!updatedHomeworkList.isValidIndex(homeworkIndex.get())) {
            throw new CommandException(HomeworkList.MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        updatedHomeworkList.unmarkAtIndex(homeworkIndex.get());
        return updatedHomeworkList;
    }

    public static AttendanceList getUpdatedAttendanceList(Person personToUnmark,
            MarkPersonDescriptor markPersonDescriptor) throws CommandException {
        AttendanceList updatedAttendanceList = personToUnmark.getAttendanceList();
        Optional<Index> attendanceIndex = markPersonDescriptor.getAttendanceIndex();
        if (attendanceIndex.isEmpty()) {
            return updatedAttendanceList;
        }
        if (!updatedAttendanceList.isValidIndex(attendanceIndex.get())) {
            throw new CommandException(AttendanceList.MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        updatedAttendanceList.unmarkAtIndex(attendanceIndex.get());
        return updatedAttendanceList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        // state check
        UnmarkCommand e = (UnmarkCommand) other;
        return markPersonDescriptor.equals(e.markPersonDescriptor);
    }

}
