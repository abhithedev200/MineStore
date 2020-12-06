import java.net.Socket;

public class websocketClient {

    public static void main(String[] args) throws Exception
    {
        Socket soc = new Socket("localhost",33266);

        String str = "hard_password_here  op Spigot_Abhiram";

        soc.getOutputStream().write(str.getBytes());

        soc.getOutputStream().flush();

        soc.close();
    }
}
