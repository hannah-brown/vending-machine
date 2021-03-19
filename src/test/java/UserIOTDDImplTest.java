import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIOTDDImplTest {

    @Test
    public void shouldCallSystemOutPrint() throws IOException {
        UserIO io = new UserIOTDDImpl();
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        io.print("test");

        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());

        assertThat(allWrittenLines).isEqualTo("test\n");
    }

    @Test
    public void shouldReadIntWhenValid(){
        UserIO io = new UserIOTDDImpl();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int actualInt = io.readInt("test",0,Integer.MAX_VALUE);

        assertThat(actualInt).isEqualTo(1);
    }

    @Test
    public void shouldCatchInvalidNumber() throws IOException {
        UserIO io = new UserIOTDDImpl();
        String input = "one";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        int actualInt = io.readInt("test", 0,Integer.MAX_VALUE);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());

        assertThat(allWrittenLines).isEqualTo("Not a valid number\n");
        assertThat(actualInt).isEqualTo(-1);

    }

    @Test
    public void shouldCatchNumberOutsideRange() throws IOException {
        UserIO io = new UserIOTDDImpl();
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        int actualInt = io.readInt("test", 0,3);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());

        assertThat(allWrittenLines).isEqualTo("Not within range\n");
        assertThat(actualInt).isEqualTo(-1);
    }

    @Test
    public void shouldReturnTrueWhenValidPasscode(){
        UserIO io = new UserIOTDDImpl();
        String input = "1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        boolean actualBool = io.readAndVerifyPasscode("test");

        assertThat(actualBool).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenInValidPasscode(){
        UserIO io = new UserIOTDDImpl();
        String input = "wrong";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        boolean actualBool = io.readAndVerifyPasscode("test");

        assertThat(actualBool).isFalse();
    }
}