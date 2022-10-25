package seedu.trackascholar.logic.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.exceptions.DataConversionException;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.storage.JsonTrackAScholarStorage;

/**
 * Imports a new trackAScholarImport file as a model and merges it with the current model.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_SUCCESS = "Imported new file";
    public static final String REPLACE = "r";
    public static final String KEEP = "k";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports a new trackAScholarImport.json file and merges it with the current trackAScholar file.\n"
            + "Please insert file at path data/trackAScholarImport.json with the exact name.\n"
            + "Parameters: 'r' (replace duplicates with imported applicants) / 'k' (keep existing duplicates)\n"
            + "Example: " + COMMAND_WORD + " r";
    public static final String NO_FILE_ERROR = "No File Found"
            + ": Please insert file to be imported into data/trackAScholarImport.json with the exact name.\n";

    public static final String FILE_FORMAT_ERROR = "File data format is invalid";

    private final Path importedFilePath = Paths.get("data", "trackAScholarImport.json");
    private final String str;

    public ImportCommand(String str) {
        this.str = str;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (Files.notExists(importedFilePath)) {
            throw new CommandException(NO_FILE_ERROR);
        }
        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        try {
            Optional<ReadOnlyTrackAScholar> trackAScholar =
                    jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);
            ObservableList<Applicant> applicantList = trackAScholar.get().getApplicantList();
            if (str.equals(REPLACE)) {
                model.importWithReplace(applicantList);
            } else if (str.equals(KEEP)) {
                model.importWithoutReplace(applicantList);
            }
        } catch (DataConversionException e) {
            throw new CommandException(FILE_FORMAT_ERROR);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));

    }
}
