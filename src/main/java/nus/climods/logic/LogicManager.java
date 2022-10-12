package nus.climods.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.logic.commands.Command;
import nus.climods.logic.commands.CommandResult;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.AddressBookParser;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;
import nus.climods.model.ReadOnlyAddressBook;
import nus.climods.model.module.UserModule;
import nus.climods.model.person.Person;
import nus.climods.storage.Storage;

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
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        logger.info("LogicManager#execute finished");

        return commandResult;
    }

    @Override
    public ObservableList<UserModule> getFilteredModuleList() throws ParseException{
        List<UserModule> UserModuleList = new ArrayList<>();
        UserModule test = new UserModule("CS1101S");
        UserModuleList.add(test);

        UserModule test2 = new UserModule("CS1010S");
        UserModuleList.add(test2);

        UserModule test3 = new UserModule("CS1010J");
        UserModuleList.add(test3);


        return FXCollections.observableList(UserModuleList);
    }

    @Override
    public ObservableList<UserModule> getFilteredSavedModuleList() throws ParseException {
        List<UserModule> savedUserModuleList = new ArrayList<>();
        UserModule test = new UserModule("CS2100");
        savedUserModuleList.add(test);
        savedUserModuleList.add(test);
        savedUserModuleList.add(test);
        savedUserModuleList.add(test);
        savedUserModuleList.add(test);

        return model.getFilteredUserModuleList();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
