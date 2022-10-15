package hobbylist.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

/**
 * Contains utility methods for populating {@code HobbyList} with sample data.
 */
public class SampleDataUtil {
    public static Activity[] getSampleActivities() {
        List<Date> s = new ArrayList<>();
        return new Activity[] {
            new Activity(new Name("And Then There Were None"),
                new Description("Mystery novel by Agatha Christie"),
                getTagSet("book"), s),
            new Activity(new Name("Battlefield 4"),
                new Description("First person shooter by EA"),
                getTagSet("video_game"), s),
            new Activity(new Name("Charlotte"),
                new Description("Comedy drama anime"),
                getTagSet("anime", "tv_series"),
                    s),
            new Activity(new Name("Despicable Me 3"),
                new Description("Comedy film directed by Pierre Coffin and Kyle Balda"),
                getTagSet("movie"),
                    s),
            new Activity(new Name("42km run"),
                new Description("Exercise on 1 Jan 1970"),
                getTagSet("exercise"),
                    s),
            new Activity(new Name("Sleep for 48 hours"),
                new Description("Sleep from 1 Oct 2022 to 3 Oct 2022"),
                getTagSet("sleep"),
                    s)
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
