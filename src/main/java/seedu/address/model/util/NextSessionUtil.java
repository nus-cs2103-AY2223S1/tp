package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.person.Person;
import seedu.address.model.person.Session;

/**
 * Utility class containing helper methods to help with nextSessionFeedback method.
 */
public class NextSessionUtil {

    private final Session timeNowSession;
    private final ArrayList<Session> toSortList;
    private final HashMap<Session, Person> sessionPersonHashMap;

    /**
     * Class implementing Consumer acting as a helper to add Sessions later
     * than current time in the week to the list to be sorted.
     */
    private Consumer<Person> toSortListAdder = new Consumer<Person>() {
        @Override
        public void accept(Person person) {
            for (int i = 0; i < person.getSessionList().sessionList.size(); i++) {
                Session currSession = person.getSessionList().sessionList.get(i);
                toSortList.add(currSession);
                sessionPersonHashMap.put(currSession, person);
            }
        }
    };

    /**
     * Constructs an {@code NextSessionUtil}.
     */
    public NextSessionUtil() {
        timeNowSession = getTimeNowAsSession();
        toSortList = new ArrayList<>();
        sessionPersonHashMap = new HashMap<>();
    }

    /**
     * Helper method to get the system time now as a Session for constructor to help constructor.
     * @return the system time now as a Session.
     */
    private static Session getTimeNowAsSession() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE HH:mm").withResolverStyle(ResolverStyle.STRICT);
        String timeNow = LocalDateTime.now().format(dtf);
        return new Session(timeNow);
    }

    public Consumer<Person> getToSortListAdder() {
        return toSortListAdder;
    }

    public ArrayList<Session> getToSortList() {
        return toSortList;
    }

    /**
     * Returns the feedback for the next closest Session to the current time to ModelManager class.
     * If no Session left this week, return the coming week's closest Session time.
     * @return a String feedback to be displayed to user.
     */
    public String nextSessionFeedback() {
        String feedback = "Next Session: ";
        toSortList.sort(Session::compareTo);
        Predicate<Session> laterThanNowCounter = s -> timeNowSession.compareTo(s) <= 0;
        Stream<Session> toSortListAsStream = toSortList.stream().filter(laterThanNowCounter);
        List<Session> filteredSortedList = toSortListAsStream.collect(Collectors.toList());
        if (filteredSortedList.size() == 0) { //All sessions in the week is finished, first Session of new week.
            return feedback + sessionPersonHashMap.get(toSortList.get(0)).getName()
                    + " " + toSortList.get(0).toString();
        } else { //Session in the week that is not done yet.
            return feedback + sessionPersonHashMap.get(filteredSortedList.get(0)).getName()
                    + " " + filteredSortedList.get(0).toString();
        }
    }
}
