import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UserInfo extends Thread{
	
	GameRoom room;
	BufferedReader in = null;
//	PrintWriter out = null;
	PrintStream out = null;
	Socket sock = null;
	String user_name = null;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String user_name) {// name 가지고 생성
		this.user_name = user_name;
	}
	
	public void enterRoom(GameRoom room) {// 룸에 입장
		room.enterRoom(this);
		this.room = room;
	}
	
		public UserInfo(Socket sock) {
			this.sock = sock;
			try {
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out = new PrintStream(sock.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				user_name = in.readLine();
				System.out.println("유저이름 : " + user_name);
				out.println(user_name + "님이 접속하였습니다.");
				out.flush();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
}
