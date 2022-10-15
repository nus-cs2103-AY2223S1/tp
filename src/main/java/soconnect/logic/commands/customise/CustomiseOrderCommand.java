package soconnect.logic.commands.customise;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import soconnect.commons.core.GuiSettings;
import soconnect.commons.core.Messages;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;

/**
 * Customises the user experience by ordering attributes.
 */
public class CustomiseOrderCommand extends CustomiseCommand {

    public static final String COMMAND_WORD = "order";

    public static final String MESSAGE_USAGE = "customise " + COMMAND_WORD + " : Changes the order of the details\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Example: " + COMMAND_WORD + " order p/ a/";

    public static final String MESSAGE_SUCCESS = "Preferred order of details changed.";

    public final List<Attribute> attributeList;

    /**
     * Constructs a CustomiseOrderCommand object.
     *
     * @param attributeList The List of ordered attributes.
     */
    public CustomiseOrderCommand(List<Attribute> attributeList) {
        requireNonNull(attributeList);
        this.attributeList = attributeList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        GuiSettings currSettings = model.getGuiSettings();
        int xCoordinate = 0;
        int yCoordinate = 0;
        if (currSettings.getWindowCoordinates() != null) {
            xCoordinate = (int) currSettings.getWindowCoordinates().getX();
            yCoordinate = (int) currSettings.getWindowCoordinates().getY();
        }
        GuiSettings newSettings = new GuiSettings(currSettings.getWindowWidth(), currSettings.getWindowHeight(),
                xCoordinate, yCoordinate, convertOrderToString(attributeList), currSettings.getHiddenAttributes());

        model.setGuiSettings(newSettings);
        refreshList(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Converts a given Attribute array into a string.
     *
     * @param attributeList An array of Attribute objects.
     * @return The string representation of the attribute order.
     * @throws CommandException If the execution of the command encountered an error.
     */
    private String convertOrderToString(List<Attribute> attributeList) throws CommandException {
        if (attributeList == null || attributeList.size() != 4) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributeList.size() - 1; i++) {
            sb.append(attributeList.get(i).toString()).append(">");
        }

        sb.append(attributeList.get(attributeList.size() - 1));

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CustomiseOrderCommand)
                && attributeList.equals(((CustomiseOrderCommand) other).attributeList);
    }
}
