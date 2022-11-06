package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddPersonCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.stubs.ModelStub;
import seedu.address.logic.commands.stubs.ModelStubWithPerson;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.logic.parser.PhoneConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

// TODO: Add implementation for tests
public class AddPersonCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = model;
    private final Command commandToBeTested = new AddPersonCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter())
            .registerConverter(Phone.class, new PhoneConverter());

    @Test
    public void execute_personAcceptedByModel_addSuccessful() {
        Person validPerson = new PersonBuilder().build();
        commandLine.parseArgs(PersonUtil.convertPersonToArgs(validPerson));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_SUCCESS, validPerson));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        ModelStub modelStub = new ModelStubWithPerson(validPerson);
        commandLine.parseArgs(PersonUtil.convertPersonToArgs(validPerson));
        assertThrows(CommandException.class, AddPersonCommand.MESSAGE_DUPLICATE_PERSON, ()
                -> commandToBeTested.execute(modelStub));
    }

    @Test
    public void equals() {
    }

}
