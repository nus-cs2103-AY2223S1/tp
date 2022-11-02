package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.AddressBookParser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Jackson-friendly version of {@link AddressBookParser}.
 */
public class JsonAdaptedAddressBookParser {

    private final Map<String, String> alias = new HashMap<>();
    private final Map<String, JsonAdaptedCustomCommandBuilder> macros = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedAddressBookParser} with the given addressBookParser details.
     */
    @JsonCreator
    public JsonAdaptedAddressBookParser(@JsonProperty("alias") Map<String, String> alias,
            @JsonProperty("macros") Map<String, JsonAdaptedCustomCommandBuilder> macros) {
        requireAllNonNull(alias, macros);
        this.alias.putAll(alias);
        this.macros.putAll(macros);
    }

    /**
     * Converts a given {@code CustomCommandBuilder} into this class for Jackson use.
     */
    public JsonAdaptedAddressBookParser(AddressBookParser source) {
        requireNonNull(source);
        alias.putAll(source.getAliasMapper());
        source.getBonusMapper().forEach((macro, customCommand)
                -> macros.put(macro, new JsonAdaptedCustomCommandBuilder(customCommand)));
    }

    /**
     * Converts this Jackson-friendly adapted AddressBookParser object into the model's
     * {@code AddressBookParser} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted addressBookParser.
     */
    public void toModelType() throws IllegalValueException {
        AddressBookParser addressBookParser = AddressBookParser.get();
        assert addressBookParser != null;

        alias.forEach((beforeAlias, newAlias) -> addressBookParser.addAlias(beforeAlias, newAlias));
        macros.forEach((macro, customCommand) -> addressBookParser.addCommand(customCommand.toModelType()));
    }
}
