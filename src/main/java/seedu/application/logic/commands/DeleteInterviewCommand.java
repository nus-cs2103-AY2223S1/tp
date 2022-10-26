package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;

/**
 * Deletes an interview identified using its displayed index on the CinternS interview list interface.
 */
public class DeleteInterviewCommand extends Command {

    public static final String COMMAND_WORD = "remove-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPLICATION_SUCCESS = "Deleted Interview: %1$s";

    private final Index targetIndex;

    public DeleteInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getApplicationListWithInterview();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Application applicationToDeleteInterview = lastShownList.get(targetIndex.getZeroBased());
        Application editedApplication = new Application(applicationToDeleteInterview);
        model.setApplication(applicationToDeleteInterview, editedApplication);
        model.updateApplicationListWithInterview();

        assert applicationToDeleteInterview.getInterview().isPresent();
        Interview deletedInterview = applicationToDeleteInterview.getInterview().get();
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICATION_SUCCESS, deletedInterview));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInterviewCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInterviewCommand) other).targetIndex)); // state check
    }
}
