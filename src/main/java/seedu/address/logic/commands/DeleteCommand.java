package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list"
            + " OR delete all persons satisfying the specified attributes.\n"
            + "Parameters: INDEX (must be a positive integer) OR [ra/RACE] [re/RELIGION] [s/NAME OF SURVEY]\n"
            + "Example: " + COMMAND_WORD + " 1" + " OR " + COMMAND_WORD + " ra/Chinese re/Christian";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Optional<Index> targetIndex;
    private final Optional<Race> race;
    private final Optional<Religion> religion;
    private final Optional<Survey> survey;

    /**
     * Initialises a new DeleteCommand
     *
     * @param targetIndex
     * @param race
     * @param religion
     * @param survey
     */
    public DeleteCommand(Optional<Index> targetIndex, Optional<Race> race, Optional<Religion> religion,
            Optional<Survey> survey) {
        this.targetIndex = targetIndex;
        this.race = race;
        this.religion = religion;
        this.survey = survey;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.isPresent()) {
            if (targetIndex.get().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.get().getZeroBased());
            model.deletePerson(personToDelete);
            model.commitAddressBook();
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        }

        Predicate<Person> predicate = x -> x.getRace().equals(race.orElse(x.getRace()))
                && x.getReligion().equals(religion.orElse(x.getReligion()))
                && x.getSurveys().contains(survey.orElse(x.getSurveys().iterator().next()));

        model.updateFilteredPersonList(predicate);
        List<Person> personList = new ArrayList<>();
        if (model.getFilteredPersonList().isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        personList.addAll(model.getFilteredPersonList());

        for (Person p : personList) {
            if (survey.isPresent() && p.hasMultipleSurveys()) {
                Set<Survey> surveys = p.getSurveys();
                Set<Survey> editedSurvey = new HashSet<>();
                editedSurvey.addAll(surveys);
                editedSurvey.remove(survey.orElseThrow());

                Person editedPerson = new Person(p.getName(), p.getPhone(), p.getEmail(), p.getAddress(), p.getGender(),
                        p.getBirthdate(), p.getRace(), p.getReligion(), editedSurvey, p.getTags());
                model.setPerson(p, editedPerson);
            } else {
                model.deletePerson(p);
            }
        }

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personList.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        return targetIndex.equals(otherDeleteCommand.targetIndex) && race.equals(otherDeleteCommand.race)
                && religion.equals(otherDeleteCommand.religion) && survey.equals(otherDeleteCommand.survey);
    }
}
