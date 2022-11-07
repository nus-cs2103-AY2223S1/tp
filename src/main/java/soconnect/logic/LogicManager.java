package soconnect.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.commons.core.LogsCenter;
import soconnect.logic.autocomplete.Autocomplete;
import soconnect.logic.autocomplete.AutocompleteManager;
import soconnect.logic.commands.Command;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.SoConnectParser;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.person.Person;
import soconnect.model.todo.Todo;
import soconnect.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final SoConnectParser soConnectParser;
    private final Autocomplete autocomplete;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        soConnectParser = new SoConnectParser();
        autocomplete = new AutocompleteManager(model.getSoConnect());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = soConnectParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveSoConnect(model.getSoConnect());
            autocomplete.updateSoConnect(model.getSoConnect());
            storage.saveTodoList(model.getTodoList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlySoConnect getSoConnect() {
        return model.getSoConnect();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public SimpleStringProperty getTodoListHeader() {
        return model.getTodoListHeader();
    }

    @Override
    public Path getSoConnectFilePath() {
        return model.getSoConnectFilePath();
    }

    @Override
    public ReadOnlyTodoList getTodoList() {
        return model.getTodoList();
    }

    @Override
    public ObservableList<Todo> getFilteredTodoList() {
        return model.getFilteredTodoList();
    }

    @Override
    public Path getTodoListFilePath() {
        return model.getTodoListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Autocomplete getAutocompleteManager() {
        return this.autocomplete;
    }

    @Override
    public String getAttributeOrder() {
        return model.getAttributeOrder();
    }

    @Override
    public String getHiddenAttributes() {
        return model.getHiddenAttributes();
    }
}
