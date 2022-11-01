package nus.climods.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.commons.core.LogsCenter;
import nus.climods.logic.commands.Command;
import nus.climods.logic.commands.CommandResult;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.CliModsParser;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;
import nus.climods.storage.Storage;
import nus.climods.storage.exceptions.StorageException;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    private void saveModuleList(boolean isSave) throws StorageException {
        if (isSave) {
            storage.saveUserModuleList(model.getUserModuleList());
        }
    }
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, StorageException {
        logger.info("[User Command] " + commandText);
        // clear module in focus before each command
        model.clearModuleInFocus();

        CommandResult commandResult;
        Command command = CliModsParser.parseCommand(commandText);
        commandResult = command.execute(model);
        saveModuleList(commandResult.isSave());
        return commandResult;
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return model.getModuleList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<UserModule> getFilteredUserModuleList() {
        return model.getFilteredUserModuleList();
    }

    @Override
    public Path getUserModuleListPath() {
        return storage.getUserModuleListPath();
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
