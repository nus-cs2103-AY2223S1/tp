package nus.climods.logic;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
import nus.climods.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
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

    public ObservableList<Module> getFilteredModuleList() { return model.getFilteredModuleList(); }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("[User Command] " + commandText);

        CommandResult commandResult;
        Command command = CliModsParser.parseCommand(commandText);
        commandResult = command.execute(model);

        return commandResult;
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return model.getModuleList();
    }

    @Override
    public ObservableList<Module> getUserModuleList() {
        // TODO Add implementation for user module list
        return FXCollections.observableList(new ArrayList<>());
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
