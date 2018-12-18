package hello.services.implement;

import hello.domains.User;
import hello.repositories.UserRepository;
import hello.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            return newUser.getId();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<User> getUSer(int id) {
        Optional<User> returnUser = userRepository.findById(id);
        return returnUser;
    }


    @Override
    public Integer changeStatus(int id, Boolean newStatus) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            User userForChange = optionalUser.get();
            userForChange.setStatus(newStatus);
            userRepository.save(userForChange);
            return userForChange.getId();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getUsersByStatus(Boolean status) {
        if (status == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByStatus(status);
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        User updateUser = optionalUser.isPresent() ? optionalUser.get() : null;
        if (updateUser != null) {

            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setStatus(user.getStatus());
            userRepository.save(updateUser);

        }

        return updateUser;
    }
}