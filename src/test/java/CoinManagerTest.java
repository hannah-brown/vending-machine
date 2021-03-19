import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinManagerTest {

    @Test
    public void testCalculateChange() {
        List<Coin> actualChange = CoinManager.calculateChange(41);
        List<Coin> expectedChange = new ArrayList<>();
        expectedChange.add(Coin.QUARTER);
        expectedChange.add(Coin.DIME);
        expectedChange.add(Coin.NICKEL);
        expectedChange.add(Coin.PENNY);

        assertThat(actualChange).isEqualTo(expectedChange);
    }
}
