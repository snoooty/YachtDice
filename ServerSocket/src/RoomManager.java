import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	
	List<GameRoom> roomList; // 방의 리스트
	
	public RoomManager() {
		roomList = new ArrayList<GameRoom>();
	}
	
	public GameRoom createRoom() {
		GameRoom room = new GameRoom();
		roomList.add(room);
		System.out.println("방이 생겼습니다.");
		return room;
	}
	
	public void removeRoom(GameRoom room) {
		roomList.remove(room);
		System.out.println("방을 삭제되었습니다.");
	}
	
	public int roomCount() {
		return roomList.size();
	}

}
