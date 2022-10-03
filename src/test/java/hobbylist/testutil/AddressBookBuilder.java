package hobbylist.testutil;

import hobbylist.model.HobbyList;
import hobbylist.model.activity.Activity;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private HobbyList hobbyList;

    public AddressBookBuilder() {
        hobbyList = new HobbyList();
    }

    public AddressBookBuilder(HobbyList hobbyList) {
        this.hobbyList = hobbyList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Activity activity) {
        hobbyList.addPerson(activity);
        return this;
    }

    public HobbyList build() {
        return hobbyList;
    }
}
