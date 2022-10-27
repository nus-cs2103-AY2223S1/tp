package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Lesson;
import jarvis.model.LessonDesc;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.TimePeriod;

/**
 * Adds a studio lesson to the lesson book.
 */
public class AddStudioCommand extends Command {

    public static final String COMMAND_WORD = "addstudio";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a studio lesson to JARVIS.\n"
            + "Parameters: "
            + "[" + PREFIX_LESSON + "LESSON_DESC] "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_START_TIME + "START_TIME ["
            + PREFIX_END_DATE + "END_DATE] "
            + PREFIX_END_TIME + "END_TIME"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Studio 3 "
            + PREFIX_START_DATE + "2022-10-12 "
            + PREFIX_START_TIME + "14:00 "
            + PREFIX_END_TIME + "16:00 ";

    public static final String MESSAGE_SUCCESS = "New studio added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDIO = "This studio already exists in JARVIS";
    public static final String MESSAGE_TIME_PERIOD_CLASH = "This studio overlaps with another lesson in JARVIS";
    public static final String MESSAGE_START_DATE_AFTER_END_DATE =
            "This studio's end date time must be after start date time";

    private final LessonDesc studioDesc;
    private final TimePeriod studioPeriod;

    /**
     * Creates an AddStudioCommand to add the specified {@code Studio}
     * with {@code studioDesc, studioPeriod}
     */
    public AddStudioCommand(LessonDesc studioDesc, TimePeriod studioPeriod) {
        requireNonNull(studioPeriod);
        this.studioDesc = studioDesc;
        this.studioPeriod = studioPeriod;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> allStudentList = model.getFilteredStudentList();

        Studio studioToAdd = new Studio(studioDesc, studioPeriod, allStudentList);

        if (model.hasLesson(studioToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDIO);
        } else if (model.hasPeriodClash(studioToAdd)) {
            model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
            List<Lesson> allLessonList = model.getFilteredLessonList();
            allLessonList.stream().filter(studioToAdd::hasTimingConflict).forEach(Lesson::markClash);
            // Update for JavaFX
            for (Lesson l : allLessonList) {
                if (l.hasTimingConflict()) {
                    model.setLesson(l, l);
                }
            }
            throw new CommandException(MESSAGE_TIME_PERIOD_CLASH);
        }
        model.addLesson(studioToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studioToAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof AddStudioCommand)) { // instanceof handles nulls
            return false;
        }

        AddStudioCommand sc = (AddStudioCommand) other;

        boolean studioDescEquality;
        if (studioDesc == null) {
            studioDescEquality = sc.studioDesc == null;
        } else {
            studioDescEquality = sc.equals(sc.studioDesc);
        }

        return studioDescEquality && studioPeriod.equals(sc.studioPeriod);
    }


}
