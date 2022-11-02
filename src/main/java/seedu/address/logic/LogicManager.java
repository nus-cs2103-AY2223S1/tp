package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.HealthContactParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHealthContact;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.bill.Bill;
import seedu.address.model.patient.Patient;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HealthContactParser healthContactParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        healthContactParser = new HealthContactParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = healthContactParser.parseCommand(commandText);
        model.getHistory().updateHealthContactHistory();
        if (!(command instanceof UndoCommand || command instanceof RedoCommand)) {
            model.getHistory().clearRedoHealthContactHistory();
            model.getHistory().clearRedoPatientsHistory();
            model.getHistory().clearRedoAppointmentsHistory();
            model.getHistory().clearRedoBillsHistory();
        }
        try {
            commandResult = command.execute(model);
        } catch (CommandException e) {
            logger.info("Invalid command: " + commandText);
            model.getHistory().deleteHealthContactHistory(model.getHistory().getHealthContactHistorySize() - 1);
            model.getHistory().deletePatientsHistory(model.getHistory().getPatientsHistorySize() - 1);
            model.getHistory().deleteAppointmentsHistory(model.getHistory().getAppointmentsHistorySize() - 1);
            model.getHistory().deleteBillsHistory(model.getHistory().getBillsHistorySize() - 1);
            throw e;
        }


        try {
            storage.saveHealthContact(model.getHealthContact());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHealthContact getHealthContact() {
        return model.getHealthContact();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public ObservableList<Bill> getFilteredBillList() {
        return model.getFilteredBillList();
    }

    @Override
    public Path getHealthContactFilePath() {
        return model.getHealthContactFilePath();
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
