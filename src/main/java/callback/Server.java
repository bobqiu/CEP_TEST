package callback;

/**
 * Created by Administrator on 2015/11/4.
 */
public class Server {
    public void getClientMsg(CallBack callBack, String msg) {
        System.out.println("服务器端接收到客户端信息为：" + msg);

        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String status = "200";
        System.out.println("服务器端成功接收消息，返回状态："+status);
        callBack.process(status);

    }
}
