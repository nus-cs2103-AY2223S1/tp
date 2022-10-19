package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
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
            + PREFIX_START_DATE_TIME + "START_DATE_TIME "
            + PREFIX_END_DATE_TIME + "END_DATE_TIME "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Mastery check 1 "
            + PREFIX_START_DATE_TIME + "2022-10-12T12:00 "
            + PREFIX_END_DATE_TIME + "2022-10-12T14:00 "
            + PREFIX_STUDENT_INDEX + "1 "
            + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New mastery check added: %1$s";
    public static final String MESSAGE_DUPLICATE_MASTERY_CHECK = "This mastery check already exists in JARVIS";

    private final LessonDesc masteryCheckDesc;
    private final TimePeriod masteryCheckPeriod;
    private final Set<Index> studentIndexSet;

    /**
     * Creates an AddMasteryCheckCommand to add the specified {@code MasteryCheck} with
     * {@code masteryCheckDesc, masteryCheckPeriod, studentIndexSet}
     */
    public AddMasteryCheckCommand(LessonDesc masteryCheckDesc, TimePeriod masteryCheckPeriod,
                                  Set<Index> studentIndexSet) {
        requireNonNull(masteryCheckDesc);
        requireNonNull(masteryCheckPeriod);
        requireNonNull(studentIndexSet);
        this.masteryCheckDesc = masteryCheckDesc;
        this.masteryCheckPeriod = masteryCheckPeriod;
        this.studentIndexSet = studentIndexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
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
        }

        model.addLesson(masteryCheckToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, masteryCheckToAdd), true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMasteryCheckCommand // instanceof handles nulls
                && masteryCheckDesc.equals(((AddMasteryCheckCommand) other).masteryCheckDesc))
                && masteryCheckPeriod.equals(((AddMasteryCheckCommand) other).masteryCheckPeriod)
                && studentIndexSet.equals(((AddMasteryCheckCommand) other).studentIndexSet);
    }
}
