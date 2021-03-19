import java.util.ArrayList;
import java.util.List;

public class CoinManager {
    public static List<Coin> calculateChange(int i) {
        List<Coin> change = new ArrayList<>();
        int j = 0;
        int numberOfCoins;
        for (Coin coin : Coin.getAllCoins()) {
            numberOfCoins = (i-j)/coin.getValue();
            j += coin.getValue() * numberOfCoins;
            for (int k = 0; k < numberOfCoins; k++) {
                change.add(coin);
            }
        }
        return change;
    }
}
