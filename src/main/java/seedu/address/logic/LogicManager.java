package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PennyWiseParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPennyWise;
import seedu.address.model.entry.Entry;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PennyWiseParser pennyWiseParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        pennyWiseParser = new PennyWiseParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = pennyWiseParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePennyWise(model.getPennyWise());
            logger.info("TRYING TO LOGGGG ===========");
            logger.info(model.getPennyWise().toString());
            logger.info(storage.readPennyWise().toString());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        } catch (DataConversionException e) {
            throw new RuntimeException(e);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPennyWise getPennyWise() {
        return model.getPennyWise();
    }

    @Override
    public ObservableList<Entry> getFilteredExpenditureList() {
        return model.getFilteredExpenditureList();
    }

    @Override
    public ObservableList<Entry> getFilteredIncomeList() {
        return model.getFilteredIncomeList();
    }

    @Override
    public ObservableList<PieChart.Data> getIncomePieChartData() {
        return model.getIncomePieChartData();
    }

    @Override
    public ObservableList<PieChart.Data> getExpensePieChartData() {
        return model.getExpensePieChartData();
    }

    @Override
    public Path getPennyWiseFilePath() {
        return model.getPennyWiseFilePath();
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
