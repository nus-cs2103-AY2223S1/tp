package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "statistics";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("All statistics displayed");
    }
}