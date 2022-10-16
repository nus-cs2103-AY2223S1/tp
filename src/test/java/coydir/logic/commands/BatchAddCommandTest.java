package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;

class BatchAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    void execute_batchAddFileNoExist_throwsCommandException() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "filename.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("filename.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "File Not Found");
    }

    @Test
    void execute_batchAddFileDuplicates_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddDuplicatePerson.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddDuplicatePerson.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "One person in the list is found to be a duplicate. Call aborted");
    }
}
