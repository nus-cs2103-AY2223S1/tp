package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents the deadline of a task in the TaskList.
 */
public class Deadline extends Command {
    // Implement later.

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return null;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

}

