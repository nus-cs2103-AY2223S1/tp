package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddLinkCommand;
import seedu.clinkedin.model.link.Link;

public class AddLinkCommandParserTest {
    private final AddLinkCommandParser parser = new AddLinkCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // No index given
        assertParseFailure(parser, "l/https://www.instagram.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));

        // No valid prefix given
        assertParseFailure(parser, "1 https://www.instagram.com", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddLinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parser_validArgs() throws MalformedURLException {
        URL url = new URL("https://www.instagram.com");
        Link link = new Link(url);
        Set<Link> set = new HashSet<>();
        set.add(link);
        AddLinkCommand expectedAddLinkCommand = new AddLinkCommand(Index.fromOneBased(1), set);
        assertParseSuccess(parser, "1 l/https://www.instagram.com", expectedAddLinkCommand);
        assertParseSuccess(parser, " \n \t1 l/https://www.instagram.com\t", expectedAddLinkCommand);
    }

}
