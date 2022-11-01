package hobbylist.model.activity;

import java.util.List;
import java.util.function.Predicate;

import hobbylist.commons.util.StringUtil;

public class RatingMatchesGivenValuePredicate implements Predicate<Activity> {
    private final List<String> values;

    public RatingMatchesGivenValuePredicate(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean test(Activity activity) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).split("rate/").length == 2) {
                String[] t = values.get(i).split("rate/");
                int index;
                try {
                    index = Integer.valueOf(t[1]);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (activity.getRating() == index) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RatingMatchesGivenValuePredicate // instanceof handles nulls
                && values.equals(((RatingMatchesGivenValuePredicate) other).values)); // state check
    }
}
