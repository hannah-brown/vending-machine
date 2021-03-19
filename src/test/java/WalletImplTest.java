import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletImplTest {

    private Wallet wallet;

    @Before
    public void setup(){
        wallet = new WalletImpl(0);
    }

    @Test
    public void testAddCoinForPenny() {
        //Act
        wallet.addCoin(Coin.PENNY);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(1);
    }

    @Test
    public void testAddCoinForNickel() {
        //Act
        wallet.addCoin(Coin.NICKEL);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(5);
    }

    @Test
    public void testAddCoinForDime() {
        //Act
        wallet.addCoin(Coin.DIME);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(10);
    }

    @Test
    public void testAddCoinForQuarter() {
        //Act
        wallet.addCoin(Coin.QUARTER);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(25);
    }

    @Test
    public void testBuyProductAndUpdateBalanceForCoke() {
        //Arrange
        Wallet wallet = new WalletImpl(50);
        //Act
        wallet.buyProductAndUpdateBalance(Product.Coke);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(25);
        assertThat(wallet.getTotalIncome()).isEqualTo(50);
    }

    @Test
    public void testBuyProductAndUpdateBalanceForPepsi() {
        //Arrange
        Wallet wallet = new WalletImpl(50);
        //Act
        wallet.buyProductAndUpdateBalance(Product.Pepsi);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(15);
        assertThat(wallet.getTotalIncome()).isEqualTo(50);

    }

    @Test
    public void testBuyProductAndUpdateBalanceForSoda() {
        //Arrange
        Wallet wallet = new WalletImpl(50);
        //Act
        wallet.buyProductAndUpdateBalance(Product.Soda);
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(5);
        assertThat(wallet.getTotalIncome()).isEqualTo(50);
    }

    @Test
    public void testClearBalanceWithoutPurchase() {
        //Arrange
        Wallet wallet = new WalletImpl(50);
        //Act
        wallet.clearBalance();
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(0);
    }

    @Test
    public void testClearBalanceWithoutPurchaseWithBalanceZero() {
        //Act
        wallet.clearBalance();
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(0);

    }

    @Test
    public void testClearBalanceWithPurchase() {
        //Arrange
        Wallet wallet = new WalletImpl(50);
        wallet.buyProductAndUpdateBalance(Product.Soda);
        //Act
        wallet.clearBalance();
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(45);
    }

    @Test
    public void testAddCoinAndClearBalanceWithPurchase() {
        //Arrange
        wallet.addCoin(Coin.QUARTER);
        wallet.addCoin(Coin.QUARTER);
        wallet.buyProductAndUpdateBalance(Product.Soda);
        //Act
        wallet.clearBalance();
        //Assert
        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(45);
    }

    @Test
    public void testAddCoinAndClearBalanceWithoutPurchase(){
        wallet.addCoin(Coin.QUARTER);
        wallet.addCoin(Coin.QUARTER);

        wallet.clearBalance();

        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(0);
    }

    @Test
    public void testClearTotalIncome() {
        Wallet wallet = new WalletImpl(50);

        wallet.clearTotalIncome();

        assertThat(wallet.getBalance()).isEqualTo(0);
        assertThat(wallet.getTotalIncome()).isEqualTo(0);
    }

    @Test
    public void testIsPurchasable() {
        Wallet wallet = new WalletImpl(35);

        boolean cokeIsPurchasable = wallet.isPurchasable(Product.Coke);
        boolean pepsiIsPurchasable = wallet.isPurchasable(Product.Pepsi);
        boolean sodaIsNotPurchasable = wallet.isPurchasable(Product.Soda);

        assertThat(cokeIsPurchasable).isTrue();
        assertThat(pepsiIsPurchasable).isTrue();
        assertThat(sodaIsNotPurchasable).isFalse();
    }
}