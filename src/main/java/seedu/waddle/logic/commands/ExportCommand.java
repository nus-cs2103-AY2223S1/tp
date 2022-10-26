package seedu.waddle.logic.commands;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Export an itinerary into pdf format.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": export current itinerary to PDF\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EXPORT_SUCCESS = "PDF created!";
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
            PDDocument doc = new PDDocument();
            PDPage myPage = new PDPage();
            doc.addPage(myPage);
            PDPage page = doc.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            contentStream.newLineAtOffset(0, 700);
            contentStream.showText(itinerary.toString());
            contentStream.endText();
            contentStream.close();
            doc.save("./data/" + itinerary.getName().description + ".pdf");
            doc.close();
        } catch (IOException e) {
            return new CommandResult(MESSAGE_EXPORT_FAILURE);
        }

        return new CommandResult(MESSAGE_EXPORT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof ExportCommand;

    }
}
