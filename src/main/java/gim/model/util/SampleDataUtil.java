package gim.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import gim.model.AddressBook;
import gim.model.ReadOnlyAddressBook;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Rep;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Date;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Alex Yeoh"), new Weight("87438807"), new Sets("1"),
                new Rep("1"),
                getDateSet("01/10/2022")),
            new Exercise(new Name("Bernice Yu"), new Weight("99272758"), new Sets("2"),
                new Rep("2"),
                getDateSet("colleagues", "friends")),
            new Exercise(new Name("Charlotte Oliveiro"), new Weight("93210283"), new Sets("3"),
                new Rep("3"),
                getDateSet("02/10/2022")),
            new Exercise(new Name("David Li"), new Weight("91031282"), new Sets("4"),
                new Rep("4"),
                getDateSet("family")),
            new Exercise(new Name("Irfan Ibrahim"), new Weight("92492021"), new Sets("5"),
                new Rep("5"),
                getDateSet("03/10/2022")),
            new Exercise(new Name("Roy Balakrishnan"), new Weight("92624417"), new Sets("6"),
                new Rep("1"),
                getDateSet("04/10/2022"))
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
    public static Set<Date> getDateSet(String... strings) {
        return Arrays.stream(strings)
                .map(Date::new)
                .collect(Collectors.toSet());
    }

}
