package version1_1netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server 端首先创建了一个serverSocket来监听 8000 端口，
 * 然后创建一个线程，线程里面不断调用阻塞方法 serverSocket.accept();
 * 获取新的连接，见(1)，当获取到新的连接之后，给每条连接创建一个新的线程，这个线程负责从该连接中读取数据，见(2)，
 * 然后读取数据是以字节流的方式，见(3)。
 */
public class IOServer {
    public static void main(String[] args) throws Exception {

        final ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // (1) 阻塞方法获取新的连接
                        final Socket socket = serverSocket.accept();

                        // (2) 每一个新的连接都创建一个线程，负责读取数据
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    int len;
                                    byte[] data = new byte[1024];
                                    InputStream inputStream = socket.getInputStream();
                                    // (3) 按字节流方式读取数据
                                    while ((len = inputStream.read(data)) != -1) {
                                        System.out.println(new String(data, 0, len));
                                    }
                                } catch (IOException e) {
                                }
                            }
                        }).start();

                    } catch (IOException e) {
                    }

                }
            }
        }).start();
    }
}