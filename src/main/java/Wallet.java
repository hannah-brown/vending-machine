public interface Wallet {
    int getBalance();

    int getTotalIncome();

    void addCoin(Coin coin);

    void buyProductAndUpdateBalance(Product product);

    void clearBalance();

    void clearTotalIncome();

    boolean isPurchasable(Product product);

}
