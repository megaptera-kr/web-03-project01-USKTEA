package service;

import models.Order;
import models.User;
import repository.UserRepository;

import java.util.Optional;

import java.io.IOException;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void setSession(User user) {
        userRepository.setSession(user);
    }

    public Optional<User> getSession() {
        return userRepository.getSession();
    }

    public void purchase(User user, Order order) throws IOException {
        user.pay(order);

        userRepository.modifyMoney(user.id(), user.balance());
    }

    public void refund(User user, Order order) throws IOException {
        user.refund(order);

        userRepository.modifyMoney(user.id(), user.balance());
    }

    public void getReward(User user, int reward) throws IOException {
        user.getReward(reward);

        userRepository.modifyMoney(user.id(), user.balance());
    }
}
