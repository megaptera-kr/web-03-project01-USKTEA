package models;

import java.io.IOException;
import java.util.Objects;

public class User {
    private String id;
    private String password;
    private int balance;
    private String grade;

    public User(String[] information) throws IOException {
        this.id = information[0];
        this.password = information[1];
        this.balance = Integer.parseInt(information[2]);
        this.grade = information[3];
    }

    public User(int balance) {
        this.balance = balance;
    }

    public User(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public User() {}

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        User otherUser = (User) other;

        return Objects.equals(id, otherUser.id);
    }

    @Override
    public String toString() {
        return "아이디: " + id + ", " + "비밀번호: " + password + ", " + "잔액: " + balance + ", " + "등급: " + grade;
    }

    public String[] information() {
        return new String[] {id, Integer.toString(balance), grade};
    }

    public void pay(Order order) {
        balance -= order.amount();
    }

    public void refund(Order order) {
        balance += order.amount();
    }

    public void getReward(int reward) {
        this.balance += reward;
    }

    public int balance() {
        return balance;
    }

    public String id() {
        return id;
    }
}
