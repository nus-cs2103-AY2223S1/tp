package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the acceptable command words of a command.
 */
public class CommandWord {
    /* The main command word shown in command examples */
    private String mainCommandWord;
    /* The other acceptable command words */
    private ArrayList<String> alternativeCommandWords;

    /**
     * Constructs the object with given fields.
     * @param mainCommandWord The main command word shown in command examples.
     * @param alternativeCommandWords The other acceptable command words.
     */
    public CommandWord(String mainCommandWord, String ... alternativeCommandWords) {
        requireNonNull(mainCommandWord);
        this.mainCommandWord = mainCommandWord;
        this.alternativeCommandWords = new ArrayList<>(List.of(alternativeCommandWords));
    }

    /**
     * Returns whether the given string matches the command words.
     * @param input The given string.
     * @return The boolean indicating whether the given string matches the command words.
     */
    public boolean matches(String input) {

        return mainCommandWord.toLowerCase().equals(input.toLowerCase())
                || alternativeCommandWords.stream()
                .map(w -> w.toLowerCase())
                .anyMatch(w -> w.equals(input.toLowerCase()));
    }

    /**
     * Returns the main command word.
     * @return The main command word.
     */
    @Override
    public String toString() {
        return this.mainCommandWord;
    }
}
