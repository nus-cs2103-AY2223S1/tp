//@@author arnav-ag
package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.Config;
import kong.unirest.UnirestInstance;

public class UserInfoWrapper {
    //@@author arnav-ag

    private final GetInfoRoute.GetInfoRequest getUserInfoGetInfoRequest;
    private JSONObject userJson;

    private final String NAME_KEY = "name";
    private final String USERNAME_KEY = "login";
    private final String EMAIL_KEY = "email";
    private final String LOCATION_KEY = "location";
    private final String IMAGE_LOCATION_KEY = "avatar_url";

    public UserInfoWrapper(String user) {
        requireAllNonNull(user);

        GetInfoRoute getUserInfoGetInfoRoute = GetInfoRoute.getUserInfoRoute(user);
        Config config = new Config();
        UnirestInstance unirest = new UnirestInstance(config);

        getUserInfoGetInfoRequest = getUserInfoGetInfoRoute.createRequest(unirest);
    }

    private void getUserJson() {
        this.userJson = this.getUserInfoGetInfoRequest.getJSON();
    }

    public Optional<String> getName() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(NAME_KEY, null));
    }

    public Optional<String> getUsername() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(USERNAME_KEY, null));
    }

    public Optional<String> getEmail() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(EMAIL_KEY, null));
    }

    public Optional<String> getLocation() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(LOCATION_KEY, null));
    }

    public Optional<String> getImageLocation() {
        if (userJson == null) {
            getUserJson();
        }
        return Optional.ofNullable(userJson.optString(IMAGE_LOCATION_KEY, null));
    }
}
