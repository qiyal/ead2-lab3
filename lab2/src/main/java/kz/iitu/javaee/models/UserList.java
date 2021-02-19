package kz.iitu.javaee.models;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private Map<String, User> userMap = new HashMap<String, User>();
    
    {
        userMap.put("admin", new User("admin", "admin"));
        userMap.put("qwerty", new User("qwerty", "qwerty"));
    }
    
    public UserList() {}
    
    public User getUser(String username) {
        return userMap.get(username);
    }
    
}
