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
    public static final String REPLACE = "r";
    public static final String KEEP = "k";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports a new trackAScholarImport.json file and merges it with the current trackAScholar file.\n"
            + "Please insert file at path data/trackAScholarImport.json with the exact name.\n"
            + "Parameters: 'r' (replace duplicates with imported applicants) / 'k' (keep existing duplicates)\n"
            + "Example: " + COMMAND_WORD + " r";

    public static final String MESSAGE_NO_FILE_FOUND_ERROR = "No File Found"
            + ": Please insert file to be imported into data/trackAScholarImport.json with the exact name.\n";
    public static final String MESSAGE_INVALID_FILE_DATA_FORMAT = "Invalid file data format!";
    public static final String MESSAGE_SUCCESS = "Imported new file";

    private final Path importedFilePath;
    private final String str;

    public ImportCommand(String str) {
        this.str = str;
        importedFilePath = Paths.get("data", "trackAScholarImport.json");
    }

    /**
     * ImportCommand only used for testing to import specific files instead of the expected file
     *
     * @param str to input 'k' or 'r'
     * @param newPath filePath of the imported file
     */
    public ImportCommand(String str, Path newPath) {
        assert newPath != null;
        this.str = str;
        importedFilePath = newPath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (Files.notExists(importedFilePath)) {
            throw new CommandException(MESSAGE_NO_FILE_FOUND_ERROR);
        }
        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        try {
            Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                    jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

            assert !optionalTrackAScholar.isEmpty();
            ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
            ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();

            if (str.equals(REPLACE)) {
                model.importWithReplace(applicantList);
            } else if (str.equals(KEEP)) {
                model.importWithoutReplace(applicantList);
            }
        } catch (DataConversionException e) {
            throw new CommandException(MESSAGE_INVALID_FILE_DATA_FORMAT);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && this.str.equals(((ImportCommand) other).str) // check str
                && this.importedFilePath.equals(((ImportCommand) other).importedFilePath)); // check path
    }


}
