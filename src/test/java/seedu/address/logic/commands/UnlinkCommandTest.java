package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

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




public class UnlinkCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_unlinkPersonAndInternship_unlinkSuccessful() throws Exception {

        model.addPerson(new PersonBuilder().withPersonId(0).withInternshipId(0).build());
        model.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(0).build());

        expectedModel.addPerson(new PersonBuilder().withPersonId(0).build());
        expectedModel.addInternship(new InternshipBuilder().withInternshipId(0).build());

        CommandResult commandResult = new UnlinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP).execute(model);

        assertEquals(commandResult.getFeedbackToUser(),
                String.format(UnlinkCommand.MESSAGE_SUCCESS,
                        PersonBuilder.DEFAULT_NAME,
                        InternshipBuilder.DEFAULT_NAME));
        assertEquals(model.findPersonById(new PersonId(0)), expectedModel.findPersonById(new PersonId(0)));
        assertEquals(model.findInternshipById(new InternshipId(1)),
                expectedModel.findInternshipById(new InternshipId(1)));
    }

    @Test
    public void execute_unlinkLinkedPersonAndUnlinkedInternship_throwCommandException() {
        model.addPerson(new PersonBuilder().withPersonId(0).withInternshipId(0).build()); //amy
        model.addInternship(new InternshipBuilder().withInternshipId(0).build()); //ABC ltd

        Command command = new UnlinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(UnlinkCommand.MESSAGE_UNLINKED_INTERNSHIP,
                        InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertEquals(model.findPersonById(new PersonId(0)).getInternshipId(), new InternshipId(0));
        assertNull(model.findInternshipById(new InternshipId(0)).getContactPersonId());
    }

    @Test
    public void execute_unlinkUnlinkedPersonAndLinkedInternship_throwCommandException() {
        model.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(0).build()); //ABC ltd
        model.addPerson(new PersonBuilder().withPersonId(0).build()); //amy

        Command command = new UnlinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(UnlinkCommand.MESSAGE_UNLINKED_PERSON,
                        PersonBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertEquals(model.findInternshipById(new InternshipId(0)).getContactPersonId(), new PersonId(0));
        assertNull(model.findPersonById(new PersonId(0)).getInternshipId());
    }

    @Test
    public void execute_unlinkCorrectPersonIncorrectInternship_throwCommandException() {
        model.addPerson(new PersonBuilder().withName("Bob").withPersonId(0).withInternshipId(0).build()); //Bob
        model.addPerson(new PersonBuilder().withPersonId(1).withInternshipId(0).build()); //amy
        model.addInternship(new InternshipBuilder().withInternshipId(0).withPersonId(0).build()); //ABC ltd

        Command command = new UnlinkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(UnlinkCommand.MESSAGE_INCORRECT_LINK_INTERNSHIP,
                        PersonBuilder.DEFAULT_NAME, InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
    }

    @Test
    public void execute_unlinkPerson_throwCommandException() {
        model.addPerson(new PersonBuilder().build()); //amy


        Command command = new UnlinkCommand(INDEX_FIRST_PERSON, null);

        assertThrows(CommandException.class,
                String.format(UnlinkCommand.MESSAGE_UNLINKED_PERSON,
                        PersonBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertNull(model.findPersonById(new PersonId(0)).getInternshipId());
    }

    @Test
    public void execute_unlinkInternship_throwCommandException() {
        model.addInternship(new InternshipBuilder().build()); //amy


        Command command = new UnlinkCommand(null, INDEX_FIRST_INTERNSHIP);

        assertThrows(CommandException.class,
                String.format(UnlinkCommand.MESSAGE_UNLINKED_INTERNSHIP,
                        InternshipBuilder.DEFAULT_NAME), () -> command.execute(model));
        assertNull(model.findInternshipById(new InternshipId(0)).getContactPersonId());
    }
}
