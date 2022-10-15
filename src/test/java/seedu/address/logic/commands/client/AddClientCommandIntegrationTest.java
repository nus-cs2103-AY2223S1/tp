//package seedu.address.logic.commands.client;
//
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.client.Person;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.ui.StubUiManager;
//import seedu.address.ui.Ui;
//
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddClientCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newPerson_success() {
//        Person validPerson = new PersonBuilder().build();
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//
//        Ui stubUi = new StubUiManager();
//        expectedModel.addPerson(validPerson);
//
//        assertCommandSuccess(new AddClientCommand(validPerson), model,
//                String.format(AddClientCommand.MESSAGE_SUCCESS, validPerson), expectedModel, stubUi);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person personInList = model.getAddressBook().getPersonList().get(0);
//        Ui stubUi = new StubUiManager();
//        assertCommandFailure(
//                new AddClientCommand(personInList), model, AddClientCommand.MESSAGE_DUPLICATE_PERSON, stubUi);
//    }
//
//}
