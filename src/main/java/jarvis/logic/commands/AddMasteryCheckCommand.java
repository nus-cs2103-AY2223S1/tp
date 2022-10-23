package jarvis.logic.commands;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.MasteryCheck;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.TimePeriod;

/**
 * Adds a mastery check lesson to the lesson book.
 */
public class AddMasteryCheckCommand extends Command {

    public static final String COMMAND_WORD = "addmc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a mastery check lesson to JARVIS.\n"
            + "Parameters: "
            + PREFIX_LESSON + "LESSON_DESC "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_START_TIME + "START_TIME ["
            + PREFIX_END_DATE + "END_DATE] "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX..."
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Mastery check 1 "
            + PREFIX_START_DATE + "2022-10-12 "
            + PREFIX_START_TIME + "12:00 "
            + PREFIX_END_TIME + "14:00 "
            + PREFIX_STUDENT_INDEX + "1 "
            + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New mastery check added: %1$s";
    public static final String MESSAGE_DUPLICATE_MASTERY_CHECK = "This mastery check already exists in JARVIS";
    public static final String MESSAGE_TIME_PERIOD_CLASH = "This mastery check overlaps with another lesson in JARVIS";
    public static final String MESSAGE_START_DATE_AFTER_END_DATE =
            "This mastery check's end date time must be after start date time";

    private final LessonDesc masteryCheckDesc;
    private final TimePeriod masteryCheckPeriod;
    private final Set<Index> studentIndexSet;

    /**
     * Creates an AddMasteryCheckCommand to add the specified {@code MasteryCheck} with
     * {@code masteryCheckDesc, masteryCheckPeriod, studentIndexSet}
     */
    public AddMasteryCheckCommand(LessonDesc masteryCheckDesc, TimePeriod masteryCheckPeriod,
                                  Set<Index> studentIndexSet) {
        requireAllNonNull(masteryCheckDesc, masteryCheckPeriod, studentIndexSet);
        this.masteryCheckDesc = masteryCheckDesc;
        this.masteryCheckPeriod = masteryCheckPeriod;
        this.studentIndexSet = studentIndexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        Set<Student> studentSet = new HashSet<>();
        for (Index studentIndex : studentIndexSet) {
            if (studentIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToAdd = lastShownList.get(studentIndex.getZeroBased());
            studentSet.add(studentToAdd);
        }
        LessonAttendance masteryCheckAttendance = new LessonAttendance(studentSet);
        LessonNotes masteryCheckNotes = new LessonNotes(studentSet);
        MasteryCheck masteryCheckToAdd = new MasteryCheck(masteryCheckDesc, masteryCheckPeriod,
                masteryCheckAttendance, masteryCheckNotes);

        if (model.hasLesson(masteryCheckToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MASTERY_CHECK);
        } else if (model.hasPeriodClash(masteryCheckToAdd)) {
            throw new CommandException(MESSAGE_TIME_PERIOD_CLASH);
        }

        model.addLesson(masteryCheckToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, masteryCheckToAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof AddMasteryCheckCommand)) { // instanceof handles nulls
            return false;
        }

        AddMasteryCheckCommand mc = (AddMasteryCheckCommand) other;

        return masteryCheckDesc.equals(mc.masteryCheckDesc)
                && masteryCheckPeriod.equals(mc.masteryCheckPeriod)
                && studentIndexSet.equals(mc.studentIndexSet);
    }
}
