package coydir.logic.commands;

import static coydir.logic.commands.BatchAddCommand.MESSAGE_DUPLICATE_FOUND;
import static coydir.logic.commands.BatchAddCommand.MESSAGE_FILE_NOT_FOUND;
import static coydir.logic.commands.BatchAddCommand.MESSAGE_MISSING_COMP_FIELDS;
import static coydir.logic.commands.BatchAddCommand.MESSAGE_NO_DATA;
import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import coydir.model.Database;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.testutil.PersonBuilder;
import coydir.testutil.TypicalPersons;


class BatchAddCommandTest {


    private static final Person KIM = new PersonBuilder().withName("Kim Meier").withPhone("84824249")
            .withEmail("kimmeier@example.com").withPosition("Frontend Engineer")
            .withDepartment("Information Technology").withAddress("Little India").withTags("PromotionComing")
            .withEmployeeId("1").withTotalLeave(20).build();
    private static final Person PETRIS = new PersonBuilder().withName("Petris Mueller").withPhone("96722343")
            .withEmptyEmail().withEmptyAddress().withPosition("Marketing Intern").withDepartment("Marketing")
            .withEmployeeId("2").withTotalLeave(13).withEmployeeId("2").build();
    private static final Person PAUL = new PersonBuilder().withName("Paul Morty").withEmail("paul@example.com")
            .withEmptyPhone().withEmptyAddress().withPosition("UI/UX Engineer").withDepartment("Sales")
            .withEmployeeId("3").withTags("InnovationLead")
            .build();

    private Model model = new ModelManager(TypicalPersons.getTypicalDatabase(), new UserPrefs());

    @Test
    void execute_batchAdd_success() {
        Model emptyDatabase = new ModelManager(new Database(), new UserPrefs());
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddSuccess.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddSuccess.csv");
        batchAddCommand.setFilePath(testData);

        String expectedMessage = String.format(BatchAddCommand.MESSAGE_SUCCESS, 3);
        ModelManager expectedModel = new ModelManager(new Database(), new UserPrefs());
        EmployeeId.setCount(1);
        expectedModel.addPerson(KIM);
        expectedModel.addPerson(PETRIS);
        expectedModel.addPerson(PAUL);

        assertCommandSuccess(batchAddCommand, emptyDatabase, expectedMessage, expectedModel);
    }

    @Test
    void execute_batchAddFileNoExist_throwsCommandException() {
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "filename.csv");
        BatchAddCommand batchAddCommand = new BatchAddCommand("filename.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, MESSAGE_FILE_NOT_FOUND);
    }

    @Test
    void execute_batchAddFileDuplicates_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddDuplicatePerson.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddDuplicatePerson.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, MESSAGE_DUPLICATE_FOUND);
    }

    @Test
    void execute_batchAddFileMissingName_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddMissingName.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddMissingName.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, MESSAGE_MISSING_COMP_FIELDS);
    }

    @Test
    void execute_batchAddFilePhoneIncorrectFormat_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddInvalidPhone.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddInvalidPhone.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    void execute_batchAddFileEmpty_throwsCommandException() {
        BatchAddCommand batchAddCommand = new BatchAddCommand("BatchAddEmpty.csv");
        Path testData = Paths.get("src", "test", "data", "BatchAddTest", "BatchAddEmpty.csv");
        batchAddCommand.setFilePath(testData);
        assertCommandFailure(batchAddCommand, model, String.format(MESSAGE_NO_DATA, "BatchAddEmpty.csv"));
    }
}
