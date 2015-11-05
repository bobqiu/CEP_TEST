package callback;

/**
 * Created by Administrator on 2015/11/4.
 */
public class Test {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("hello");

    }
}
