package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Profile[] getSampleProfiles() {
        return new Profile[] {
            new Profile(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Telegram("lexyeoh"), getTagSet("friends")),
            new Profile(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                Telegram.EMPTY_TELEGRAM, getTagSet("colleagues", "friends")),
            new Profile(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Telegram("charlotte_ol1"), getTagSet("neighbours")),
            new Profile(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                Telegram.EMPTY_TELEGRAM, getTagSet("family")),
            new Profile(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                Telegram.EMPTY_TELEGRAM, getTagSet("classmates")),
            new Profile(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                Telegram.EMPTY_TELEGRAM, getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Profile sampleProfile : getSampleProfiles()) {
            sampleAb.addProfile(sampleProfile);
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
