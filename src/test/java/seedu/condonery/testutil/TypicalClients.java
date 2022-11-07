package seedu.condonery.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client AMY_CLIENT = new ClientBuilder().withName("Amy Bee")
            .withAddress("Block 312, Amy Street 1")
            .withTags("husband").build();

    public static final Client BOB_CLIENT = new ClientBuilder().withName("Bob")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").build();

    public static final Client CARL_CLIENT = new ClientBuilder().withName("Carl Kurz")
            .withAddress("wall street").build();
    public static final Client DANIEL_CLIENT = new ClientBuilder()
            .withName("Daniel Meier").withAddress("10th street").withTags("friends").build();
    public static final Client ELLE_CLIENT = new ClientBuilder().withName("Elle Meyer")
            .withAddress("michegan ave").build();
    public static final Client FIONA_CLIENT = new ClientBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo").build();
    public static final Client GEORGE_CLIENT = new ClientBuilder().withName("George Best")
            .withAddress("4th street").build();
    public static final Set<Tag> TYPICAL_CLIENT_TAG = new HashSet<Tag>() { {
            add(new Tag("tag1"));
            add(new Tag("tag2"));
            add(new Tag("tag3"));
        }};


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
        return new ArrayList<>(Arrays.asList(AMY_CLIENT, BOB_CLIENT, CARL_CLIENT, DANIEL_CLIENT,
                ELLE_CLIENT, FIONA_CLIENT, GEORGE_CLIENT));
    }
}
