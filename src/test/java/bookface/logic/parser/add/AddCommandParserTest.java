package bookface.logic.parser.add;

import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalPersons.BOB;

import bookface.logic.commands.CommandTestUtil;
import bookface.logic.commands.add.AddUserCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.person.Person;
import bookface.testutil.PersonBuilder;
import org.junit.jupiter.api.Test;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parseCommand_incompleteAdd_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("add"));
    }

    @Test
    public void parseCommand_wrongAddSubcommands_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("add !+x"));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, AddUserCommand.COMMAND_WORD
                + CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND, new AddUserCommand(expectedPerson));
    }
}
