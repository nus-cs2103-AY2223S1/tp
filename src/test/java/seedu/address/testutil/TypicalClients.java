package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.JeeqTracker;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withClientPhone("87654321")
            .withClientEmail("amybee@gmail.com")
            .withTags("friends").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withClientPhone("12112121")
            .withClientEmail("ben@gmail.com")
            .withTags("owesMoney", "friends").withAddedCompany(TypicalCompany.BENSON).build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withAddress("wall street")
            .withClientPhone("9818112")
            .withClientEmail("carl@gmail.com").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier")
            .withAddress("10th street")
            .withClientPhone("12121212")
            .withClientEmail("dan@gmail.com")
            .withTags("friends").withAddedCompany(TypicalCompany.DANIEL).build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer")
            .withAddress("michegan ave")
            .withClientPhone("9228282")
            .withClientEmail("eellee@gmail.com").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo")
            .withClientPhone("12121212")
            .withClientEmail("fiona@gmail.com").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best")
            .withAddress("4th street")
            .withClientPhone("9778282")
            .withClientEmail("george@gmail.com").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier")
            .withAddress("little india")
            .withClientPhone("9918282")
            .withClientEmail("hoon@gmail.com").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller")
            .withAddress("chicago ave")
            .withClientPhone("99228282")
            .withClientEmail("ida@gmail.com").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withClientPhone(VALID_PHONE_AMY)
            .withClientEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withClientPhone(VALID_PHONE_BOB)
            .withClientEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code JeeqTracker} with all the typical clients.
     */
    public static JeeqTracker getTypicalJeeqTracker() {
        JeeqTracker jq = new JeeqTracker();
        for (Client client : getTypicalClients()) {
            jq.addClient(client);
        }
        return jq;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
