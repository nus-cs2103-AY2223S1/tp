package seedu.foodrem.logic.commands.generalcommands;

import static seedu.foodrem.commons.enums.CommandType.EXIT_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    @Override
    public CommandResult<String> execute(Model model) {
        return new CommandResult<>() {
            @Override
            public String getOutput() {
                return "Exiting FoodRem as requested ...";
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
                try {
                    return getOutput().equals(asType.getOutput())
                            && super.equals(asType);
                } catch (UnsupportedOperationException e) {
                    return false;
                }
            }
        };
    }

    public static String getUsage() {
        return EXIT_COMMAND.getUsage();
    }
}
