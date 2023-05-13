import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class SendThread extends Thread{
	
	BufferedReader in = null;
	private Socket sock;
	String sm,rm;
	String user_name;
	String[] splitMS;
	Scanner sc = new Scanner(System.in);
	
	public SendThread(Socket sock){
	    this.sock = sock;
	  }
	
	@Override
	public void run() {
		System.out.println(sock);
		try {
			PrintStream out = new PrintStream(sock.getOutputStream());
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			while(true) {
				if(in != null) {
					rm = in.readLine();// 메세지 받아오고
					System.out.println("받은 메세지 : " + rm);
					if(rm == null) break;// 빈값이면 while 나가라
					splitMS = rm.split("//");//받은 메세지 자르고
					user_name = splitMS[0];// 자르고 나온 유저의 이름
					sm = splitMS[1];// 자르고 나온 유저의 메세지					
				}
				
				System.out.println("유저이름 : " + user_name);
				System.out.println("보내는 내용 : " + sm);
				out.println(user_name + "//" + sm);
				out.flush();
//				out.close();
			}
		}catch(SocketException e2) {
			System.out.println("Send : 상대방 연결이 종료되었습니다.");
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
