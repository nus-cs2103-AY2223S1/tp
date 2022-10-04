package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Calorie;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Food;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Food[] getSamplePersons() {
        return new Food[] {
            new Food(new Name("Alex Yeoh"), new Calorie(), getTagSet("friends")),
            new Food(new Name("Bernice Yu"), new Calorie(), getTagSet("colleagues", "friends")),
            new Food(new Name("Charlotte Oliveiro"), new Calorie(), getTagSet("neighbours")),
            new Food(new Name("David Li"), new Calorie(), getTagSet("family")),
            new Food(new Name("Irfan Ibrahim"), new Calorie(), getTagSet("classmates")),
            new Food(new Name("Roy Balakrishnan"), new Calorie(), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Food sampleFood : getSamplePersons()) {
            sampleAb.addPerson(sampleFood);
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
