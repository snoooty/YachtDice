import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;


public class myServerSocket {
	
	static ArrayList<Socket> users = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		int port = 6000;
		
		// 서버소켓을 생성합니다.
		ServerSocket serverSocket = new ServerSocket(port);  // 서버 자신의 포트를 설정합니다.
		RoomManager roomManager = new RoomManager();
		GameRoom gameRoom = new GameRoom();		
		
		
		System.out.println("접속대기중 입니다.");
		
		while(true) {
			Socket sock = serverSocket.accept(); // 클라이언트가 접속했을 때, 새로운 소켓을 생성합니다.
			UserInfo user = new UserInfo(sock);
			
			
			System.out.println("유저 소켓" + sock);			
			System.out.println("사용자가 접속했습니다.");
			System.out.println(sock.getInetAddress()+"의 "+sock.getPort()
			+ "포트에" + sock.getLocalAddress()+"의 "+sock.getLocalPort()
			+ "포트로 연결되었습니다.");
			System.out.println("접속시간 : " + LocalTime.now());
			
			users.add(sock); // 소켓 리스트에 추가
			
			System.out.println("main list.size()" + users.size());
			
						
			user.enterRoom(gameRoom);// 유저 방에입장
			gameRoom.enterUser(user);// 방에 유저 정보 설정
			
			
			if(roomManager.roomCount() == 0) {// 최초로 방을 방 리스트에 추가
				gameRoom.setRoomNum(sock.getPort() + sock.getLocalPort());// 방번호 설정
				System.out.println("방 번호 설정 : " + gameRoom.getRoomNum());
				roomManager.createRoom(gameRoom);// 방생성
				System.out.println("방 생성 후 roomCount : " + roomManager.roomCount());
			}else if(roomManager.roomList.get(roomManager.roomCount() -1).GetUserSize() == 2){// 최근 방에 유저 수가 최대일때 마다 방 리스트에 추가
 CX				gameRoom = new GameRoom();
//				gameRoom.setRoomNum(sock.getPort() + sock.getLocalPort());// 방번호 설정
//				System.out.println("방 번호 설정 : " + gameRoom.getRoomNum());
//				roomManager.createRoom(gameRoom);// 방생성
				System.out.println("방 생성 후 roomCount : " + roomManager.roomCount());
			}
			
			System.out.println("현재 방 번호 : " + gameRoom.getRoomNum());
			System.out.println("들어간 유저 정보 : " + gameRoom.userList.get(gameRoom.GetUserSize() - 1).getSock());
			System.out.println("방 생성 후 userCount : " + gameRoom.GetUserSize());
									
			for(int i = 0; i < roomManager.roomCount(); i++) {
				System.out.println(roomManager.roomList.get(i).getRoomNum() + "번방의 유저 수 : "
			+ roomManager.roomList.get(i).GetUserSize());
			}
			
			SendThread send = new SendThread(sock, users);
			send.start();
		}
	}
}