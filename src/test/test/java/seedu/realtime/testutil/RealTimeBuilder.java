package seedu.realtime.testutil;

import seedu.realtime.model.RealTime;
import seedu.realtime.model.person.Person;

/**
 * A utility class to help with building RealTime objects.
 * Example usage: <br>
 *     {@code RealTime rt = new RealTimeBuilder().withPerson("John", "Doe").build();}
 */
public class RealTimeBuilder {

    private RealTime realTime;

    public RealTimeBuilder() {
        RealTime = new realTime();
    }

    public RealTimeBuilder(RealTime realTime) {
        this.realTime = realTime;
    }

    /**
     * Adds a new {@code Person} to the {@code RealTime} that we are building.
     */
    public RealTimeBuilder withPerson(Person person) {
        realTime.addPerson(person);
        return this;
    }

    public RealTime build() {
        return realTime;
    }
}
