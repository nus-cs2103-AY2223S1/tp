package coydir.logic.commands;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.Person;
import coydir.testutil.PersonBuilder;
import coydir.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.*;

class BatchAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    

    @Test
    void execute_batchAddFileNoExist_throwsCommandException() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest","filename.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("filename.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "File Not Found");
    }

    @Test
    void execute_batchAddFileDuplicates_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddDuplicatePerson.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest","BatchAddDuplicatePerson.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "One person in the list is found to be a duplicate. Call aborted");
    }
}
