package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.testutil.TypicalIndexes.ID_FIRST_EMPLOYEE;
import static coydir.testutil.TypicalIndexes.ID_SECOND_EMPLOYEE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import coydir.commons.core.Messages;
import coydir.model.Database;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.model.person.Rating;
import coydir.testutil.PersonBuilder;

public class RateCommandTest {

    @Test
    public void execute_validRate_success() {
        Model model = new ModelManager(new Database(), new UserPrefs());
        Rating rating1 = new Rating("3");
        Rating rating2 = new Rating("5");
        Person personToRate = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating(rating2.toString()).withTags("friends").withTotalLeave(15)
                .withEmployeeId("1").build();

        Person personToRateCopy = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating(rating2.toString()).withTags("friends").withTotalLeave(15)
                .withEmployeeId("1").build();

        model.addPerson(personToRateCopy);
        RateCommand rateCommand = new RateCommand(ID_FIRST_EMPLOYEE, rating1);
        String expectedMessage = String.format(RateCommand.MESSAGE_RATE_SUCCESS, personToRate.getName());
        personToRate.setRating(rating1);
        personToRate.addRating(rating1);
        ModelManager expectedModel = new ModelManager(new Database(), new UserPrefs());
        expectedModel.addPerson(personToRate);

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_employeeRated_throwsCommandException() {
        Rating rating = new Rating("2");
        Rating rating2 = new Rating("4");

        Model model = new ModelManager(new Database(), new UserPrefs());
        Person personRated = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(10)
                .withEmployeeId("1").build();
        personRated.addRating(rating2); // add a rating to personRated with same localdate timestamp
        model.addPerson(personRated);
        RateCommand rateCommand = new RateCommand(ID_FIRST_EMPLOYEE, rating);

        assertCommandFailure(rateCommand, model, RateCommand.MESSAGE_EMPLOYEE_RATING_COMPLETED);
    }

    @Test
    public void execute_invalidID_throwsCommandException() {
        Rating rating = new Rating("2");
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person person = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(10)
                .withEmployeeId("1").build();
        model.addPerson(person);
        RateCommand rateCommand = new RateCommand(ID_SECOND_EMPLOYEE, rating);
        assertCommandFailure(rateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EmployeeId id = new EmployeeId("1");
        EmployeeId id2 = new EmployeeId("2");
        Rating rating1 = new Rating("4");
        Rating rating2 = new Rating("5");
        RateCommand rateCommand1 = new RateCommand(id, rating1);
        RateCommand rateCommand2 = new RateCommand(id, rating2);
        RateCommand rateCommand3 = new RateCommand(id2, rating1);

        // same object -> returns true
        assertTrue(rateCommand1.equals(rateCommand1));

        // same values -> returns true
        RateCommand rateCommand1Copy = new RateCommand(id, rating1);
        assertTrue(rateCommand1.equals(rateCommand1Copy));

        // different types -> returns false
        assertFalse(rateCommand1.equals(1));

        // null -> returns false
        assertFalse(rateCommand1.equals(null));

        // different ratings -> returns false
        assertFalse(rateCommand1.equals(rateCommand2));

        // different ID -> returns false
        assertFalse(rateCommand1.equals(rateCommand3));
    }

}
