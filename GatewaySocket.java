import java.io.*;
import java.net.*;

public class GatewaySocket{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ServerCallback callback;

    public void start(int port, ServerCallback callback) throws IOException {
        this.callback = callback;
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            new Thread(() -> {
                try {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received message from client: " + inputLine);
                        callback.handleMessage(inputLine);
                    }

                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public void send(String message) {
        out.println(message);
    }

    public interface ServerCallback {
        void handleMessage(String message);
    }
}
