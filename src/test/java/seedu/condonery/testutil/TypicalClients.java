package seedu.condonery.testutil;

import static seedu.condonery.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.client.Client;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Pinnacle@Duxton")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").build();
    public static final Client BOB = new ClientBuilder().withName("Oasis")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withAddress("wall street").build();
    public static final Client DANIEL = new ClientBuilder()
            .withName("Daniel Meier").withAddress("10th street").withTags("friends").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer")
            .withAddress("michegan ave").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best")
            .withAddress("4th street").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {
    } // prevents instantiation

    /**
     * Returns an {@code PropertyDirectory} with all the typical persons.
     */
    public static ClientDirectory getTypicalClientDirectory() {
        ClientDirectory cd = new ClientDirectory();
        for (Client property: getTypicalClients()) {
            cd.addClient(property);
        }
        return cd;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BOB, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
