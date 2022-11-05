package soconnect.logic.commands.customise;

import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static soconnect.model.Model.PREDICATE_SHOW_NO_PERSON;

import java.util.List;

import soconnect.commons.core.GuiSettings;
import soconnect.commons.core.Messages;
import soconnect.logic.commands.Command;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;

/**
 * Customises the user experience.
 */
public abstract class CustomiseCommand extends Command {

    public static final String COMMAND_WORD = "customise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " order: Changes the order of the details.\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Example: " + COMMAND_WORD + " order p/ a/\n\n"
            + COMMAND_WORD + " hide / " + COMMAND_WORD + " show: Changes the visibility of the details.\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]\n"
            + "Examples: " + COMMAND_WORD + " hide p/ a/, " + COMMAND_WORD + " show a/ e/ p/ t/";

    public static final String MESSAGE_UNKNOWN_CUSTOMISE_COMMAND = "Unknown customise command.\n%1$s";

    public static final String MESSAGE_DUPLICATE_ATTRIBUTE = "There should not be duplicate attributes.";

    public static final int NUMBER_OF_CUSTOMISABLE_ATTRIBUTES = 4; //0: ADDRESS, 1: EMAIL, 2: PHONE, 3: TAGS

    public static final String NONE = "NONE";

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
     * Toggles the attributes between being shown or hidden.
     *
     * @param model {@code Model} which the command operates on.
     * @throws CommandException If an error occurs during command execution.
     */
    protected void toggleAttributes(Model model, boolean isSetToHide, List<Attribute> attributeList)
            throws CommandException {
        if (attributeList == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        GuiSettings currSettings = model.getGuiSettings();
        String currHiddenAttributes = currSettings.getHiddenAttributes().trim();
        boolean[] isHidden = new boolean[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES];
        if (!currHiddenAttributes.equals(NONE)) {
            String[] currHiddenAttributesArr = currHiddenAttributes.split(",");
            try {
                setCurrentHiddenState(currHiddenAttributesArr, isHidden);
            } catch (IllegalArgumentException e) {
                isHidden = new boolean[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES];
            }
        }

        setNewHiddenState(isHidden, isSetToHide, attributeList);

        int xCoordinate = 0;
        int yCoordinate = 0;
        if (currSettings.getWindowCoordinates() != null) {
            xCoordinate = (int) currSettings.getWindowCoordinates().getX();
            yCoordinate = (int) currSettings.getWindowCoordinates().getY();
        }
        GuiSettings newSettings = new GuiSettings(currSettings.getWindowWidth(), currSettings.getWindowHeight(),
                xCoordinate, yCoordinate, currSettings.getAttributeOrder(), convertHiddenToString(isHidden));

        model.setGuiSettings(newSettings);
        refreshList(model);
    }

    /**
     * Sets the array elements to true if attributes are hidden based on the input String array.
     *
     * @param strArr An array of string representations of the attributes.
     * @param isHidden A boolean array where index 0 represents address, index 1 represents email
     *                 index 2 represents phone and index 3 represents tags.
     */
    private void setCurrentHiddenState(String[] strArr, boolean[] isHidden) {
        assert isHidden != null;

        for (String s : strArr) {
            isHidden[convertAttributeToIndex(s)] = true;
        }
    }

    /**
     * Sets the new attributes to be hidden based on the arguments.
     *
     * @param isHidden A boolean array where index 0 represents address, index 1 represents email,
     *                 index 2 represents phone and index 3 represents tags.
     * @param isSetToHide Is set to true to hide and false to show.
     */
    private void setNewHiddenState(boolean[] isHidden, boolean isSetToHide, List<Attribute> attributeList) {
        assert isHidden != null;

        for (Attribute argument : attributeList) {
            isHidden[convertAttributeToIndex(argument.toString())] = isSetToHide;
        }
    }

    /**
     * Converts the boolean array to a string representing what attributes are hidden.
     *
     * @param isHidden A boolean array where index 0 represents address, index 1 represents email,
     *                 index 2 represents phone and index 3 represents tags.
     * @return A string representation of what attributes are hidden.
     */
    private String convertHiddenToString(boolean[] isHidden) {
        assert isHidden != null;

        StringBuilder sb = new StringBuilder();
        if (isHidden[0]) {
            sb.append(ADDRESS).append(",");
        }
        if (isHidden[1]) {
            sb.append(EMAIL).append(",");
        }
        if (isHidden[2]) {
            sb.append(PHONE).append(",");
        }
        if (isHidden[3]) {
            sb.append(TAGS).append(",");
        }

        if (sb.length() == 0) {
            return NONE;
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Converts the given attribute into the index corresponding to the attribute.
     *
     * @param attribute The string representation of the attribute.
     * @return An index that corresponds to the attribute.
     */
    private int convertAttributeToIndex(String attribute) {
        switch(attribute) {
        case "ADDRESS":
            return 0;
        case "EMAIL":
            return 1;
        case "PHONE":
            return 2;
        case "TAGS":
            return 3;
        default:
            throw new IllegalArgumentException();
        }
    }


    /**
     * Refreshes the list.
     *
     * @param model {@code Model} which the command operates on.
     */
    protected static void refreshList(Model model) {
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSON);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
