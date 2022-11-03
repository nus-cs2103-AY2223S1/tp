package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROUND;

import java.util.List;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.exceptions.InvalidInterviewException;

/**
 * Adds an application to CinternS.
 */
public class AddInterviewCommand extends Command {

    public static final String COMMAND_WORD = "interview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to application in CinternS. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ROUND + "ROUND "
            + PREFIX_INTERVIEW_DATE + "INTERVIEW_DATE "
            + PREFIX_INTERVIEW_TIME + "INTERVIEW_TIME "
            + PREFIX_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROUND + "Technical interview 1 "
            + PREFIX_INTERVIEW_DATE + "2022-10-12 "
            + PREFIX_INTERVIEW_TIME + "1430 "
            + PREFIX_LOCATION + "Zoom ";

    public static final String MESSAGE_SUCCESS = "New interview added to application: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview has clashed with another interview "
            + "currently stored in CinternS. Please ensure the interview you add is at least 1 hour before or after any"
            + " other interview.";
    public static final String MESSAGE_INVALID_INTERVIEW = "This interview date is before the application's "
            + "applied date.";


    private final Interview toAdd;
    private final Index index;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Application}
     *
     * @param index of the specified Application.
     * @param interview to be added to the Application.
     */
    public AddInterviewCommand(Index index, Interview interview) {
        requireNonNull(index);
        requireNonNull(interview);

        this.index = index;
        this.toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        boolean hasInterview = applicationToEdit.getInterview().isPresent();
        if (!hasInterview && model.hasSameInterviewTime(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }
        if (hasInterview && model.hasSameInterviewTimeExcludeSelf(toAdd, applicationToEdit)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }
        Application editedApplication;
        try {
            editedApplication = new Application(applicationToEdit, toAdd);
        } catch (InvalidInterviewException e) {
            throw new CommandException(MESSAGE_INVALID_INTERVIEW);
        }

        model.setApplication(applicationToEdit, editedApplication);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedApplication));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd));
    }
}
