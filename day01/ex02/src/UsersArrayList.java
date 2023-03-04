public class UsersArrayList implements UsersList {
    private int arraySize;
    private int arrayCapacity;
    private User[] usersArray;

    UsersArrayList() {
        arraySize = 10;
        arrayCapacity = 0;
        usersArray = new User[arraySize];
    }

    UsersArrayList(int size) {
        arraySize = size;
        arrayCapacity = 0;
        usersArray = new User[arraySize];
    }

    /* GETTERS - SETTERS */
    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int size) {
        if (size <= arraySize)
            System.out.println("Array size unchanged");
        else
            resize(size);
    }

    /* METHODS */
    @Override
    public void addUser(User person) {
        if (person == null)
            return;
        if (arrayCapacity + 1 == arraySize)
            resize(arraySize + arraySize / 2);
        usersArray[arrayCapacity] = person;
        arrayCapacity++;
    }

    @Override
    public User findUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < arrayCapacity; i++) {
            if (usersArray[i].getIdentifier() == id)
                return usersArray[i];
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public User findUserByIndex(int index) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        if (index > arrayCapacity || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        return usersArray[index];
    }

    @Override
    public int numberOfUsers() {
        return arrayCapacity;
    }

    /* PRIVATE METHODS */
    private void resize(int newSize) {
        User[] tmp = new User[newSize];
        for (int i = 0; i < arraySize; i++)
            tmp[i] = usersArray[i];
        usersArray = tmp;
        arraySize = newSize;
    }
}