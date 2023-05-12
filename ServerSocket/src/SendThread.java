import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class SendThread extends Thread{
	
	private Socket sock;
	String s;
	Scanner sc = new Scanner(System.in);
	
	public SendThread(Socket sock){
	    this.sock = sock;
	  }
	
	@Override
	public void run() {
		System.out.println(sock);
		try {
			PrintStream out = new PrintStream(sock.getOutputStream());
			while(true) {
				s = sc.nextLine();
				System.out.println("보내는 내용 : " + s);
				out.println(s);
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
