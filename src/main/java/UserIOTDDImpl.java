import java.util.Scanner;

public class UserIOTDDImpl implements UserIO{

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner scan = new Scanner(System.in);
        try{
            int i = Integer.parseInt(scan.nextLine());
            if(i < min || i>max){
                System.out.println("Not within range");
                return -1;
            }
            return i;
        }catch (NumberFormatException e){
            System.out.println("Not a valid number");
            return -1;
        }
    }

    @Override
    public boolean readAndVerifyPasscode(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Passcode");
        String i = scan.nextLine();
        return i.equals("1234");
    }
}
