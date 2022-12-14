package controller;

import models.Order;
import models.Service;
import models.User;
import service.PaymentService;
import service.UserService;

import java.util.Optional;
import java.io.IOException;

public class MallPanelController {
    private UserService userService;
    private Optional<User> session;
    private User user;

    public MallPanelController(UserService userService) {
        this.userService = userService;
        this.session = userService.getSession();
    }

    public String[] userInformation() {
        if (session.isEmpty()) {
            return new String[] {"Guest", "-", "방문 고객"};
        }

        return session.get().information();
    }

    public Optional<Order> purchase(Service service) throws IOException {
        user = session.get();

        Optional<Order> order = new PaymentService().purchase(user, service);

        if (order.isEmpty()) {
            return Optional.empty();
        }

        return order;
    }

    public Optional<User> getSession() {
        return session;
    }

    public void toCart(Service service) throws IOException {
        user = session.get();

        new CartController(user).add(service);
    }

    public void send(int reward) throws IOException {
        user = session.get();

        userService.getReward(user, reward);
    }
}
