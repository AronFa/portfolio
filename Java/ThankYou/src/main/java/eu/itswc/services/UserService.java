package eu.itswc.services;

import eu.itswc.model.User;
import eu.itswc.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(@Autowired UserRepository ur){
        userRepository = ur;
    }

    public void addUser(User user){
        if (userRepository.existsById(user.getUserName())) {
            throw new IllegalArgumentException("This user name is taken, please choose another.");
        } else {
            userRepository.save(user);
        }
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUserName(String userName){
        return userRepository.findFirstByUserName(userName);
    }

    public void updateuser(User user){
        if (userRepository.existsById(user.getUserName())) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User cannot be updated: User not in UserRepository");
        }
    }

    public void deleteUserByUserName(String userName) {
        userRepository.deleteById(userName);
    }
}