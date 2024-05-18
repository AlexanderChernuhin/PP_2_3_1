package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        logger.debug("Fetching all users");
        List<User> users = dao.getAllUsers();
        logger.info("Fetched {} users", users.size());
        return users;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        logger.debug("Fetching user with ID: {}", id);
        User user = dao.getUserById(id);
        if (user != null) {
            logger.info("User with ID: {} found", id);
        } else {
            logger.warn("User with ID: {} not found", id);
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        logger.debug("Saving user: {}", user);
        dao.saveUser(user);
        logger.info("User saved successfully: {}", user);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser, int id) {
        logger.debug("Updating user with ID: {}", id);
        User user = getUserById(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setEmail(updatedUser.getEmail());
            dao.updateUser(user);
            logger.info("User with ID: {} updated successfully", id);
        } else {
            logger.warn("User with ID: {} not found. Update aborted.", id);
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        logger.debug("Deleting user with ID: {}", id);
        dao.deleteUser(id);
        logger.info("User with ID: {} deleted successfully", id);
    }
}
