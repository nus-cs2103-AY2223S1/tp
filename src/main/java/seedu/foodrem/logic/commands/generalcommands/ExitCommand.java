package seedu.foodrem.logic.commands.generalcommands;

import static seedu.foodrem.commons.enums.CommandType.EXIT_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Exiting Address Book as requested ...";

    @Override
    public CommandResult<String> execute(Model model) {
        return new CommandResult<>() {
            @Override
            public String getOutput() {
                return MESSAGE_SUCCESS;
            }

            @Override
            public boolean shouldExit() {
                return true;
            }

            // TODO: Test this
            @Override
            public boolean equals(Object other) {
                if (other == this) {
                    return true;
                }
                if (!(other instanceof CommandResult)) {
                    return false;
                }

                CommandResult<?> asType = (CommandResult<?>) other;
                return super.equals(asType);
            }
        };
    }

    public static String getUsage() {
        return EXIT_COMMAND.getUsage();
    }
}
