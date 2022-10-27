package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.watson.logic.algorithm.PredictionUtil;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.person.Name;
import seedu.watson.model.person.subject.Subject;

/**
 * Gets the predicted score for a given student's next assessment for a subject
 * that they take.
 */
public class PredictionCommand extends Command {

    public static final String COMMAND_WORD = "predict";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Predicts the next assessment score"
        + "that a chosen student might get for a given Subject.\n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_SUBJECT + "SUBJECT";

    public static final String SHOWING_PREDICTION_MESSAGE = "Opened prediction window.";

    private static final String MESSAGE_FORMAT = "Grade prediction for %s is %.2f";

    private final Name name;
    private final Subject subject;

    /**
     * Creates a PredictionCommand to get the predicted score for a given student's next assessment
     */
    public PredictionCommand(Name name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        double gradePredicted = PredictionUtil.predictGrade(subject.getGrades());
        return new CommandResult(SHOWING_PREDICTION_MESSAGE, false, false, true, false,
            String.format(MESSAGE_FORMAT, name, 76.5));
    }
}
