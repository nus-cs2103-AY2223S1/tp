package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.wrapper.UserReposRoute.getUserReposRoute;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.RepoNotFoundException;

public class UserReposWrapper {
    //@@author arnav-ag

    private final UserReposRoute.UserReposRequest getUserReposRequest;
    private JSONArray reposJson;

    public UserReposWrapper(String user, UnirestInstance unirest) {
        requireAllNonNull(user);

        UserReposRoute getUserInfoGetInfoRoute = getUserReposRoute(user);
        getUserReposRequest = getUserInfoGetInfoRoute.createRequest(unirest);
    }

    private void updateReposJson() {
        this.reposJson = getUserReposRequest.getJSON();
    }

    public ArrayList<Integer> getIDs() {
        if (this.reposJson == null) {
            updateReposJson();
        }
        ArrayList<Integer> result = new ArrayList<>();
        JSONObject obj;
        for (int i = 0; i < this.reposJson.length(); i++) {
            obj = (JSONObject) this.reposJson.get(i);
            result.add(obj.getInt("id"));
        }

        return result;
    }

    public String getRepoName(int id) {
        if (this.reposJson == null) {
            updateReposJson();
        }
        JSONObject obj;
        for (int i = 0; i < this.reposJson.length(); i++) {
            obj = (JSONObject) this.reposJson.get(i);
            if (obj.getInt("id") == id) {
                return obj.getString("name");
            }
        }

        throw new RepoNotFoundException("Provided ID does not correspond to a repository owned by this user!");
    }

    public String getRepoUrl(int id) {
        if (this.reposJson == null) {
            updateReposJson();
        }
        JSONObject obj;
        for (int i = 0; i < this.reposJson.length(); i++) {
            obj = (JSONObject) this.reposJson.get(i);
            if (obj.getInt("id") == id) {
                return obj.getString("html_url");
            }
        }

        throw new RepoNotFoundException("Provided ID does not correspond to a repository owned by this user!");
    }
}
