package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private  EntityManager manager;

    @Override
    public List<User> getAllUsers() {
        List<User> users = manager.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public User getUserById(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        manager.merge(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        manager.remove(manager.find(User.class, id));
    }
}
