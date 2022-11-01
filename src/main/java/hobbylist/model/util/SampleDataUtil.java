package hobbylist.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.activity.Review;
import hobbylist.model.activity.Status;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

/**
 * Contains utility methods for populating {@code HobbyList} with sample data.
 */
public class SampleDataUtil {

    public static final Status NO_STATUS = new Status();

    public static Activity[] getSampleActivities() {

        Optional<Date> s = Optional.empty();
        return new Activity[] {
            new Activity(new Name("And Then There Were None"),
                new Description("Mystery novel by Agatha Christie"),
                getTagSet("book"), s, new Status("ongoing"),
                    Optional.of(new Review("Interesting read, great unsolvable mystery"))),
            new Activity(new Name("Battlefield 4"),
                new Description("First person shooter by EA"),
                getTagSet("video_game"), s, new Status("upcoming"), Optional.empty()),
            new Activity(new Name("Charlotte"),
                new Description("Comedy drama anime"),
                getTagSet("anime", "tv_series"),
                s, new Status("completed"), Optional.of(new Review("Good overall but strange ending"))),
            new Activity(new Name("Despicable Me 3"),
                new Description("Comedy film directed by Pierre Coffin and Kyle Balda"),
                getTagSet("movie"),
                s, NO_STATUS, Optional.empty()),
            new Activity(new Name("42km run"),
                new Description("Exercise on 1 Jan 1970"),
                getTagSet("exercise"),
                s, NO_STATUS, Optional.empty()),
            new Activity(new Name("Sleep for 48 hours"),
                new Description("Sleep from 1 Oct 2022 to 3 Oct 2022"),
                getTagSet("sleep"),
                s, NO_STATUS, Optional.of(new Review("finally some good sleep")))
        };
    }

    public static ReadOnlyHobbyList getSampleHobbyList() {
        HobbyList sampleAb = new HobbyList();
        for (Activity sampleActivity : getSampleActivities()) {
            sampleAb.addActivity(sampleActivity);
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
