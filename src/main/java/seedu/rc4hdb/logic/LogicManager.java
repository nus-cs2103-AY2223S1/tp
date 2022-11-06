package seedu.rc4hdb.logic;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.StorageModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.misccommands.MiscCommand;
import seedu.rc4hdb.logic.parser.Rc4hdbParser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.ui.ObservableItem;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final Rc4hdbParser residentBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        residentBookParser = new Rc4hdbParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = residentBookParser.parseCommand(commandText);
        CommandResult commandResult = execute(command);

        try {
            storage.saveResidentBook(model.getResidentBook());
            storage.saveVenueBook(model.getVenueBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    /**
     * Determines the type of {@code Command} that is being executed and passes the appropriate arguments to it.
     * <p>
     * Note: When more types of commands are added to the application, add them in this method accordingly.
     * <p/>
     *
     * @param command The command to be executed.
     * @return The {@code CommandResult} from executing the command.
     * @throws CommandException if the command does not belong to any of the command groups.
     */
    private CommandResult execute(Command command) throws CommandException {
        if (command instanceof MiscCommand) {
            return ((MiscCommand) command).execute();
        }
        if (command instanceof ModelCommand) {
            return ((ModelCommand) command).execute(model);
        }
        if (command instanceof StorageCommand) {
            return ((StorageCommand) command).execute(storage);
        }
        if (command instanceof StorageModelCommand) {
            return ((StorageModelCommand) command).execute(storage, model);
        }
        throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        return model.getFilteredResidentList();
    }

    @Override
    public ObservableItem<Venue> getCurrentlyDisplayedVenue() {
        return model.getCurrentlyDisplayedVenue();
    }

    @Override
    public ObservableList<Venue> getObservableVenues() {
        return model.getObservableVenues();
    }

    @Override
    public ObservableValue<Path> getObservableFolderPath() {
        return storage.getObservableFolderPath();
    }

    @Override
    public ObservableList<String> getVisibleFields() {
        return model.getVisibleFields();
    }

    @Override
    public ObservableList<String> getHiddenFields() {
        return model.getHiddenFields();
    }

}
