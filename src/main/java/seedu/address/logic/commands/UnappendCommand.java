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
 * Unappends surveys or tags from an existing person in Survin
 */
public class UnappendCommand extends Command {

    public static final String COMMAND_WORD = "unappend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unappends surveys or tags from the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_SURVEY + "SURVEY]... "
            + "[" + PREFIX_TAG + "TAG]...\n" + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SURVEY + "Food Survey "
            + PREFIX_SURVEY + "Environment Survey " + PREFIX_TAG + "friend " + PREFIX_TAG + "student";

    public static final String MESSAGE_UNAPPEND_SUCCESS = "Unappended Person: %1$s";
    public static final String MESSAGE_NOT_UNAPPENDED = "At least one field to unappend must be provided";
    public static final String MESSAGE_SURVEY_TAG_NOT_FOUND = "Unable to unappend any survey(s) or tag(s)"
            + " as they do not exist.";


    private final Index index;
    private final Set<Survey> surveysToRemove;
    private final Set<Tag> tagsToRemove;

    /**
     * @param index of the person in the filtered person to unappend surveys or tags from
     * @param surveysToRemove set of surveys to unappend
     * @param tagsToRemove set of tags to unappend
     */
    public UnappendCommand(Index index, Set<Survey> surveysToRemove, Set<Tag> tagsToRemove) {
        requireNonNull(index);
        requireNonNull(surveysToRemove);
        requireNonNull(tagsToRemove);

        this.index = index;
        this.surveysToRemove = surveysToRemove;
        this.tagsToRemove = tagsToRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnappend = lastShownList.get(index.getZeroBased());
        Person unappendedPerson = createUnappendedPerson(personToUnappend);

        if (personToUnappend.equals(unappendedPerson)) {
            throw new CommandException(MESSAGE_SURVEY_TAG_NOT_FOUND);
        }

        model.setPerson(personToUnappend, unappendedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitSurvin();
        return new CommandResult(String.format(MESSAGE_UNAPPEND_SUCCESS, unappendedPerson));
    }

    private Person createUnappendedPerson(Person personToUnappend) {
        assert personToUnappend != null;

        Set<Survey> surveys = removeSurveysFromSurveys(personToUnappend.getSurveys(), surveysToRemove);
        Set<Tag> tags = removeTagsFromTags(personToUnappend.getTags(), tagsToRemove);

        return new Person(personToUnappend.getName(), personToUnappend.getPhone(), personToUnappend.getEmail(),
                personToUnappend.getAddress(), personToUnappend.getGender(), personToUnappend.getBirthdate(),
                personToUnappend.getRace(), personToUnappend.getReligion(),
                surveys, tags);
    }

    private static Set<Survey> removeSurveysFromSurveys(Set<Survey> set, Set<Survey> setToExclude) {
        Set<Survey> returnSet = new HashSet<Survey>();
        for (Survey survey : set) {
            boolean isFound = false;
            for (Survey excludedSurvey : setToExclude) {
                if (survey.survey.equals(excludedSurvey.survey)) {
                    isFound = true;
                }
            }
            if (!isFound) {
                returnSet.add(survey);
            }
        }
        return returnSet;
    }

    private static Set<Tag> removeTagsFromTags(Set<Tag> set, Set<Tag> setToExclude) {
        Set<Tag> returnSet = new HashSet<Tag>();
        for (Tag tag : set) {
            boolean isFound = false;
            for (Tag excludedTag : setToExclude) {
                if (tag.tagName.equals(excludedTag.tagName)) {
                    isFound = true;
                }
            }
            if (!isFound) {
                returnSet.add(tag);
            }
        }
        return returnSet;
    }
}
