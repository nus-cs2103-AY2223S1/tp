package seedu.address.commons.util;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Class to include some functional interfaces for general use
 */
public final class FunctionalInterfaces {
    /**
     * Interface for extracting index from model
     */
    @FunctionalInterface
    public interface Getter<U> {
        U apply(Model model, Index index) throws CommandException;
    }

    /**
     * Interface for modifying item from model
     */
    @FunctionalInterface
    public interface Changer<U> {
        void apply(Model model, U item) throws CommandException;
    }

    /**
     * Interface for retrieving item from model
     */
    @FunctionalInterface
    public interface Retriever<U> {
        U apply(Model model) throws CommandException;
    }
}
