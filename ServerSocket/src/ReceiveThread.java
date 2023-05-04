import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ReceiveThread extends Thread{
	
	private Socket sock;
	
	public ReceiveThread(Socket sock) {
	    this.sock = sock;
	  }
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String s;
			while(true) {
				s = in.readLine();
				System.out.println("유저 : " + s);
			}
		} catch(SocketException e1) {
			System.out.println("Receive : 상대방 연결이 종료되었습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
