package src.main.java.edu.school.chat.models;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        User one = new User(1, "one", "one");
        User two = new User(2, "two", "two");
        Chatroom room = new Chatroom(3, "chatik", one);

        for (int i = 10; i < 15; i++) {
            Message tmp = new Message(i, one, room,"privet two" + i, LocalDateTime.now());
            room.addMessage(tmp);
            System.out.println(tmp.toString());
        }

        System.out.println("user one toString\n" + one.toString());
        System.out.println("user two toString\n" + two.toString());
        System.out.println("chat toString\n" + room);
    }
}
