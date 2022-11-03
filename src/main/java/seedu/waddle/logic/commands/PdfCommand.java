package seedu.waddle.logic.commands;

import java.io.IOException;

import seedu.waddle.logic.PdfFiller;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Export an itinerary into pdf format.
 */
public class PdfCommand extends Command {

    public static final String COMMAND_WORD = "pdf";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": export current itinerary to PDF\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EXPORT_SUCCESS =
            "Created a pdf for %1$s! Find it under Waddle in your Documents folder!";
    public static final String MESSAGE_EXPORT_FAILURE = "Failed to export!";

    public static final String MESSAGE_EXPORT_WRONG_STAGE = "Please select an itinerary before exporting.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager stageManager = StageManager.getInstance();

        if (stageManager.isCurrentStage(Stages.HOME)) {
            return new CommandResult(MESSAGE_EXPORT_WRONG_STAGE);
        }

        Itinerary itinerary = stageManager.getSelectedItinerary();

        try {
            String pdfTemplate = "/template/waddle_template.pdf";
            PdfFiller pdfFiller = new PdfFiller(itinerary, pdfTemplate);
            pdfFiller.fillItinerary();
        } catch (IOException e) {
            return new CommandResult(MESSAGE_EXPORT_FAILURE);
        }
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, itinerary.getDescription()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof PdfCommand;

    }
}
