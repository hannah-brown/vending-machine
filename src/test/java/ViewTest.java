import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {
    @Mock
    private UserIO io;

    @Test
    public void shouldPrintCoins(){
        View view = new View(io);
        List<Coin> change = newArrayList(Coin.NICKEL,Coin.DIME);

        view.printChangeMessage(1, change);

        verify(io, times(3)).print(anyString());
        verify(io, times(1)).print(Coin.NICKEL.name());
        verify(io, times(1)).print(Coin.DIME.name());

    }

    @Test
    public void shouldPrintMainMenuAndGetSelectionWhenValidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(1);

        int i = view.printMainMenuAndGetSelection();

        verify(io, times(7)).print(anyString());
        verify(io, times(1)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldRepeatedlyRecallPrintMainMenuWhenInvalidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(-1).thenReturn(1);

        int i = view.printMainMenuAndGetSelection();

        verify(io, times(14)).print(anyString());
        verify(io, times(2)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldPrintInsertMenuAndGetSelectionWhenValidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(1);

        int i = view.printInsertMenuAndGetSelection();

        verify(io, times(6)).print(anyString());
        verify(io, times(1)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldRepeatedlyRecallPrintInsertMenuWhenInvalidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(-1).thenReturn(1);

        int i = view.printInsertMenuAndGetSelection();

        verify(io, times(12)).print(anyString());
        verify(io, times(2)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }


    @Test
    public void shouldPrintProductMenuAndGetSelectionWhenValidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(1);

        int i = view.printProductMenuAndGetSelection();

        verify(io, times(5)).print(anyString());
        verify(io, times(1)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldRepeatedlyRecallPrintProductMenuWhenInvalidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(-1).thenReturn(1);

        int i = view.printProductMenuAndGetSelection();

        verify(io, times(10)).print(anyString());
        verify(io, times(2)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldPrintTotalIncomeMenuAndGetSelectionWhenValidInput(){
        View view = new View(io);
        when(io.readInt(anyString(),anyInt(),anyInt())).thenReturn(1);

        int i = view.printTotalIncomeMenuAndGetSelection(50);

        verify(io, times(3)).print(anyString());
        verify(io, times(1)).readInt(anyString(),anyInt(),anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldRepeatedlyRecallPrintIncomeMenuWhenInvalidInput() {
        View view = new View(io);
        when(io.readInt(anyString(), anyInt(), anyInt())).thenReturn(-1).thenReturn(1);

        int i = view.printTotalIncomeMenuAndGetSelection(50);

        verify(io, times(6)).print(anyString());
        verify(io, times(2)).readInt(anyString(), anyInt(), anyInt());
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void shouldReturnTrueWhenValidInput(){
        View view = new View(io);
        when(io.readAndVerifyPasscode(anyString())).thenReturn(true);

        boolean bool = view.printPasscodeRequestAndVerifyAccess();

        verify(io, times(0)).print(anyString());
        verify(io, times(0)).readInt(anyString(),anyInt(),anyInt());
        verify(io, times(1)).readAndVerifyPasscode(anyString());
        assertThat(bool).isTrue();
    }

}
