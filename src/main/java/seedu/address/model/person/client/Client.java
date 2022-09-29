package seedu.address.model.person.client;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: name is present and not null, field value is validated, immutable.
 */
public class Client extends Person {

    /**
     * Constructs a client with name given by user and the rest of the fields set to default values.
     * @param name String representing name of the client
     */
    public Client(String name) {
        super(new Name(name), new Phone("000"), new Email("notSpecified@gmail.com"), new Address("notSpecified"),
                Set.of(new Tag("notSpecified")));
    }

    /**
     * Constructs a client with name, phone number given by user and the rest of the fields set to default values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     */
    public Client(String name, String phone) {
        super(new Name(name), new Phone(phone), new Email("notSpecified@gmail.com"), new Address("notSpecified"),
                Set.of(new Tag("notSpecified")));
    }

    /**
     * Constructs a client with name, phone number, email given by user and the rest of the fields set to default
     * values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     */
    public Client(String name, String phone, String email) {
        super(new Name(name), new Phone(phone), new Email(email), new Address("notSpecified"), Set.of(new Tag(
                "notSpecified")));
    }

    /**
     * Constructs a client with name, phone number, email, address given by user and the rest of the fields set to
     * default values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     * @param address String representing location of the client
     */
    public Client(String name, String phone, String email, String address) {
        super(new Name(name), new Phone(phone), new Email(email), new Address(address),
                Set.of(new Tag("notSpecified")));
    }

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     * @param address String representing location of the client
     * @param tag String representing the tag associated with the client
     */
    public Client(String name, String phone, String email, String address, String tag) {
        super(new Name(name), new Phone(phone), new Email(email), new Address(address), Set.of(new Tag(tag)));
    }
}
