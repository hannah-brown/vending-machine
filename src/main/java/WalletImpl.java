public class WalletImpl implements Wallet{
    private int balance;
    private int totalIncome;

    WalletImpl(int balance){
        this.balance = balance;
        this.totalIncome = balance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int getTotalIncome() {
        return totalIncome;
    }

    @Override
    public void addCoin(Coin coin) {
        balance += coin.getValue();
        totalIncome += coin.getValue();
    }

    @Override
    public void buyProductAndUpdateBalance(Product product) {
        balance -= product.getPrice();
    }

    @Override
    public void clearBalance() {
        this.totalIncome -= balance;
        this.balance = 0;
    }

    @Override
    public void clearTotalIncome() {
        this.totalIncome = 0;
        this.balance = 0;
    }

    @Override
    public boolean isPurchasable(Product product) {
        return balance >= product.getPrice();
    }



}
