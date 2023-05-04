import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class SendThread extends Thread{
	
	private Socket sock;
	
	public SendThread(Socket sock){
	    this.sock = sock;
	  }
	
	@Override
	public void run() {
		try {
			PrintStream out = new PrintStream(sock.getOutputStream());
			String s;
			while(true) {
				out.println("보내는 메세지");
				out.flush();
			}
		}catch(SocketException e2) {
			System.out.println("Send : 상대방 연결이 종료되었습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
