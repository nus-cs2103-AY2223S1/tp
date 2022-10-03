package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Customises the user experience.
 */
public class CustomiseCommand extends Command {
    public static final String COMMAND_WORD = "customise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " order: Changes the order of the details.\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Example: " + COMMAND_WORD + " order p/ a/";

    public static final String MESSAGE_ORDER_SUCCESS = "Preferred order of details changed.";

    private final String subCommand;

    private final Attribute[] arguments;

    /**
     * The attributes of the contacts excluding name.
     */
    public enum Attribute {
        ADDRESS,
        EMAIL,
        PHONE,
        TAGS
    }

    /**
     * Creates an CustomiseCommand to customise user preferences.
     *
     * @param subCommand the sub command that is specified
     * @param args attributes
     */
    public CustomiseCommand(String subCommand, Attribute[] args) {
        this.subCommand = subCommand;
        this.arguments = args;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (subCommand.equals("order")) {
            GuiSettings currSettings = model.getGuiSettings();
            GuiSettings newSettings = new GuiSettings(
                    currSettings.getWindowWidth(),
                    currSettings.getWindowHeight(),
                    (int) currSettings.getWindowCoordinates().getX(),
                    (int) currSettings.getWindowCoordinates().getY(),
                    convertToStringFormat(arguments));

            model.setGuiSettings(newSettings);
            model.updateFilteredPersonList(unused -> false);
            model.updateFilteredPersonList(unused -> true);
            return new CommandResult(MESSAGE_ORDER_SUCCESS);
        }
        throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    /**
     * Converts a given Attribute array into a string.
     *
     * @param attributes an array of Attribute objects.
     * @return the string representation of the attribute order
     * @throws CommandException if the execution of the command encountered an error
     */
    private String convertToStringFormat(Attribute[] attributes) throws CommandException {
        if (attributes == null || attributes.length == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributes.length - 1; i++) {
            sb.append(attributes[i].toString()).append(">");
        }

        sb.append(attributes[attributes.length - 1]);

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CustomiseCommand
                && subCommand.equals(((CustomiseCommand) other).subCommand))
                && Arrays.equals(arguments, ((CustomiseCommand) other).arguments);
    }
}
