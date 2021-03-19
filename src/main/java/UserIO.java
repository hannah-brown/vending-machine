public interface UserIO {

    void print(String msg);

    int readInt(String prompt, int min, int max);

    boolean readAndVerifyPasscode(String prompt);
}
