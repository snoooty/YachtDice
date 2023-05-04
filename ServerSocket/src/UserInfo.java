import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UserInfo extends Thread{
	
	BufferedReader in = null;
//	PrintWriter out = null;
	PrintStream out = null;
	Socket sock = null;
	String user_name = null;
	
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
		
		
//		public String user_name() {
//			try {
//				return 
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return user_name;
//		}
//		
		@Override
		public void run() {
			try {
				user_name = in.readLine();
				System.out.println("유저이름 : " + user_name);
				out.println(user_name);
				out.append("님이 접속하였습니다.");
				out.append("이게왜 대체 null인데?");
				out.flush();
				out.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
}
