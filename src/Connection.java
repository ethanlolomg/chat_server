import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
// Queue <String> to store messages
//lock thread


public class Connection implements Runnable {
    private Socket conn;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public Connection(Socket socket, Server s){
        server = s;
        conn = socket;

        try {
            in = new DataInputStream(conn.getInputStream());
            out = new DataOutputStream(conn.getOutputStream());

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public void run(){
        while(true){
            try{
                String msg = in.readUTF();
                server.message(msg);
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public void send(String msg){
        try {
            out.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
