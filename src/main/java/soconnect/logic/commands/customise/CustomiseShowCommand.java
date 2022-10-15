package soconnect.logic.commands.customise;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;

/**
 * Customises the user experience by showing attributes.
 */
public class CustomiseShowCommand extends CustomiseCommand {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = "customise " + COMMAND_WORD + " : Shows details\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Examples: " + COMMAND_WORD + " show p/ a/";

    public static final String MESSAGE_SUCCESS = "Attributes specified are shown.";

    public final List<Attribute> attributeList;

    /**
     * Constructs a CustomiseShowCommand object.
     *
     * @param attributeList A List of attributes.
     */
    public CustomiseShowCommand(List<Attribute> attributeList) {
        requireNonNull(attributeList);
        this.attributeList = attributeList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        toggleAttributes(model, false, attributeList);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CustomiseShowCommand)
                && attributeList.equals(((CustomiseShowCommand) other).attributeList);
    }
}
