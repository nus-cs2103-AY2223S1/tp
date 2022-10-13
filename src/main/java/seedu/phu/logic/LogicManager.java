package seedu.phu.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.phu.commons.core.GuiSettings;
import seedu.phu.commons.core.LogsCenter;
import seedu.phu.logic.commands.Command;
import seedu.phu.logic.commands.CommandResult;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.logic.parser.InternshipBookParser;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.Model;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;
import seedu.phu.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InternshipBookParser internshipBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internshipBookParser = new InternshipBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = internshipBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternshipBook(model.getInternshipBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return model.getInternshipBook();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInternshipBookFilePath() {
        return model.getInternshipBookFilePath();
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
