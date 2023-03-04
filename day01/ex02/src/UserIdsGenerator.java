public class UserIdsGenerator {
    private static int lastID;

//    Singleton pattern V1
    private static UserIdsGenerator instance;  //Singleton pattern
    private UserIdsGenerator() {}
    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

//    Singleton pattern V2
/*    public static class UserIdsGeneratorHolder {
        public static final UserIdsGenerator HOLDER_INSTANCE = new UserIdsGenerator();
    }

    public static UserIdsGenerator getInstance() {
        return UserIdsGeneratorHolder.HOLDER_INSTANCE;
    }*/

    public static int generateId() {
        lastID++;
        return lastID;
    }
}
