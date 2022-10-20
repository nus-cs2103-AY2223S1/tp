package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_GENDER, PREFIX_TAG, PREFIX_LOCATION, PREFIX_GITHUBUSERNAME, PREFIX_RATING, PREFIX_YEAR,
                        PREFIX_SPECIALISATION, PREFIX_OFFICEHOUR);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            editPersonDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap
                    .getValue(PREFIX_MODULE_CODE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editPersonDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUBUSERNAME).isPresent()) {
            String usernameInput = argMultimap.getValue(PREFIX_GITHUBUSERNAME).orElse(GithubUsername.DEFAULT_USERNAME);
            if (usernameInput.equals(GithubUsername.DEFAULT_USERNAME)) {
                editPersonDescriptor.setGithubUsername(ParserUtil.parseGitHubUsername(usernameInput, false));
            } else {
                editPersonDescriptor.setGithubUsername(ParserUtil.parseGitHubUsername(usernameInput, true));
            }
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            String ratingInput = argMultimap.getValue(PREFIX_RATING).orElse(Rating.EMPTY_RATING);
            if (ratingInput.equals(Rating.EMPTY_RATING)) {
                editPersonDescriptor.setRating(ParserUtil.parseRating(ratingInput, false));
            } else {
                editPersonDescriptor.setRating(ParserUtil.parseRating(ratingInput, true));
            }
        }

        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            String yearInput = argMultimap.getValue(PREFIX_YEAR).orElse(Year.EMPTY_YEAR);

            if (yearInput.equals(Year.EMPTY_YEAR)) {
                editPersonDescriptor.setYear(ParserUtil.parseYear(yearInput, false));
            } else {
                editPersonDescriptor.setYear(ParserUtil.parseYear(yearInput, true));
            }
        }

        if (argMultimap.getValue(PREFIX_SPECIALISATION).isPresent()) {
            String specialisationInput = argMultimap.getValue(PREFIX_SPECIALISATION)
                                                    .orElse(Specialisation.EMPTY_SPECIALISATION);
            if (specialisationInput.equals(Specialisation.EMPTY_SPECIALISATION)) {
                editPersonDescriptor.setSpecialisation(ParserUtil.parseSpecialisation(specialisationInput, false));
            } else {
                editPersonDescriptor.setSpecialisation(ParserUtil.parseSpecialisation(specialisationInput, true));
            }
        }

        if (argMultimap.getValue(PREFIX_OFFICEHOUR).isPresent()) {
            String officeHourInput = argMultimap.getValue(PREFIX_OFFICEHOUR)
                    .orElse(OfficeHour.EMPTY_OFFICE_HOUR);
            if (officeHourInput.equals(OfficeHour.EMPTY_OFFICE_HOUR)) {
                editPersonDescriptor.setOfficeHour(ParserUtil.parseOfficeHour(officeHourInput, false));
            } else {
                editPersonDescriptor.setOfficeHour(ParserUtil.parseOfficeHour(officeHourInput, true));
            }
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
