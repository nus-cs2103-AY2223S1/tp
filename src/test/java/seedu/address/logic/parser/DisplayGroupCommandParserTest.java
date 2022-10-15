package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DisplayGroupCommand;
import seedu.address.model.group.FullGroupNamePredicate;

public class DisplayGroupCommandParserTest {

    private DisplayGroupCommandParser parser = new DisplayGroupCommandParser();

    @Test
    public void parse_validArgs_returnsDisplayGroupCommand() {
        FullGroupNamePredicate predicate = new FullGroupNamePredicate(VALID_GROUPNAME_TP);
        assertParseSuccess(parser, " " + VALID_GROUPNAME_TP,
                new DisplayGroupCommand(predicate));
    }

}
