package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Syncs meetings to the local time on machine.
 * Deletes meetings with times that are before the present time.
 */
public class SyncCommand extends Command {
    public static final String COMMAND_WORD = "sync";

    public static final String MESSAGE_SUCCESS = "Removed past meetings.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.syncMeetingTimes();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
