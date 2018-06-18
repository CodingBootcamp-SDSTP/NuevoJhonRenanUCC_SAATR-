public class Room
{
	private final int ROOMID;
	private String buildingName;
	private String room;
	private String assignee;

	public Room(int id,String bn,String rm,String a) {
		ROOMID=id;
		buildingName=bn;
		room=rm;
		assignee=a;
	}

	public Room(String bn,String rm,String a) {
		buildingName=bn;
		room=rm;
		assignee=a;
		ROOMID=0;
	}

	public void setBuildingName(String bn) {
		buildingName=bn;
	}

	public void setRoom(String rm) {
		room=rm;
	}

	public void setAssignee(String a) {
		assignee=a;
	}

	public int getId() {
		return ROOMID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getRoom() {
		return room;
	}

	public String getAssignee() {
		return assignee;
	}
}
