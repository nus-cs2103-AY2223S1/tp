package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.Title;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code NuScheduler} with sample data.
 */
public class SampleDataUtil {
    private static Profile alex = new Profile(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@u.nus.edu"), new Telegram("lexyeoh"), getTagSet("friends"));
    private static Profile bernice = new Profile(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@u.duke.nus.edu"), Telegram.EMPTY_TELEGRAM, getTagSet("colleagues", "friends"));
    private static Profile roy = new Profile(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@u.nus.edu"), Telegram.EMPTY_TELEGRAM, getTagSet("colleagues"));
    private static Event presentation = new Event(new Title("Discuss presentation"), new DateTime("11/10/2022 09:00"),
            new DateTime("11/10/2022 10:00"), getTagSet("CS2103T"));
    private static Event formalDinner = new Event(new Title("Formal dinner"), new DateTime("12/10/2022 19:00"),
                    new DateTime("12/10/2022 23:00"), getTagSet("RC")
            );

    public static Profile[] getSampleProfiles() {
        return new Profile[] {
            alex, bernice,
            new Profile(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@nus.edu.sg"),
                new Telegram("charlotte_ol1"), getTagSet("neighbours")),
            new Profile(new Name("David Li"), new Phone("91031282"), new Email("lidavid@comp.nus.edu.sg"),
                Telegram.EMPTY_TELEGRAM, getTagSet("family")),
            new Profile(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@u.yale-nus.edu.sg"),
                    Telegram.EMPTY_TELEGRAM, getTagSet("classmates")),
            roy
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {presentation,
            new Event(new Title("Practice"), new DateTime("11/10/2022 13:00"),
                    new DateTime("11/10/2022 14:00"), getTagSet("CCA")),
            new Event(new Title("Finish problem set"), new DateTime("12/10/2022 16:00"),
                    new DateTime("12/10/2022 17:00"), getTagSet("CS2109S")),
            formalDinner
        };
    }

    private static void addAttendees() {
        presentation.addAttendees(List.of(bernice, roy));
        formalDinner.addAttendees(List.of(alex));
    }

    private static void addEventsAttending() {
        formalDinner.addToAllAttendees(List.of(alex));
        presentation.addToAllAttendees(List.of(bernice, roy));
    }

    public static ReadOnlyNuScheduler getSampleNuScheduler() {
        NuScheduler sampleAb = new NuScheduler();
        addAttendees();
        addEventsAttending();
        for (Profile sampleProfile : getSampleProfiles()) {
            sampleAb.addProfile(sampleProfile);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
