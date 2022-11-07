package seedu.condonery.logic.parser.client;

import static seedu.condonery.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.condonery.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.condonery.testutil.TypicalClients.AMY_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.client.AddClientCommand;
import seedu.condonery.model.client.Client;
import seedu.condonery.testutil.ClientBuilder;

public class AddClientCommandParserTest {
    private final AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(AMY_CLIENT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + TAG_DESC_HUSBAND
                        + ADDRESS_DESC_AMY,
                new AddClientCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
                + NAME_DESC_AMY
                + TAG_DESC_HUSBAND
                + ADDRESS_DESC_AMY,
                new AddClientCommand(expectedClient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_AMY
                + TAG_DESC_HUSBAND
                + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB
                + ADDRESS_DESC_AMY,
                new AddClientCommand(expectedClient));
    }
}
