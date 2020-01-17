import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.util.ArrayList;


public class Server {
    private ServerSocket listener;
    private ArrayList<Connection> connections;

    public Server(){
        try {
            listener = new ServerSocket(9090);
            connections = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        while(true){
            try {
                Socket conn = listener.accept();
                Connection c = new Connection(conn, this);
                Thread t = new Thread(c);
                t.run();
                connections.add(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void message(String msg) {
        for (Connection c: connections){
           c.send(msg);
        }
    }
}
