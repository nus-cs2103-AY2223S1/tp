package gim.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import gim.model.AddressBook;
import gim.model.ReadOnlyAddressBook;
import gim.model.exercise.Address;
import gim.model.exercise.Email;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Alex Yeoh"), new Weight("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Exercise(new Name("Bernice Yu"), new Weight("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Exercise(new Name("Charlotte Oliveiro"), new Weight("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Exercise(new Name("David Li"), new Weight("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Exercise(new Name("Irfan Ibrahim"), new Weight("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Exercise(new Name("Roy Balakrishnan"), new Weight("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Exercise sampleExercise : getSampleExercises()) {
            sampleAb.addExercise(sampleExercise);
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
