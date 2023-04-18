package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {
    @Override
    public String toString() {
        return "Non-existing value of user/room id.";
    }

    @Override
    public String getLocalizedMessage() {
        return "Non-existing value of user/room id.";
    }
}
