package seedu.condonery.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.ExitCommand;
import seedu.condonery.logic.commands.HelpCommand;
import seedu.condonery.logic.commands.UndoCommand;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.parser.CondoneryParser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CondoneryParser condoneryParser;

    private final Collection<Class<? extends Command>> commandsToIgnore =
            Arrays.asList(HelpCommand.class, ExitCommand.class, UndoCommand.class);

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        condoneryParser = new CondoneryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = condoneryParser.parseCommand(commandText);
        if (!commandsToIgnore.contains(command.getClass())) {
            model.addCommand(command);
        }

        commandResult = command.execute(model);

        try {
            storage.savePropertyDirectory(model.getPropertyDirectory());
            storage.saveClientDirectory(model.getClientDirectory());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPropertyDirectory getPropertyDirectory() {
        return model.getPropertyDirectory();
    }

    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return model.getFilteredPropertyList();
    }

    @Override
    public Path getPropertyDirectoryFilePath() {
        return model.getPropertyDirectoryFilePath();
    }

    @Override
    public ReadOnlyClientDirectory getClientDirectory() {
        return model.getClientDirectory();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public Path getClientDirectoryFilePath() {
        return model.getClientDirectoryFilePath();
    }

    @Override
    public Path getUserImageDirectoryPath() {
        return model.getUserPrefs().getUserImageDirectoryPath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
