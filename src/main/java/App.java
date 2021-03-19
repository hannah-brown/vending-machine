public class App {
    public static void main(String[] args) {
        UserIO ui = new UserIOTDDImpl();
        Wallet wallet = new WalletImpl(0);
        View view = new View(ui);
        Controller controller = new Controller(view,wallet);
        controller.run();
    }
}
