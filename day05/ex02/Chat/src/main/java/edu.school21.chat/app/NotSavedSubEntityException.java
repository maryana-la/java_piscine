package edu.school21.chat.app;

public class NotSavedSubEntityException extends RuntimeException {
    @Override
    public String toString() {
        return "Non-existing value of user/room id.";
    }
}
