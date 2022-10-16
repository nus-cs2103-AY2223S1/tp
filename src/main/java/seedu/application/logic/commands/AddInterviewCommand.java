package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.application.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;

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
            + PREFIX_LOCATION + "LOCATION "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROUND + "Technical interview 1 "
            + PREFIX_INTERVIEW_DATE + "2022-10-12 "
            + PREFIX_INTERVIEW_TIME + "1430 "
            + PREFIX_LOCATION + "Zoom ";

    public static final String MESSAGE_SUCCESS = "New interview added to application: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview has clashed with another interview "
            + "exists in CinternS";


    private final Interview toAdd;
    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Application}
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

        if (model.hasSameInterviewTime(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        List<Application> lastShownList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        Application editedApplication = new Application(applicationToEdit, toAdd);

        model.setApplication(applicationToEdit, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedApplication));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd));
    }
}
