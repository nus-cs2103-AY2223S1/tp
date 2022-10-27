package seedu.address.logic.parser;

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
    public void parse_validArgs_returnsAllTagCustomerCommand() {
        List<Tag> tagList = new ArrayList<Tag>();
        tagList.add(new Tag("tag1"));
        tagList.add(new Tag("tag 2"));
        tagList.add(new Tag("tag 3"));
        AllTagCustomerCommand expectedAllTagCustomerCommand =
                new AllTagCustomerCommand(new CustomerContainsAllTagPredicate(tagList));
        assertParseSuccess(parser, " t/tag1 t/tag 2 t/tag 3", expectedAllTagCustomerCommand);
    }

}
