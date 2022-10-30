package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MyInsuRec;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {
    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withProducts("Product1")
            .build();

    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withProducts("Product1", "Product2").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
           .build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withProducts("Product2").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("94822241")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("94824421")
            .withEmail("anna@example.com").withAddress("4th street").build();
    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("84824241")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withProducts(VALID_PRODUCT_1).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withProducts(VALID_PRODUCT_2, VALID_PRODUCT_1)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private static final Product PRODUCT_1 = new Product("Product1");
    private static final Product PRODUCT_2 = new Product("Product2");


    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code MyInsuRec} with all the typical clients. This includes their associated products.
     */
    public static MyInsuRec getTypicalMyInsuRec() {
        MyInsuRec myInsuRec = new MyInsuRec();
        for (Client client : getTypicalClients()) {
            myInsuRec.addClient(client);
        }
        for (Product product : getTypicalProducts()) {
            myInsuRec.addProduct(product);
        }
        return myInsuRec;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Product> getTypicalProducts() {
        return new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2));
    }
}
