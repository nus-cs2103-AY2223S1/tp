package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import seedu.address.model.person.Person;
import seedu.address.model.person.Session;

/**
 * Utility class containing helper methods to help with getNextSession method in
 * ModelManager.
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
                if (timeNowSession.compareTo(currSession) <= 0) {
                    toSortList.add(currSession);
                    sessionPersonHashMap.put(currSession, person);
                }
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
     * Helper method to get the system time now as a Session for constructor to help getNextSession.
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

    public HashMap<Session, Person> getSessionPersonHashMap() {
        return sessionPersonHashMap;
    }

    /**
     * Returns the feedback for the next closest Session to the current time to ModelManager class.
     * @return a String feedback to be displayed to user.
     */
    public String nextSessionFeedback() {
        return "Next Session: " + sessionPersonHashMap.get(toSortList.get(0)).getName()
                + " " + toSortList.get(0).toString();
    }
}
