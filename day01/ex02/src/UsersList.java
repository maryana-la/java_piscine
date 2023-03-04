interface UsersList {
    void addUser(User person);
    User findUserById(int id) throws UserNotFoundException;
    User findUserByIndex(int index) throws UserNotFoundException;
    int numberOfUsers();
}
