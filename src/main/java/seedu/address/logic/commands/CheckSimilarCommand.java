package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose are similar.
 */
public class CheckSimilarCommand extends Command {

    public static final String COMMAND_WORD = "checkSimilar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns persons that maybe similar";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Person> personList = model.getFilteredPersonList();
        for (int i = 0; i < personList.size(); i++) {
            Person curr = personList.get(i);
            Optional<Person> similarPerson = personList.stream()
                    .filter(x -> x.isSimilarPerson(curr) && !x.isSamePerson(curr)).findAny();
            if (similarPerson.isPresent()) {
                model.updateFilteredPersonList(x -> x.isSamePerson(curr) || x.isSamePerson(similarPerson.get()));
                return new CommandResult(
                        String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
            }
        }
        return new CommandResult(
                String.format("No similar people found!"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
