package seedu.address.testutil;

import seedu.address.model.question.Description;
import seedu.address.model.question.ImportantTag;
import seedu.address.model.question.Question;

/**
 * A utility class to help with building Question objects.
 */
public class QuestionBuilder {

    public static final String DEFAULT_DESCRIPTION = "What is the purpose of the UML diagram?";
    public static final boolean DEFAULT_IMPORTANT_TAG = false;


    private Description description;
    private ImportantTag importantTag;


    /**
     * Creates a {@code QuestionBuilder} with the default details.
     */
    public QuestionBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        importantTag = new ImportantTag(DEFAULT_IMPORTANT_TAG);
    }

    /**
     * Initializes the QuestionBuilder with the data of {@code questionToCopy}.
     */
    public QuestionBuilder(Question questionToCopy) {
        description = questionToCopy.getDescription();
        importantTag = questionToCopy.getImportantTag();
    }

    /**
     * Sets the {@code Description} of the {@code Question} that we are building.
     */
    public QuestionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Important Tag} of the {@code Question} that we are building.
     */
    public QuestionBuilder withImportantTag(boolean bool) {
        this.importantTag = new ImportantTag(bool);
        return this;
    }

    public Question build() {
        return new Question(description, importantTag);
    }

}
