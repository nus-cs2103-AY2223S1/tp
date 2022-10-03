package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Profile[] getSampleProfiles() {
        return new Profile[] {
            new Profile(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com")),
            new Profile(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com")),
            new Profile(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com")),
            new Profile(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com")),
            new Profile(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com")),
            new Profile(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Profile sampleProfile : getSampleProfiles()) {
            sampleAb.addProfile(sampleProfile);
        }
        return sampleAb;
    }

}
