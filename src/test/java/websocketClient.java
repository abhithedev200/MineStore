import com.google.gson.JsonObject;

import java.net.Socket;

public class websocketClient {

    public static void main(String[] args) throws Exception
    {
        Socket soc = new Socket("localhost",33266);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("password","hard_password_here");
        jsonObject.addProperty("command","say Spigot_Abhiram");
        jsonObject.addProperty("username","Spigot_Abhiram");

        System.out.println(jsonObject.toString().getBytes());
        soc.getOutputStream().write(jsonObject.toString().getBytes());

        soc.getOutputStream().flush();

        soc.close();
    }
}
