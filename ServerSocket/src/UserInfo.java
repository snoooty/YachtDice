
import java.net.Socket;

public class UserInfo{
	
	GameRoom gameRoom;
	
	Socket sock = null;
	String user_name = "지정해주세요";
	int roomNum;
	
		public UserInfo(Socket sock) {
			this.sock = sock;
		}
		
		public Socket getSock() {
			return sock;
		}
		
		public String getUser_name() {
			return user_name;
		}
		
		public void enterRoom(GameRoom room) {
			this.gameRoom = room;
		}
		
		public int getRoomNum() {
			return gameRoom.getRoomNum();
		}
}