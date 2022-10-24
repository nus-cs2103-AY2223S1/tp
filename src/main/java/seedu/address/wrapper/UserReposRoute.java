package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.JsonParseException;
import seedu.address.wrapper.exceptions.ResponseInvalidException;

public class UserReposRoute {
    //@@author arnav-ag

    public static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String SERVER_DOWN_STATUS_MESSAGE = "Unable to get information from API.";

    /**
     * Whether to run status checks on {@link UserInfoRoute.UserInfoRequest#getJSON()} by default.
     */
    public static boolean defaultRunChecks = true;

    private static final String GET_USER_BASE_PATH = "/users/";
    private static final String GET_REPO_PATH = "/repos";

    private final String path;

    private UserReposRoute(String path) {
        requireAllNonNull(path);
        this.path = path;
    }

    public static UserReposRoute getUserReposRoute(String user) {
        requireAllNonNull(user);
        return new UserReposRoute(GET_USER_BASE_PATH + user + GET_REPO_PATH);
    }

    public UserReposRequest createRequest(UnirestInstance unirest) {
        requireAllNonNull(unirest);

        return new UserReposRequest(unirest, BASE_GITHUB_URL + this.path);
    }

    public String getPath() {
        return this.path;
    }

    public static class UserReposRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserReposRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public JSONArray getJSON() {
            return getJSON(defaultRunChecks);
        }

        public JSONArray getJSON(boolean runChecks) {
            String response = this.unirest.get(this.url).asString().getBody();

            try {
                JSONObject toCheck = new JSONObject(response);
                if (runChecks) {
                    ensureSuccessful(toCheck);
                }
            } catch (JSONException ignored) {
                // This only happens if JSON does not contain an error
                // i.e. it is as intended, as a proper JSONArray will cause JSONObject to throw this exception.
            }

            try {
                return new JSONArray(response);
            } catch (JSONException e) {
                throw new JsonParseException("Unable to parse API result.");
            }
        }

        static void ensureSuccessful(JSONObject response) {
            // Only appears when 404 response is given, only other response is 200 OK
            if (response.toMap().containsKey("message")) {
                throw new ResponseInvalidException(SERVER_DOWN_STATUS_MESSAGE);
            }
        }
    }
}
