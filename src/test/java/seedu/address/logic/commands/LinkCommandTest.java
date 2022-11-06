package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.person.PersonId;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.PersonBuilder;

public class LinkCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_linkPersonAndInternship_linkSuccessful() throws Exception {
        model.addPerson(new PersonBuilder().withPersonId(0).build());
        model.addInternship(new InternshipBuilder().withInternshipId(0).build());

        expectedModel.addPerson(new PersonBuilder().withPersonId(0).withInternshipId(0).build());
        expectedModel.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(0).build());

        CommandResult commandResult = new LinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP).execute(model);

        assertEquals(commandResult.getFeedbackToUser(),
                String.format(LinkCommand.MESSAGE_SUCCESS, PersonBuilder.DEFAULT_NAME, InternshipBuilder.DEFAULT_NAME));
        assertEquals(model.findPersonById(new PersonId(0)), expectedModel.findPersonById(new PersonId(0)));
        assertEquals(model.findInternshipById(new InternshipId(1)),
                expectedModel.findInternshipById(new InternshipId(1)));
    }

    @Test
    public void execute_linkLinkedPersonAndInternship_throwCommandException() {
        model.addPerson(new PersonBuilder().withPersonId(0).withInternshipId(1).build()); //amy linked to ABCltd
        model.addInternship(new InternshipBuilder().withCompanyName("Google").withInternshipId(0).build()); //google
        model.addInternship(new InternshipBuilder().withInternshipId(1).withPersonId(0).build()); //ABCltd linked to amy

        //attempt to link amy with google
        Command command = new LinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(LinkCommand.MESSAGE_LINKED_PERSON,
                        PersonBuilder.DEFAULT_NAME,
                        InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertEquals(model.findPersonById(new PersonId(0)).getInternshipId(),
                model.findInternshipById(new InternshipId(1)).getInternshipId());
        assertNull(model.findInternshipById(new InternshipId(0)).getContactPersonId());
    }

    @Test
    public void execute_linkPersonAndLinkedInternship_throwCommandException() {
        model.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(1).build()); //ABC ltd
        model.addPerson(new PersonBuilder().withName("Bob").withPersonId(0).build()); //Bob
        model.addPerson(new PersonBuilder().withPersonId(1).withInternshipId(0).build()); //amy

        Command command = new LinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(LinkCommand.MESSAGE_LINKED_INTERNSHIP,
                        PersonBuilder.DEFAULT_NAME,
                        InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertEquals(model.findInternshipById(new InternshipId(0)).getContactPersonId(),
                model.findPersonById(new PersonId(1)).getPersonId());
        assertNull(model.findPersonById(new PersonId(0)).getInternshipId());
    }

    @Test
    public void execute_linkLinkedPersonAndLinkedInternship_throwCommandException() {
        model.addPerson(new PersonBuilder().withPersonId(0).withInternshipId(0).build()); //amy linked to ABCltd
        model.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(0).build()); //ABCltd linked to amy

        //attempt to link amy with ABCltd again
        Command command = new LinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(LinkCommand.MESSAGE_LINKED_PERSON,
                        PersonBuilder.DEFAULT_NAME,
                        InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertEquals(model.findPersonById(new PersonId(0)).getInternshipId(), new InternshipId(0));
        assertEquals(model.findInternshipById(new InternshipId(0)).getContactPersonId(), new PersonId(0));
    }
}
