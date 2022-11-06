package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tutorial.Tutorial;



/**
 * A utility class containing a list of {@code Tutorial} objects to be used in tests
 */
public class TypicalTutorials {

    public static final Tutorial TUTORIAL1 = new TutorialBuilder().withContent("UML Diagrams")
            .withGroup("T08").withTime("2022-10-01 0800")
            .withStatus(false).build();

    public static final Tutorial TUTORIAL2 = new TutorialBuilder().withContent("Developer Guide")
            .withGroup("T09").withTime("2022-10-02 0900")
            .withStatus(false).build();

    public static final Tutorial TUTORIAL3 = new TutorialBuilder().withContent("Defensive Coding")
            .withGroup("T10").withTime("2022-11-02 1300")
            .withStatus(true).build();

    public static final Tutorial TUTORIAL4 = new TutorialBuilder().withContent("Coding Standards")
            .withGroup("T11").withTime("2022-10-02 0730")
            .withStatus(true).build();

    public static final Tutorial TUTORIAL5 = new TutorialBuilder().withContent("Design Considerations")
            .withGroup("T12").withTime("2022-09-04 0900")
            .withStatus(false).build();

    public static final Tutorial TUTORIAL6 = new TutorialBuilder().withContent("Developer Guide")
            .withGroup("T09").withTime("2022-10-09 0900")
            .withStatus(true).build();

    // Can add more tutorials

    private TypicalTutorials() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tutorials.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tutorial tutorial : getTypicalTutorials()) {
            ab.addTutorial(tutorial);
        }
        return ab;
    }

    public static List<Tutorial> getTypicalTutorials() {
        return new ArrayList<>(Arrays.asList(TUTORIAL1, TUTORIAL2, TUTORIAL3, TUTORIAL4, TUTORIAL5, TUTORIAL6));
    }



}
