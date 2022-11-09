package seedu.guest.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.guest.commons.core.GuiSettings;
import seedu.guest.commons.core.LogsCenter;
import seedu.guest.logic.commands.Command;
import seedu.guest.logic.commands.CommandResult;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.logic.parser.GuestBookParser;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.Model;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.guest.Guest;
import seedu.guest.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final GuestBookParser guestBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        guestBookParser = new GuestBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = guestBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveGuestBook(model.getGuestBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyGuestBook getGuestBook() {
        return model.getGuestBook();
    }

    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return model.getFilteredGuestList();
    }

    @Override
    public Path getGuestBookFilePath() {
        return model.getGuestBookFilePath();
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
