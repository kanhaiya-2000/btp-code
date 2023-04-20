import java.io.*;
import java.net.*;

public class ClientSocket {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ClientCallback callback;

    public void start(String ip, int port, ClientCallback callback) throws IOException {
        this.callback = callback;
        socket = new Socket(ip, port);
        System.out.println("Connected to server: " + ip + ":" + port);

        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message from server: " + inputLine);
                    callback.handleMessage(inputLine);
                }

                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public void send(String message) {
        out.println(message);
    }

    public interface ClientCallback {
        void handleMessage(String message);
    }
}
