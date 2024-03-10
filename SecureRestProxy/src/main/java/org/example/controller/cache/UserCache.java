package org.example.controller.cache;

import java.util.HashMap;
import java.util.Map;
import org.example.model.User;

public class UserCache {
  private Map<String, User> userCache;

  public UserCache() {
    this.userCache = new HashMap<>();
  }

  public User getUserFromCache(String userName) {
    return userCache.get(userName);
  }

  public void addToCache(User user) {
    userCache.put(user.getUsername(), user);
  }

  public boolean containsUser(String userName) {
    return userCache.containsKey(userName);
  }

  public void removeFromCache(String userName) {
    userCache.remove(userName);
  }
}
