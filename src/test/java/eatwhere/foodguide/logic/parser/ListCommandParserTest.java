package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.HELP_DESC;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_displayHelp_success() {
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser, HELP_DESC, ListCommand.MESSAGE_USAGE);
    }
}
