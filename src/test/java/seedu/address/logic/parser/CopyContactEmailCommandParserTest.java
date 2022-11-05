package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.CopyContactEmailCommand;
import seedu.address.logic.parser.contact.CopyContactEmailCommandParser;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class CopyContactEmailCommandParserTest {

    private CopyContactEmailCommandParser parser = new CopyContactEmailCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyContactEmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //invalid tag name
        assertParseFailure(parser, "CS2100**",
                String.format(Tag.MESSAGE_CONSTRAINTS, CopyContactEmailCommand.MESSAGE_USAGE));

        //multiple tags
        assertParseFailure(parser, "CS2100 CS2102",
                String.format(Tag.MESSAGE_CONSTRAINTS, CopyContactEmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_success() {
        String tagKeyword1 = "CS2103T";
        Set<Tag> tagKeywords1 = new HashSet<>();
        tagKeywords1.add(new Tag(tagKeyword1));

        PersonContainsKeywordsPredicate pred = new PersonContainsKeywordsPredicate(tagKeywords1);
        assertParseSuccess(parser, "CS2103T", new CopyContactEmailCommand(pred));
    }
}
