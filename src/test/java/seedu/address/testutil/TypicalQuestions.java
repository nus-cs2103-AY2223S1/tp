package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.question.Question;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_Q1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_Q2;


/**
 * A utility class containing a list of {@code Question} objects to be used in tests.
 */
public class TypicalQuestions {

    // Manually added - Question's details found in {@code CommandTestUtil}
    public static final Question Q1 =
            new QuestionBuilder().withDescription(VALID_DESCRIPTION_Q1).withImportantTag(false).build();
    public static final Question Q2 =
            new QuestionBuilder().withDescription(VALID_DESCRIPTION_Q2).withImportantTag(false).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalQuestions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical questions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Question question : getTypicalQuestions()) {
            ab.addQuestion(question);
        }
        return ab;
    }

    public static List<Question> getTypicalQuestions() {
        return new ArrayList<>(Arrays.asList(Q1, Q2));
    }
}

