package friday.logic.commands;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;

/**
 * Returns a link to the User Guide, which can be copied and pasted into a browser for reading.
 */
public class UgCommand extends Command {
    public static final String COMMAND_WORD = "guide";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns a link to FRIDAY's user guide, which can "
            + "accessed by copying and pasting this link into a browser.\n"
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;
    private static final String ugLink = "https://ay2223s1-cs2103t-w15-4.github.io/tp/UserGuide.html";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String output = String.format("Here is the link to the User Guide:\n"
                + " %s\n" + "Please copy the link and paste it into a browser to read the User Guide.", ugLink);
        return new CommandResult(output);
    }
}
