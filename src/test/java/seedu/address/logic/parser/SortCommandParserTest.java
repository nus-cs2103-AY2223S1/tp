package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.SortCommand;

import java.util.Arrays;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.PersonComparators.ADDRESS_COMPARATOR;
import static seedu.address.model.person.PersonComparators.NAME_COMPARATOR;
import static seedu.address.model.person.PersonComparators.ROLE_COMPARATOR;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    private final List<Prefix> invalidPrefixes = Arrays.asList(new Prefix[]{
            PREFIX_TAG, PREFIX_TIMEZONE, PREFIX_GITHUB,
            PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM
    });

    private final List<Prefix> validPrefixes = Arrays.asList(new Prefix[]{
            PREFIX_NAME, PREFIX_ADDRESS, PREFIX_ROLE
    });

    @Test
    public void parse_invalidUserInput_failure() {
        assertParseFailure(parser, "", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "none", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "invalid", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));

        for (Prefix prefix: invalidPrefixes) {
            for (String alias: prefix.getAliases()) {
                assertParseFailure(parser,
                        alias.replace("/", ""), // Remove the trailing slash
                        String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SortCommand.MESSAGE_USAGE));
            }
        }
    }

    @Test
    public void parse_validUserInput_success() {
        assertParseSuccess(parser,
                " " + PREFIX_NAME.toString().replace("/", ""), // Remove the trailing slash
                new SortCommand(NAME_COMPARATOR)
        );

        assertParseSuccess(parser,
                " " + PREFIX_ROLE.toString().replace("/", ""), // Remove the trailing slash
                new SortCommand(ROLE_COMPARATOR)
        );

        assertParseSuccess(parser,
                " " + PREFIX_ADDRESS.toString().replace("/", ""), // Remove the trailing slash
                new SortCommand(ADDRESS_COMPARATOR)
        );
    }
}
