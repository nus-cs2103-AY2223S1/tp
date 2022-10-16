package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AllTagCustomerCommand;
import seedu.address.model.customer.CustomerContainsAllTagPredicate;
import seedu.address.model.tag.Tag;

public class AllTagCustomerCommandParserTest {

    private AllTagCustomerCommandParser parser = new AllTagCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllTagCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAnyTagCustomerCommand() {
        // no leading and trailing whitespaces
        List<Tag> tagList = new ArrayList<Tag>();
        tagList.add(new Tag("tag1"));
        tagList.add(new Tag("tag2"));
        AllTagCustomerCommand expectedAllTagCustomerCommand =
                new AllTagCustomerCommand(new CustomerContainsAllTagPredicate(tagList));
        assertParseSuccess(parser, "tag1 tag2", expectedAllTagCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n tag1 \n \t tag2  \t", expectedAllTagCustomerCommand);
    }

}
