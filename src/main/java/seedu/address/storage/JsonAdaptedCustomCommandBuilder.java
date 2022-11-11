package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CustomCommandBuilder;
import seedu.address.logic.parser.AddressBookParser;

/**
 * Jackson-friendly version of {@link CustomCommandBuilder}.
 */
public class JsonAdaptedCustomCommandBuilder {

    private final String commandMacroName;
    private final String commandMacroReplace;

    /**
     * Constructs a {@code JsonAdaptedCustomCommandBuilder} with the given customCommandBuilder details.
     */
    @JsonCreator
    public JsonAdaptedCustomCommandBuilder(@JsonProperty("commandMacroName") String commandMacroName,
            @JsonProperty("commandMacroReplace") String commandMacroReplace) {
        this.commandMacroName = commandMacroName;
        this.commandMacroReplace = commandMacroReplace;
    }

    /**
     * Converts a given {@code CustomCommandBuilder} into this class for Jackson use.
     */
    public JsonAdaptedCustomCommandBuilder(CustomCommandBuilder source) {
        requireNonNull(source);
        commandMacroName = source.getRepr();
        commandMacroReplace = source.getCommandData();
    }

    /**
     * Converts this Jackson-friendly adapted CustomCommandBuilder object into the model's
     * {@code CustomCommandBuilder} object.
     */
    public CustomCommandBuilder toModelType() throws IllegalValueException {
        if (!AddressBookParser.isValidName(commandMacroName)
                || Stream.of(commandMacroName, commandMacroReplace).anyMatch(Objects::isNull)
                || commandMacroReplace.isBlank()) {
            throw new IllegalValueException(CustomCommandBuilder.MESSAGE_CONSTRAINTS);
        }
        return new CustomCommandBuilder(commandMacroName, commandMacroReplace);
    }
}
