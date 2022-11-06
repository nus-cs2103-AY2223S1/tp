package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertViewingCommandSuccess;
import static coydir.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import coydir.model.Database;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.Person;
import coydir.testutil.PersonBuilder;

public class ViewCommandTest {

    @Test
    public void viewSuccess() {
        Model model = new ModelManager(new Database(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(new Database(), new UserPrefs());
        Person personToView = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(15)
                .withEmployeeId("1").build();

        model.addPerson(personToView);
        expectedModel.addPerson(personToView);
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, personToView.getName());
        assertViewingCommandSuccess(viewCommand, model, expectedMessage, expectedModel, 0);
    }
}
