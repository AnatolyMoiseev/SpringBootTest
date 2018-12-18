package hello.services;

import hello.domains.User;

import java.util.Optional;

public interface UserService {

    Integer addUser(User user);
    Optional<User> getUSer(int id);
    Integer changeStatus(int id, Boolean newStatus);
    Iterable<User> getUsersByStatus(Boolean status);
    User updateUser(User user);

}
