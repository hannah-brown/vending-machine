import java.util.List;

public class View {
    private final UserIO io;

    public View(UserIO io){
        this.io = io;
    }

    public int printMainMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. View balance");
        io.print("2. Insert Change");
        io.print("3. Select Item");
        io.print("4. Get Change");
        io.print("5. Staff reset");
        io.print("0. Exit");

        int i = io.readInt("Please Select from the choices above ", 0,5);
        return i == -1 ? printMainMenuAndGetSelection() : i;
    }

    public void displayUnsupportedCommand(){
        io.print("Command is Unsupported");
    }

    public void displayUnknownCommand(){
        io.print("Command is unknown");
    }

    public void displayBalance(int i){
        io.print("Current balance is " + i + "c");
    }

    public int printInsertMenuAndGetSelection(){
        io.print("Insert Change");
        io.print("1. Penny");
        io.print("2. Nickel");
        io.print("3. Dime");
        io.print("4. Quarter");
        io.print("0. Exit");

        int i = io.readInt("Please Select from the choices above ", 0,4);
        return i == -1 ? printInsertMenuAndGetSelection() : i;
    }

    public int printProductMenuAndGetSelection(){
        io.print("Product List");
        io.print("1. Coke : 25c");
        io.print("2. Pepsi : 35c");
        io.print("3. Soda : 45c");
        io.print("0. Exit");

        int i = io.readInt("Please Select from the choices above ", 0,3);
        return i == -1 ? printProductMenuAndGetSelection() : i;
    }

    public void printSuccessfulPurchase(int i, Product item){
        io.print("Enjoy your " + item);
        io.print("New balance is " + i + "c");
    }

    public void printUnsuccessfulPurchase(){
        io.print("Transaction Failed");
        io.print("Insufficient funds");
        io.print("This has not changed your balance");
    }

    public void printChangeMessage(int i, List<Coin> change){
        io.print("Please take your change " + i + "c");
        for (Coin coin: change) {
            io.print(coin.name());
        }
    }

    public int printTotalIncomeMenuAndGetSelection(int i){
        io.print("Total earning is " + i + "c");
        io.print("1. Withdraw");
        io.print("0. Exit");
        int selection = io.readInt("Please Select from the choices above ", 0,1);
        return selection == -1 ? printTotalIncomeMenuAndGetSelection(i) : selection;
    }

    public boolean printPasscodeRequestAndVerifyAccess(){
        return io.readAndVerifyPasscode("Please Enter your passcode");
    }

    public void printAccessDenied(){
        io.print("Access Denied");
    }

}
