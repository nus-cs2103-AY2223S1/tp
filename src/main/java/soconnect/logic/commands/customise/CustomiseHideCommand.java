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
 * Customises the user experience by hiding attributes.
 */
public class CustomiseHideCommand extends CustomiseCommand {

    public static final String COMMAND_WORD = "hide";

    public static final String MESSAGE_USAGE = CustomiseCommand.COMMAND_WORD + " "
            + COMMAND_WORD + " : Hides details.\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Example: " + CustomiseCommand.COMMAND_WORD + " " + COMMAND_WORD + " p/ a/";

    public static final String MESSAGE_SUCCESS = "Attributes specified are hidden.";

    public final List<Attribute> attributeList;

    /**
     * Constructs a CustomiseHideCommand object.
     *
     * @param attributeList A List of attributes.
     */
    public CustomiseHideCommand(List<Attribute> attributeList) {
        requireNonNull(attributeList);
        this.attributeList = attributeList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        toggleAttributes(model, true, attributeList);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CustomiseHideCommand)
                && attributeList.equals(((CustomiseHideCommand) other).attributeList);
    }
}
