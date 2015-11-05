package callback;

import sun.applet.resources.MsgAppletViewer;

/**
 * Created by Administrator on 2015/11/4.
 */

public class Client implements CallBack {

    private Server server;
    public Client(Server server) {
        this.server = server;
    }

    public void sendMsg(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.getClientMsg(Client.this, s);
            }
        }).start();
    }

    @Override
    public void process(String status) {
        System.out.println("服务器端返回状态码："+status);
    }

    @Override
    public void dosth(String s) {
        System.out.println("client do something");
    }
}
