import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;


public class myServerSocket {
	
	PrintWriter out = null;
	
	public myServerSocket(PrintWriter out) {
		this.out = out;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		myServerSocket mss = new myServerSocket(null);
		
		int port = 6000;
		
		// 서버소켓을 생성합니다.
		ServerSocket ssk = new ServerSocket(port);  // 서버 자신의 포트를 설정합니다.
		
		System.out.println("접속대기중 입니다.");
		
		while(true) {
			Socket sock = ssk.accept(); // 클라이언트가 접속했을 때, 새로운 소켓을 생성합니다.
			System.out.println("유저 소켓" + sock);
			
			System.out.println("사용자가 접속했습니다.");
			System.out.println("클라이언트의 주소 : " + sock.getInetAddress());
			System.out.println("접속시간 : " + LocalTime.now());
			
			UserInfo userinfo = new UserInfo(sock);
			userinfo.start();
			try {
				Thread.sleep(10L);
				userinfo.interrupt();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
			ReceiveThread receive = new ReceiveThread(sock);
			receive.start();
			
			SendThread send = new SendThread(sock);
//			send.start();
		}

	}

}