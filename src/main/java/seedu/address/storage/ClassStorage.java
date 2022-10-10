package seedu.address.storage;

import javafx.util.Pair;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassStorage {

    public static HashMap<LocalDate, List<Pair>> classes = new HashMap<LocalDate, List<Pair>>();

    /**
     * Saves added classes into storage if there is no conflict between the timings of the classes.
     *
     * @param date LocalDate object.
     * @param start LocalTime object.
     * @param end LocalTime object.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void saveClass(LocalDate date, LocalTime start, LocalTime end) throws ParseException {
        if (!classes.containsKey(date)) {
            List<Pair> ls = new ArrayList<>();
            ls.add(new Pair(start, end));
            classes.put(date, ls);
        } else {
            List<Pair> listOfTimings = classes.get(date);
            for(int i = 0; i < listOfTimings.size(); i++) {
                Pair currClass = listOfTimings.get(i);
                LocalTime startOfCurrClass = (LocalTime) currClass.getKey();
                LocalTime endOfCurrClass = (LocalTime) currClass.getValue();
                if (start.equals(startOfCurrClass) || end.equals(endOfCurrClass) ||
                        start.isAfter(startOfCurrClass) && start.isBefore(endOfCurrClass) ||
                        start.isBefore(startOfCurrClass) && end.isAfter(startOfCurrClass)) {
                    throw new ParseException(EditCommand.MESSAGE_CLASS_CONFLICT);
                }
            }
            listOfTimings.add(new Pair(start, end));
        }
    }
}
