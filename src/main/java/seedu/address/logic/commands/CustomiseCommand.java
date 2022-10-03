package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Customises the user experience.
 */
public class CustomiseCommand extends Command {
    public static final String COMMAND_WORD = "customise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": placeholder text ";

    public static final String MESSAGE_SUCCESS = "";

    private final String subCommand;

    private final String arguments;

    /**
     * Creates an CustomiseCommand to customise user preferences.
     *
     * @param subCommand the sub command that is specified
     * @param args attributes
     */
    public CustomiseCommand(String subCommand, String args) {
        this.subCommand = subCommand;
        this.arguments = args;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        GuiSettings currSettings = model.getGuiSettings();
        GuiSettings newSettings = new GuiSettings(
                currSettings.getWindowWidth(),
                currSettings.getWindowHeight(),
                (int) currSettings.getWindowCoordinates().getX(),
                (int) currSettings.getWindowCoordinates().getY(),
                arguments);
        model.setGuiSettings(newSettings);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
