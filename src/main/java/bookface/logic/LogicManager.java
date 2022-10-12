package bookface.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import bookface.commons.core.GuiSettings;
import bookface.commons.core.LogsCenter;
import bookface.logic.commands.Command;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.logic.parser.exceptions.ParseException;
import bookface.logic.parser.primary.PrimaryParser;
import bookface.model.Model;
import bookface.model.ReadOnlyBookFace;
import bookface.model.book.Book;
import bookface.model.person.Person;
import bookface.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PrimaryParser primaryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        primaryParser = new PrimaryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = primaryParser.parse(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveBookFace(model.getBookFace());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBookFace getBookFace() {
        return model.getBookFace();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public Path getBookFaceFilePath() {
        return model.getBookFaceFilePath();
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
