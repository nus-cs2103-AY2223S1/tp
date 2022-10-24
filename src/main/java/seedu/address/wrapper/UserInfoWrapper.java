//@@author arnav-ag
package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.Config;
import kong.unirest.UnirestInstance;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.contact.Email;

public class UserInfoWrapper {
    //@@author arnav-ag

    private final UserInfoRoute.UserInfoRequest getUserInfoUserInfoRequest;
    private JSONObject userJson;

    private final String NAME_KEY = "name";
    private final String USERNAME_KEY = "login";
    private final String EMAIL_KEY = "email";
    private final String LOCATION_KEY = "location";
    private final String IMAGE_LOCATION_KEY = "avatar_url";

    public UserInfoWrapper(String user) {
        requireAllNonNull(user);

        UserInfoRoute getUserInfoUserInfoRoute = UserInfoRoute.getUserInfoRoute(user);
        Config config = new Config();
        UnirestInstance unirest = new UnirestInstance(config);

        getUserInfoUserInfoRequest = getUserInfoUserInfoRoute.createRequest(unirest);
    }

    private void getUserJson() {
        this.userJson = this.getUserInfoUserInfoRequest.getJSON();
    }

    public Optional<Name> getName() {
        if (userJson == null) {
            getUserJson();
        }

        String result = userJson.optString(NAME_KEY, null);

        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(new Name(result));
    }

    public Optional<String> getUsername() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(USERNAME_KEY, null));
    }

    public Optional<Email> getEmail() {
        if (userJson == null) {
            getUserJson();
        }

        String result = userJson.optString(EMAIL_KEY, null);

        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(new Email(result));
    }

    public Optional<Address> getLocation() {
        if (userJson == null) {
            getUserJson();
        }

        String result = userJson.optString(LOCATION_KEY, null);

        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(new Address(result));
    }

    public Optional<String> getImageLocation() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(IMAGE_LOCATION_KEY, null));
    }
}
