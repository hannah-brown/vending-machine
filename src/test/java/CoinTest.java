import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CoinTest {

    @Test
    public void testConvertIntToCoin() {
        Coin penny = Coin.convertIntToCoin(1);
        Coin nickel = Coin.convertIntToCoin(2);
        Coin dime = Coin.convertIntToCoin(3);
        Coin quarter = Coin.convertIntToCoin(4);
        Coin nothing = Coin.convertIntToCoin(0);

        assertThat(penny).isEqualByComparingTo(Coin.PENNY);
        assertThat(nickel).isEqualByComparingTo(Coin.NICKEL);
        assertThat(dime).isEqualByComparingTo(Coin.DIME);
        assertThat(quarter).isEqualByComparingTo(Coin.QUARTER);
        assertThat(nothing).isNull();
    }

}