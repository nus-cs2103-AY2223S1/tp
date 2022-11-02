package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;
import seedu.address.model.remark.UniqueRemarkList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;

/**
 * Contains utility methods for populating {@code JeeqTracker} with sample data.
 */
public class SampleDataUtil {

    private static final Transaction[] SAMPLE_TRANSACTIONS =
        new Transaction[] {
            new BuyTransaction(new Goods("Apple Pencil"),
                    new Price("239"),
                    new Quantity("5"),
                    new Date("11/05/2022")),
            new BuyTransaction(new Goods("Apple Charger"),
                    new Price("1.55"),
                    new Quantity("2"),
                    new Date("29/05/2022")),
            new SellTransaction(new Goods("iPhone 14"),
                    new Price("2399"),
                    new Quantity("1"),
                    new Date("01/06/2022")),
            new SellTransaction(new Goods("Macbook"),
                    new Price("899"),
                    new Quantity("2"),
                    new Date("05/06/2022")),
            new SellTransaction(new Goods("Nalgene Bottle"),
                    new Price("25.86"),
                    new Quantity("12"),
                    new Date("11/07/2022")),
            new SellTransaction(new Goods("Airpods Pro"),
                    new Price("355"),
                    new Quantity("3"),
                    new Date("17/07/2022")),
            new BuyTransaction(new Goods("Spotify Premium"),
                    new Price("50"),
                    new Quantity("2"),
                    new Date("20/08/2022")),
            new BuyTransaction(new Goods("Apple Pie"),
                    new Price("2.76"),
                    new Quantity("22"),
                    new Date("22/08/2022")),
            new SellTransaction(new Goods("Logitech Speakers"),
                    new Price("200.10"),
                    new Quantity("1"),
                    new Date("01/09/2022"))
        };

    private static final TransactionLog[] SAMPLE_TRANSACTION_LOGS =
        new TransactionLog[] {
            new TransactionLog(new ArrayList<>(
                    Arrays.asList(SAMPLE_TRANSACTIONS[0], SAMPLE_TRANSACTIONS[1]))),
            new TransactionLog(new ArrayList<>(
                    Arrays.asList(SAMPLE_TRANSACTIONS[2], SAMPLE_TRANSACTIONS[3])
            )),
            new TransactionLog(new ArrayList<>(
                    Arrays.asList(SAMPLE_TRANSACTIONS[4], SAMPLE_TRANSACTIONS[5])
            )),
            new TransactionLog(new ArrayList<>(
                    Arrays.asList(SAMPLE_TRANSACTIONS[6], SAMPLE_TRANSACTIONS[7])
            )),
            new TransactionLog(new ArrayList<>(
                    List.of(SAMPLE_TRANSACTIONS[8])
            ))
        };

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new ClientPhone("8273281"),
                new ClientEmail("alex@gmail.com"),
                getTagSet("friends"),
                getSampleUniqueRemarkList("Good client, always on time!"),
                SAMPLE_TRANSACTION_LOGS[0]),
            new Client(new Name("Bernice Yu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new ClientPhone("112323323"),
                new ClientEmail("byue@gmail.com"),
                getTagSet("colleagues", "friends"),
                getSampleUniqueRemarkList("This client replies really quickly"),
                SAMPLE_TRANSACTION_LOGS[1]),
            new Client(new Name("Charlotte Oliveiro"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new ClientPhone("9818218"),
                new ClientEmail("char@gmail.com"),
                getTagSet("neighbours"),
                getSampleUniqueRemarkList("He ghosted me"),
                SAMPLE_TRANSACTION_LOGS[2]),
            new Client(new Name("David Li"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new ClientPhone("8217128"),
                new ClientEmail("dav@gmail.com"),
                getTagSet("family"),
                getSampleUniqueRemarkList("Very gullible client, easy to scam"),
                SAMPLE_TRANSACTION_LOGS[3]),
            new Client(new Name("Irfan Ibrahim"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new ClientPhone("11121212"),
                new ClientEmail("irib@gmail.com"),
                getTagSet("classmates"),
                getSampleUniqueRemarkList("A lot of good connections", "High quality goods"),
                SAMPLE_TRANSACTION_LOGS[4]),
        };
    }

    public static UniqueRemarkList getSampleUniqueRemarkList(String... remarksText) {
        UniqueRemarkList list = new UniqueRemarkList();
        for (String text : remarksText) {
            list.add(new Remark(new Text(text)));
        }

        return list;
    }

    public static ReadOnlyJeeqTracker getSampleJeeqTracker() {
        JeeqTracker sampleAb = new JeeqTracker();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
