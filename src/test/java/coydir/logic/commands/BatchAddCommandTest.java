package coydir.logic.commands;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;

import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.testutil.PersonBuilder;
import org.junit.jupiter.api.Test;

import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;

class BatchAddCommandTest {


    private static final Person KIM = new PersonBuilder().withName("Kim Meier").withPhone("84824249")
            .withEmail("kimmeier@example.com").withPosition("Frontend Engineer").withDepartment("Information Technology")
            .withAddress("Little India").withTags("PromotionComing").withEmployeeId("1").withLeave(20).build();
    private static final Person PETRIS = new PersonBuilder().withName("Petris Mueller").withPhone("96722343")
            .withPosition("Marketing Intern").withDepartment("Marketing")
            .withEmployeeId("2").build();
    private static final Person PAUL = new PersonBuilder().withName("Paul Morty").withEmail("paul@example.com")
            .withPosition("UI/UX Engineer").withDepartment("Sales")
            .withEmployeeId("3").build();

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    void execute_batchAdd_success() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddSuccess.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddSuccess.csv");
        batchAddCommand.setFilePath(testData);

        String expectedMessage = String.format(BatchAddCommand.MESSAGE_SUCCESS, 3);
        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.addPerson(KIM);
        expectedModel.addPerson(PETRIS);
        expectedModel.addPerson(PAUL);

        assertCommandSuccess(batchAddCommand, model, expectedMessage, expectedModel);
    }

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

    @Test
    void execute_batchAddFileMissingName_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddMissingName.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddMissingName.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, "Name, Position or Department is missing for one person!");
    }

    @Test
    void execute_batchAddFilePhoneIncorrectFormat_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddInvalidPhone.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddInvalidPhone.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model,"Phone numbers should only contain numbers, and it should be "
                + "from 3 digits to 15 digits long");
    }

    @Test
    void execute_batchAddFileEmpty_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddEmpty.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddEmpty.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model,String.format("BatchAddEmpty.csv does not have any data"));
    }

}
