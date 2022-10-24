package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class GithubWrapper {
    //@@author arnav-ag
    
    private final String user;
    private final UserInfoWrapper userInfoWrapper;

    public GithubWrapper(String user) {
        requireAllNonNull(user);
        this.user = user;
        this.userInfoWrapper = new UserInfoWrapper(user);
    }
}
