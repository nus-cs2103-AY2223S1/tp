package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.JeeqTracker;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {

    public static final Client ALICE = new CompanyBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").build();
    public static final Client BENSON = new CompanyBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").withAddedPoc(TypicalPoc.BENSON).build();
    public static final Client CARL = new CompanyBuilder().withName("Carl Kurz").withAddress("wall street").build();
    public static final Client DANIEL = new CompanyBuilder().withName("Daniel Meier")
            .withAddress("10th street").withTags("friends").withAddedPoc(TypicalPoc.DANIEL).build();
    public static final Client ELLE = new CompanyBuilder().withName("Elle Meyer")
            .withAddress("michegan ave").build();
    public static final Client FIONA = new CompanyBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo").build();
    public static final Client GEORGE = new CompanyBuilder().withName("George Best")
            .withAddress("4th street").build();

    // Manually added
    public static final Client HOON = new CompanyBuilder().withName("Hoon Meier")
            .withAddress("little india").build();
    public static final Client IDA = new CompanyBuilder().withName("Ida Mueller")
            .withAddress("chicago ave").build();

    // Manually added - Company's details found in {@code CommandTestUtil}
    public static final Client AMY = new CompanyBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new CompanyBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code JeeqTracker} with all the typical companies.
     */
    public static JeeqTracker getTypicalJeeqTracker() {
        JeeqTracker jq = new JeeqTracker();
        for (Client client : getTypicalCompanies()) {
            jq.addCompany(client);
        }
        return jq;
    }

    public static List<Client> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
