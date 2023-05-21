import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	
	List<GameRoom> roomList; // 방의 리스트
	
	public RoomManager() {
		roomList = new ArrayList<GameRoom>();
	}
	
	public void createRoom(GameRoom room) {
		roomList.add(room);
		System.out.println("방이 방 리스트에 들어감");
	}
	
	public void removeRoom(GameRoom room) {
		roomList.remove(room);
		System.out.println("방을 삭제되었습니다.");
	}
	
	public int roomCount() {
		return roomList.size();
	}
	
	public int roomUserSize() {
		return roomList.get(roomList.size() - 1).GetUserSize();
	}
}