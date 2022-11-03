package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.exceptions.DataConversionException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.storage.JsonTrackAScholarStorage;

public class ImportCommandTest {

    private final Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void constructor_nullApplicant_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new ImportCommand("r", null));
    }

    @Test
    public void execute_duplicateImportWithReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath =
                Paths.get("src/test/data/JsonImportCommandTest", "duplicateApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("r", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_similarImportWithReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath = Paths.get("src/test/data/JsonImportCommandTest", "similarApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("r", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_newImportWithReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath = Paths.get("src/test/data/JsonImportCommandTest", "newApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("r", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }


    @Test
    public void execute_duplicateImportWithoutReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath =
                Paths.get("src/test/data/JsonImportCommandTest", "duplicateApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("k", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithoutReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_similarImportWithoutReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath = Paths.get("src/test/data/JsonImportCommandTest", "similarApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("k", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithoutReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_newImportWithoutReplace_success() throws DataConversionException {
        // you want to show that current model after importing is the same as the
        // new model after importing
        Path importedFilePath = Paths.get("src/test/data/JsonImportCommandTest", "newApplicantTrackAScholar.json");
        ImportCommand importCommand = new ImportCommand("k", importedFilePath);

        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(importedFilePath);

        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());

        Optional<ReadOnlyTrackAScholar> optionalTrackAScholar =
                jsonTrackAScholarStorage.readTrackAScholar(importedFilePath);

        assert optionalTrackAScholar.isPresent();
        ReadOnlyTrackAScholar importedTrackAScholar = optionalTrackAScholar.get();
        ObservableList<Applicant> applicantList = importedTrackAScholar.getApplicantList();
        expectedModel.importWithoutReplace(applicantList);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void equals() {
        ImportCommand importReplaceNoPath = new ImportCommand(ImportCommand.REPLACE);
        ImportCommand importKeepNoPath = new ImportCommand(ImportCommand.KEEP);
        Path importedFilePath =
                Paths.get("src/test/data/JsonImportCommandTest", "duplicateApplicantTrackAScholar.json");
        ImportCommand importReplaceWPath = new ImportCommand(ImportCommand.REPLACE, importedFilePath);
        ImportCommand importKeepWPath = new ImportCommand(ImportCommand.KEEP, importedFilePath);

        // same object -> returns true
        assertTrue(importReplaceNoPath.equals(importReplaceNoPath));
        assertTrue(importReplaceWPath.equals(importReplaceWPath));
        assertTrue(importKeepNoPath.equals(importKeepNoPath));
        assertTrue(importKeepNoPath.equals(importKeepNoPath));


        // same values -> returns true
        ImportCommand importReplaceNoPathCopy = new ImportCommand(ImportCommand.REPLACE);
        assertTrue(importReplaceNoPath.equals(importReplaceNoPathCopy));
        ImportCommand importReplaceWPathCopy = new ImportCommand(ImportCommand.REPLACE, importedFilePath);
        assertTrue(importReplaceWPath.equals(importReplaceWPathCopy));
        ImportCommand importKeepWPathCopy = new ImportCommand(ImportCommand.KEEP, importedFilePath);
        assertTrue(importKeepWPath.equals(importKeepWPathCopy));

        // different types -> returns false
        assertFalse(importReplaceNoPath.equals(1));

        // null -> returns false
        assertFalse(importReplaceNoPath.equals(null));

        // different applicant -> returns false
        assertFalse(importReplaceNoPath.equals(importKeepNoPath));
    }


}
