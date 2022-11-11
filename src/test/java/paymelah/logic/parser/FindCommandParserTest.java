package paymelah.logic.parser;

import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import paymelah.logic.commands.FindCommand;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.testutil.DebtsDescriptorBuilder;
import paymelah.testutil.PersonDescriptorBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", FindCommand.MESSAGE_NO_KEYWORDS);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName("Alice").build(),
                new DebtsDescriptorBuilder().withDescriptions("burger").build()));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_DESCRIPTION + "burger", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PREFIX_NAME + "Alice \n \t " + PREFIX_DESCRIPTION + "burger  \t",
                expectedFindCommand);
    }

}
