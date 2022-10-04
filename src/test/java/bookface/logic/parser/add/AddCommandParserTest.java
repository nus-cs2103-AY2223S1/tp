package bookface.logic.parser.add;

import static bookface.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.add.AddCommand;
import bookface.logic.parser.exceptions.ParseException;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser(AddCommand.MESSAGE_USAGE);

    @Test
    public void parseCommand_incompleteAdd_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("add"));
    }

    @Test
    public void parseCommand_wrongAddSubcommands_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("add !+x"));
    }
}
