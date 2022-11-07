package seedu.condonery.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PropertyDirectory} with sample data.
 */
public class SampleDataUtil {
    public static Property[] getSampleProperties() {
        return new Property[] {
            new Property(new Name("PINNACLE@DUXTON"), new Address("Cantonment Rd, #1G, S085301"),
                    new Price("1000000"), getTagSet("High-End", "Available"),
                    getClientSet(
                        new Client(new Name("Bobby"),
                                new Address("2 Pasir Ris Link, #09-01, 518184"),
                                getTagSet("Friend")),
                        new Client(new Name("Samuel"),
                                new Address("127-149 Pasir Ris Grove, #04-01, 518133"),
                                getTagSet("Friend"))),
                    PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE),
            new Property(new Name("INTERLACE"), new Address("180 Depot Road, #08-01, S109684"),
                    new Price("1000000"),
                    getTagSet("High-End", "Sold"),
                    getClientSet(
                            new Client(new Name("Casey"),
                                    new Address("23 Pasir Ris Link, Watercolours, #03-02, 518169"),
                                    getTagSet("Rich"))),
                    PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE),
            new Property(new Name("D'LEEDON"), new Address("7 Leedon Heights, D'leedon, #05-01, S267953"),
                    new Price("1000000"),
                    getTagSet("High-End", "Reserved"),
                    getClientSet(
                            new Client(new Name("Jacob"),
                                    new Address("80-106 Elias Rd, #09-01, 519948"),
                                    getTagSet("Urgent")),
                            new Client(new Name("Jack"),
                                    new Address("127-149 Pasir Ris Grove, #02-01, 518133"),
                                    getTagSet("Friend"))),
                PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE)
        };
    }

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Dennis Tan"), new Address("Wall St, #1G, S085301"),
                    getTagSet("Urgent", "Colleague")),
            new Client(new Name("Jeremy Tan"), new Address("11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450"),
                    getTagSet("Urgent", "Friend")),
            new Client(new Name("Walter Wong"), new Address("7 Leedon Heights, D'leedon, S267953"),
                    getTagSet("Friend", "Colleague")),
            new Client(new Name("Jacob"), new Address("80-106 Elias Rd, #09-01, 519948"),
                    getTagSet("Urgent")),
            new Client(new Name("Jack"), new Address("127-149 Pasir Ris Grove, #02-01, 518133"),
                    getTagSet("Friend")),
            new Client(new Name("Casey"), new Address("23 Pasir Ris Link, Watercolours, #03-02, 518169"),
                    getTagSet("Rich")),
            new Client(new Name("Bobby"), new Address("2 Pasir Ris Link, #09-01, 518184"), getTagSet("Rich")),
            new Client(new Name("Samuel"), new Address("127-149 Pasir Ris Grove, #04-01, 518133"),
                    getTagSet("Friend"))
        };
    }

    public static ReadOnlyPropertyDirectory getSamplePropertyDirectory() {
        PropertyDirectory sampleAb = new PropertyDirectory();
        for (Property sampleProperty : getSampleProperties()) {
            sampleAb.addProperty(sampleProperty);
        }
        return sampleAb;
    }

    public static ReadOnlyClientDirectory getSampleClientDirectory() {
        ClientDirectory sampleCd = new ClientDirectory();
        for (Client sampleClient : getSampleClients()) {
            sampleCd.addClient(sampleClient);
        }
        return sampleCd;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a Client set containing the list of clients given.
     */
    public static Set<Client> getClientSet(Client... clients) {
        return Arrays.stream(clients)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a Property set containing the list of properties given.
     */
    public static Set<Property> getPropertySet(Property... properties) {
        return Arrays.stream(properties)
            .collect(Collectors.toSet());
    }

}
