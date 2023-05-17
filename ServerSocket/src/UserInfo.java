import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class UserInfo{
	
	GameRoom room;
	BufferedReader in = null;
//	PrintWriter out = null;
	PrintStream out = null;
	Socket sock = null;
	String user_name = null;
	String sm;
	ArrayList<Socket> users = new ArrayList<Socket>();
//	Vector<Socket> users = new Vector<Socket>();
	
//	public void enterRoom(GameRoom room) {// 룸에 입장
//		room.enterRoom(this);
//		this.room = room;
//	}
	
		public UserInfo(Socket sock, ArrayList<Socket> users) {
			this.sock = sock;
			this.users = users;
			try {
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//				users.add(sock);
				System.out.println("users의 사이즈? : " + users.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		@Override
//		public void run() {
//			try {
//				for (int i = 0; i < users.size(); i++) {
//					out = new PrintStream(users.get(i).getOutputStream());
//					user_name = in.readLine();
//					System.out.println("유저이름 : " + user_name);
//					sm = "Server알림" + "//" + user_name + "님이 접속하였습니다.";
//					out.println(sm);
//					out.flush();
////					out.close();
//				}
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//		}
}
