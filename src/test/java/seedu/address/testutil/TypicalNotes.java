package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_CLUB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CLUB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Note} objects to be used in tests.
 */
public class TypicalNotes {

    public static final Note ACCOUNTING = new NoteBuilder().withTitle("Accounting")
            .withContent("Do accounting").withTags("a").build();

    public static final Note BUY = new NoteBuilder().withTitle("Buy")
            .withContent("Buy food").withTags("b").build();

    public static final Note CHARITY = new NoteBuilder().withTitle("Charity Event")
            .withContent("Charity event").withTags("c").build();

    public static final Note DONATE = new NoteBuilder().withTitle("Donate Event")
            .withContent("Donate food").withTags("d").build();

    public static final Note ELECTION = new NoteBuilder().withTitle("Election Event")
            .withContent("Elect new club president").withTags("e").build();

    // Manually added
    public static final Note FOOTBALL = new NoteBuilder().withTitle("Football")
            .withContent("Football practice").build();

    // Manually added - Note's details found in {@code CommandTestUtil}
    public static final Note MEETING = new NoteBuilder().withTitle(VALID_TITLE_MEETING)
            .withContent(VALID_CONTENT_MEETING).build();

    public static final Note CLUB = new NoteBuilder().withTitle(VALID_TITLE_CLUB)
            .withContent(VALID_CONTENT_CLUB).build();

    public static final String KEYWORD_MATCHING_EVENT = "Event"; // A keyword that matches EVENT

    private TypicalNotes() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical notes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Note note : getTypicalNotes()) {
            ab.addNote(note);
            for (Tag tag : note.getTags()) {
                ab.addTag(tag);
            }
        }
        return ab;
    }

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(ACCOUNTING, BUY, CHARITY, DONATE, ELECTION));
    }


}
