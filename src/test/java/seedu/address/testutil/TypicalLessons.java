package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.module.Lesson;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalLessons {

    public static final Lesson CS2103T_LECTURE = new LessonBuilder()
            .withType("lec")
            .withModule("CS2103T")
            .withDay("4")
            .withStartTime("14:00")
            .withEndTime("15:00")
            .build();
    public static final Lesson CS2100_RECITATION = new LessonBuilder()
            .withType("rec")
            .withModule("CS2100")
            .withDay("2")
            .withStartTime("16:00")
            .withEndTime("18:00")
            .build();
    public static final Lesson CS2109S_TUTORIAL = new LessonBuilder()
            .withType("tut")
            .withModule("CS2109S")
            .withDay("3")
            .withStartTime("17:00")
            .withEndTime("18:00")
            .build();
    public static final Lesson CS2040S_LAB = new LessonBuilder()
            .withType("lab")
            .withModule("CS2040S")
            .withDay("1")
            .withStartTime("12:00")
            .withEndTime("14:00")
            .build();

    /**
     * Returns an {@code AddressBook} with all the typical lessons.
     */
    public static AddressBook getTypicalAddressBook() throws CommandException {
        AddressBook ab = new AddressBook();
        ab.addLessonToUser(CS2103T_LECTURE);
        for (Lesson lesson : getTypicalLessons()) {
            ab.addLessonToUser(lesson);
        }
        return ab;
    }

    public static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(CS2100_RECITATION, CS2109S_TUTORIAL, CS2040S_LAB));
    }

}
