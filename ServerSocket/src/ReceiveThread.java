import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ReceiveThread extends Thread{
	
	private Socket sock;
	BufferedReader in = null;
	String s;
	
	public ReceiveThread(Socket sock) {
	    this.sock = sock;
	  }
	
	@Override
	public void run() {
		
		try {
			System.out.println(sock);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			while(in != null) {
				s = in.readLine();
				if(s == null) break;
				System.out.println("유저 : " + s);
			}
			System.out.println(sock + "Receive : 연결이 끊겼습니다.");
		} catch(SocketException e1) {
			System.out.println("Receive : 상대방 연결이 종료되었습니다.");
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}