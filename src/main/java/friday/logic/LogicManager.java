package friday.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import friday.commons.core.GuiSettings;
import friday.commons.core.LogsCenter;
import friday.logic.commands.Command;
import friday.logic.commands.CommandResult;
import friday.logic.commands.exceptions.CommandException;
import friday.logic.parser.AddressBookParser;
import friday.logic.parser.exceptions.ParseException;
import friday.model.Model;
import friday.model.ReadOnlyFriday;
import friday.model.student.Student;
import friday.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFriday(model.getFriday());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFriday getAddressBook() {
        return model.getFriday();
    }

    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getFridayFilePath();
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
