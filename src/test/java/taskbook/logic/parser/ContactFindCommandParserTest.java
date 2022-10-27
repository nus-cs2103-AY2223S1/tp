package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.contacts.ContactFindCommand;
import taskbook.logic.parser.contacts.ContactFindCommandParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Person;

public class ContactFindCommandParserTest {
    private ContactFindCommandParser parser = new ContactFindCommandParser();
    private Predicate<Person> dummy = p -> true;
    private ContactFindCommand control = new ContactFindCommand(dummy, "test");

    @Test
    public void parse_normalInput_success() {
        assertParseSuccess(parser, " q/test", control);
    }

    @Test
    public void parse_normalInput_failure() {
        try {
            ContactFindCommand command = parser.parse(" q/testy");
            assert !command.equals(dummy);
        } catch (ParseException e) {
            assert false;
        }
    }
}
