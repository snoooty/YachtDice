import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class SendThread extends Thread{
	
	BufferedReader in = null;
	PrintStream out = null;
	private Socket sock;
	String sm,rm;
	String user_name;
	String[] splitMS;
	Scanner sc = new Scanner(System.in);
	ArrayList<Socket> users = new ArrayList<Socket>();
	RoomManager roomManager = new RoomManager();
//	Vector<Socket> users = new Vector<Socket>();
	
	public SendThread(Socket sock, ArrayList<Socket> users, RoomManager roomManager){
	    this.sock = sock;
	    this.users = users;
	    this.roomManager = roomManager;
//	    users.add(sock);
	  }
	
	@Override
	public void run() {
		System.out.println("통신 시작할때 sock : " + sock);
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			user_name = in.readLine();
//			for (int i = 0; i < users.size(); i++) {
				System.out.println("roomManager.roomList.size()" + roomManager.roomList.size());
				for(int l = 0; l < roomManager.roomList.size(); l++){
					System.out.println("roomManager.roomList.userList.size()" + roomManager.roomList.get(l).userList.size());
					for(int j = 0; j < roomManager.roomList.get(l).userList.size(); j++) {
						if(roomManager.roomList.get(l).userList.get(j).getSock().equals(sock)) {
							int roomNum = roomManager.roomList.get(l).getRoomNum();
							System.out.println("roomNum : " + roomNum);
							for(int k = 0; k < roomManager.roomList.get(l).GetUserSize(); k++) {
								System.out.println("l : " + l);
								System.out.println("k : " + k);
								System.out.println("sock : " + roomManager.roomList.get(l).userList.get(k).getSock());
								out = new PrintStream(roomManager.roomList.get(l).userList.get(k).getSock().getOutputStream());
								System.out.println("유저이름 : " + user_name);
								sm = "Server알림" + "//" + user_name + "님이 접속하였습니다.";
								out.println(sm);
								out.flush();
							}
						}
					}				
				}				
//			}
			System.out.println("유저입장알림 완료");
			while(true) {
				System.out.println("메세지 주고 받기 시작");
				rm = in.readLine();// 메세지 받아오고
				if(rm == null) break;// 빈값이면 while 나가라
				if(rm != null) {
//					for(int i = 0; i < users.size(); i++) {
						System.out.println("보내기전에 users.size : " + users.size());
						System.out.println("roomManager.roomList.size()" + roomManager.roomList.size());
						for(int l = 0; l < roomManager.roomList.size(); l++) {
							System.out.println("roomManager.roomList.userList.size()" + roomManager.roomList.get(l).userList.size());
							for(int j = 0; j < roomManager.roomList.get(l).userList.size(); j++) {
								if(roomManager.roomList.get(l).userList.get(j).getSock().equals(sock)) {
									int roomNum = roomManager.roomList.get(l).getRoomNum();
									System.out.println("roomNum : " + roomNum);
									for(int k = 0; k < roomManager.roomList.get(l).GetUserSize(); k++) {
										System.out.println("l : " + l);
										System.out.println("k : " + k);
										out = new PrintStream(roomManager.roomList.get(l).userList.get(k).getSock().getOutputStream());
										if(in != null) {
											System.out.println("받은 메세지 : " + rm);
//											if(rm == null) break;
											splitMS = rm.split("//");//받은 메세지 자르고
											user_name = splitMS[0];// 자르고 나온 유저의 이름
											sm = splitMS[1];// 자르고 나온 유저의 메세지
											System.out.println("보내는 유저이름 : " + user_name);
											System.out.println("보내는 내용 : " + sm);
											out.println(user_name + "//" + sm);
											out.flush();
//											out.close();
										}
									}
								}
							}
						}												
					}
				}
//			}
			
		}catch(SocketException e2) {
			System.out.println("Send : 상대방 연결이 종료되었습니다.");
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("대화 끝?");
		users.remove(sock);
	}
}
