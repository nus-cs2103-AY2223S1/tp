package seedu.pennywise.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.commons.core.LogsCenter;
import seedu.pennywise.commons.exceptions.DataConversionException;
import seedu.pennywise.logic.commands.Command;
import seedu.pennywise.logic.commands.CommandResult;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.logic.parser.PennyWiseParser;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.storage.Storage;

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

        Command command = pennyWiseParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        try {
            storage.savePennyWise(model.getPennyWise());
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
    public XYChart.Series<String, Number> getExpenseLineChartData() {
        return model.getExpenseLineChartData();
    }

    @Override
    public XYChart.Series<String, Number> getIncomeLineChartData() {
        return model.getIncomeLineChartData();
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
