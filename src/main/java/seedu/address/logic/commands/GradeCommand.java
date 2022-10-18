package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.subject.Subject;

/**
 * Lists all persons in the address book to the user.
 * This starts a while loop that allows the use to input grades for each student,
 * for a specified Subject and assessment.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                               + ": Allows for editing of grades by subject and assessment.\n"
                                               + "This command has 2 MODES: \n"
                                               + "1) Edit grades for ALL students\n"
                                               + "2) Edit attendance for ONE student\n"
                                               + "Parameters: SUBJECT (must not be blank) "
                                               + "ASSESSMENT (must not be blank) MODE (must be 1 or 2). "
                                               + "1 for modifying ALL students' grades,\n"
                                               + "2 for modifying a single student's grade.";
    public static final String MESSAGE_SUCCESS = "Grades for Subject %s, Assessment %s has been updated!";

    private final Subject subject;
    private final String assessment;
    private final int mode;

    /**
     * Creates a GradeCommand to edit the grades of the specified {@code Subject}
     * @param subject the Subject to edit grades for
     * @param assessmentName the name of the assessment to edit grades for
     */
    public GradeCommand(Subject subject, String assessmentName, int mode) {
        this.subject = subject;
        this.assessment = assessmentName;
        this.mode = mode;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
