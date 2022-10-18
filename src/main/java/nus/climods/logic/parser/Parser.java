package nus.climods.logic.parser;

import nus.climods.logic.commands.Command;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
