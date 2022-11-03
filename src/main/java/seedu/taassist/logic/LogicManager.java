package seedu.taassist.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.logic.commands.Command;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.parser.TaAssistParser;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;
import seedu.taassist.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaAssistParser taAssistParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taAssistParser = new TaAssistParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = taAssistParser.parseCommand(commandText);
        commandResult = command.execute(model);

        if (commandResult.hasStorageAction()) {
            commandResult = commandResult.performStorageAction(storage);
        }

        try {
            storage.saveTaAssist(model.getTaAssist());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTaAssist getTaAssist() {
        return model.getTaAssist();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<StudentView> getStudentViewList() {
        return model.getStudentViewList();
    }

    @Override
    public ObservableList<ModuleClass> getModuleClassList() {
        return model.getModuleClassList();
    }

    @Override
    public ObservableList<Session> getSessionList() {
        return model.getSessionList();
    }

    @Override
    public Path getTaAssistFilePath() {
        return model.getTaAssistFilePath();
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
    public SimpleStringProperty getFocusLabelProperty() {
        return model.getFocusLabelProperty();
    }
}
