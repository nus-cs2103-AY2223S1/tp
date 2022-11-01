package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.ModelType;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** The model type should show in the main screen. */
    private final ModelType modelType;

    /**
     * Constructs a {@code CommandResult} with the specified model fields.
     */
    public CommandResult(String feedbackToUser, ModelType modelType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.modelType = requireNonNull(modelType);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, ModelType.DEFAULT);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public ModelType getModelType() {
        return modelType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && modelType.equals(otherCommandResult.getModelType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, modelType);
    }

}
