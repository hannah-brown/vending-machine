import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    @Mock
    private View view;

    @Mock
    private Wallet wallet;

    @Test
    public void shouldDisplayBalanceWhenOneIsSelected(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(1).thenReturn(0);

        controller.run();

        verify(view, times(1)).displayBalance(wallet.getBalance());
    }

    //todo add inorder test
    @Test
    public void shouldInsertCorrectChange(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(2).thenReturn(0);
        when(view.printInsertMenuAndGetSelection()).thenReturn(1);

        controller.run();

        verify(view, times(1)).printInsertMenuAndGetSelection();
        verify(wallet, times(1)).addCoin(Coin.convertIntToCoin(1));
        verify(view, times(1)).displayBalance(wallet.getBalance());
    }

    @Test
    public void shouldExitInsertChangeWhenZeroIsSelected(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(2).thenReturn(0);
        when(view.printInsertMenuAndGetSelection()).thenReturn(0);

        controller.run();

        verify(view, times(1)).printInsertMenuAndGetSelection();
        verify(wallet, times(0)).addCoin(Coin.convertIntToCoin(1));
        verify(view, times(0)).displayBalance(wallet.getBalance());
    }

    @Test
    public void shouldSelectedCorrectItem(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(3).thenReturn(0);
        when(view.printProductMenuAndGetSelection()).thenReturn(1);
        when(wallet.isPurchasable(any())).thenReturn(true);
        Product item = Product.convertIntToProduct(1);

        controller.run();

        verify(view, times(1)).printProductMenuAndGetSelection();
        verify(wallet, times(1)).isPurchasable(item);
        verify(wallet, times(1)).buyProductAndUpdateBalance(item);
        verify(view, times(1)).printSuccessfulPurchase(wallet.getBalance(), item);
        verify(view, times(0)).printUnsuccessfulPurchase();
    }

    @Test
    public void shouldPrintUnsuccessfulPurchaseWhenIsPurchasableReturnsFalse(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(3).thenReturn(0);
        when(view.printProductMenuAndGetSelection()).thenReturn(1);
        when(wallet.isPurchasable(any())).thenReturn(false);
        Product item = Product.convertIntToProduct(1);

        controller.run();

        verify(view, times(1)).printProductMenuAndGetSelection();
        verify(wallet, times(1)).isPurchasable(item);
        verify(wallet, times(0)).buyProductAndUpdateBalance(item);
        verify(view, never()).printSuccessfulPurchase(wallet.getBalance(), item);
        verify(view, times(1)).printUnsuccessfulPurchase();
    }

    @Test
    public void shouldCallGetChangeWhenFourIsSelected(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(4).thenReturn(0);

        controller.run();

        verify(view, times(1)).printChangeMessage(wallet.getBalance(), CoinManager.calculateChange(wallet.getBalance()));
        verify(wallet, times(1)).clearBalance();
    }

    @Test
    public void shouldAllowStaffResetWhenValidPasscode(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(5).thenReturn(0);
        when(view.printPasscodeRequestAndVerifyAccess()).thenReturn(true);
        when(view.printTotalIncomeMenuAndGetSelection(anyInt())).thenReturn(1);

        controller.run();

        verify(view, times(1)).printTotalIncomeMenuAndGetSelection(wallet.getTotalIncome());
        verify(view, times(1)).printChangeMessage(wallet.getTotalIncome(), CoinManager.calculateChange(wallet.getTotalIncome()));
        verify(wallet, times(1)).clearTotalIncome();
        verify(view, never()).printAccessDenied();
    }

    @Test
    public void shouldCallAccessDeniedWhenInvalidPasscode(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(5).thenReturn(0);
        when(view.printPasscodeRequestAndVerifyAccess()).thenReturn(false);

        controller.run();

        verify(view, never()).printTotalIncomeMenuAndGetSelection(wallet.getTotalIncome());
        verify(view, never()).printChangeMessage(wallet.getTotalIncome(), CoinManager.calculateChange(wallet.getTotalIncome()));
        verify(wallet, never()).clearTotalIncome();
        verify(view, times(1)).printAccessDenied();
    }

    @Test
    public void shouldExitStaffResetWhenZeroIsSelected(){
        Controller controller = new Controller(view, wallet);
        when(view.printMainMenuAndGetSelection()).thenReturn(5).thenReturn(0);
        when(view.printPasscodeRequestAndVerifyAccess()).thenReturn(true);
        when(view.printTotalIncomeMenuAndGetSelection(anyInt())).thenReturn(0);

        controller.run();

        verify(view, times(1)).printTotalIncomeMenuAndGetSelection(wallet.getTotalIncome());
        verify(view, never()).printChangeMessage(wallet.getTotalIncome(), CoinManager.calculateChange(wallet.getTotalIncome()));
        verify(wallet, never()).clearTotalIncome();
        verify(view, never()).printAccessDenied();
    }
}
