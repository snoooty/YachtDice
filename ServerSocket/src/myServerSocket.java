import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class myServerSocket {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int port = 6000;
		
		// 서버소켓을 생성합니다.
		ServerSocket ssk = new ServerSocket(port);  // 서버 자신의 포트를 설정합니다.
		
		System.out.println("접속대기중 입니다.");
		
		while(true) {
			Socket sock = ssk.accept(); // 클라이언트가 접속했을 때, 새로운 소켓을 생성합니다.
			
			System.out.println("사용자가 접속했습니다.");
			System.out.println("클라이언트의 주소" + sock.getInetAddress());
		}

	}

}