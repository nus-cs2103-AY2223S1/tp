package soconnect.testutil;

import soconnect.model.SoConnect;
import soconnect.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code SoConnect ab = new SoConnectBuilder().withPerson("John", "Doe").build();}
 */
public class SoConnectBuilder {

    private SoConnect soConnect;

    public SoConnectBuilder() {
        soConnect = new SoConnect();
    }

    public SoConnectBuilder(SoConnect soConnect) {
        this.soConnect = soConnect;
    }

    /**
     * Adds a new {@code Person} to the {@code SoConnect} that we are building.
     */
    public SoConnectBuilder withPerson(Person person) {
        soConnect.addPerson(person);
        return this;
    }

    public SoConnect build() {
        return soConnect;
    }
}
