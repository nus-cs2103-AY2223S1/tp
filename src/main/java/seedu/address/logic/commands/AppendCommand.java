package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Appends surveys or tags to an existing person in Survin.
 */
public class AppendCommand extends Command {

    public static final String COMMAND_WORD = "append";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends surveys or tags to the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_SURVEY + "SURVEY]... "
            + "[" + PREFIX_TAG + "TAG]...\n" + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SURVEY + "Food Survey "
            + PREFIX_SURVEY + "Environment Survey " + PREFIX_TAG + "friend " + PREFIX_TAG + "student";

    public static final String MESSAGE_APPEND_SUCCESS = "Appended Person: %1$s";

    public static final String MESSAGE_NOT_APPENDED = "At least one field to append must be provided.";

    public static final String MESSAGE_SURVEY_FOUND = "Person already has one of the surveys that you are appending.";

    public static final String MESSAGE_TAG_FOUND = "Person already has one of the tags that you are appending.";

    private final Index index;
    private final Set<Survey> newSurveys;
    private final Set<Tag> newTags;

    /**
     * @param index of the person in the filtered person to append surveys or tags to
     * @param newSurveys set of new surveys to be appended
     * @param newTags set of new tags to be appended
     */
    public AppendCommand(Index index, Set<Survey> newSurveys, Set<Tag> newTags) {
        requireNonNull(index);
        requireNonNull(newSurveys);
        requireNonNull(newTags);

        this.index = index;
        this.newSurveys = newSurveys;
        this.newTags = newTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAppend = lastShownList.get(index.getZeroBased());
        Person appendedPerson = createAppendedPerson(personToAppend);

        model.setPerson(personToAppend, appendedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_APPEND_SUCCESS, appendedPerson));
    }

    private Person createAppendedPerson(Person personToAppend) throws CommandException {
        assert personToAppend != null;

        Set<Survey> oldSurveys = personToAppend.getSurveys();
        if (doesOldSurveysContainNewSurveys(oldSurveys, newSurveys)) {
            throw new CommandException(MESSAGE_SURVEY_FOUND);
        }
        Set<Survey> surveys = new HashSet<Survey>();
        surveys.addAll(oldSurveys);
        surveys.addAll(newSurveys);

        Set<Tag> oldTags = personToAppend.getTags();
        if (doesOldTagsContainNewTags(oldTags, newTags)) {
            throw new CommandException(MESSAGE_TAG_FOUND);
        }
        Set<Tag> tags = new HashSet<Tag>();
        tags.addAll(oldTags);
        tags.addAll(newTags);

        return new Person(personToAppend.getName(), personToAppend.getPhone(), personToAppend.getEmail(),
                personToAppend.getAddress(), personToAppend.getGender(), personToAppend.getBirthdate(),
                personToAppend.getRace(), personToAppend.getReligion(),
                surveys, tags);
    }

    private boolean doesOldSurveysContainNewSurveys(Set<Survey> oldSurveys, Set<Survey> newSurveys) {
        for (Survey oldSurvey : oldSurveys) {
            for (Survey newSurvey : newSurveys) {
                if (oldSurvey.survey.equals(newSurvey.survey)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doesOldTagsContainNewTags(Set<Tag> oldTags, Set<Tag> newTags) {
        for (Tag oldTag : oldTags) {
            for (Tag newTag : newTags) {
                if (oldTag.tagName.equals(newTag.tagName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
