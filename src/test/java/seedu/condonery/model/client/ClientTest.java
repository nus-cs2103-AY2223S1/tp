package seedu.condonery.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_BOB;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalClients.AMY_CLIENT;
import static seedu.condonery.testutil.TypicalClients.BOB_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.condonery.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Client(null, null, null, null));
    }

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(AMY_CLIENT.isSameClient(AMY_CLIENT));

        // null -> returns false
        assertFalse(AMY_CLIENT.isSameClient(null));

        // same name, all other attributes different -> returns true
        Client editedAmy = new ClientBuilder().withName("Amy Bee")
                .withAddress(CLIENT_VALID_ADDRESS_BOB)
                .withTags("tag1", "tag2").build();
        assertTrue(AMY_CLIENT.isSameClient(editedAmy));

        // different name, all other attributes same -> returns false
        editedAmy = new ClientBuilder().withName(CLIENT_VALID_NAME_BOB).build();
        assertFalse(AMY_CLIENT.isSameClient(editedAmy));

        // name differs in case, all other attributes same -> returns false
        Client editedBob = new ClientBuilder().withName(CLIENT_VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB_CLIENT.isSameClient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CLIENT_VALID_NAME_BOB + " ";
        editedBob = new ClientBuilder(BOB_CLIENT).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB_CLIENT.isSameClient(editedBob));
    }

}
