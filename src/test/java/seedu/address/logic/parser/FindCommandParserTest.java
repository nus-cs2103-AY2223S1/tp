package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_KEYWORDS_WITHOUT_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PREFIX_SPECIFIED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser(Model.ListType.STUDENT_LIST);

    @Test
    public void validateArgs_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_NO_PREFIX_SPECIFIED, FindCommand.FEEDBACK_MESSAGE));
    }

    @Test
    public void validateArgs_noSpecifiedPrefix_throwsParseException() {
        assertParseFailure(parser, "someone",
                String.format(MESSAGE_KEYWORDS_WITHOUT_PREFIX, FindCommand.FEEDBACK_MESSAGE));
    }
}
