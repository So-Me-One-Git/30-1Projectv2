package project;
import java.util.ArrayList;
import java.util.Random;
public class tester {
	static myTracker map = new myTracker();
	public static final Random random = new Random();
	public static void main(String[] args) throws Exception {
		makeMap(10);
        System.out.println(map.printMap());
    }
	public static void makeRandomRoom(int biasUp, int biasDown, int biasLeft, int biasRight) {
		ArrayList<Integer> weightNumber = new ArrayList<>();
		for(int i = 0; i < biasUp; i ++) {
			weightNumber.add(0);
		}
		for(int i = 0; i < biasDown; i ++) {
			weightNumber.add(1);
		}
		for(int i = 0; i < biasLeft; i ++) {
			weightNumber.add(2);
		}
		for(int i = 0; i < biasRight; i ++) {
			weightNumber.add(3);
		} 
		int j = 1;
		for(int i = 0; i < j; i++) {
			int ranIndex = random.nextInt(weightNumber.size()); 
			int ranElement = weightNumber.get(ranIndex);
			if (map.checkStuck()) {
				findRandomRoom();
			}
			try {
				switch(ranElement) {
				case 0:
					map.goUp();
					break;
				case 1:
					map.goDown();
					break;
				case 2:
					map.goLeft();
					break;
				case 3:
					map.goRight();
					break;
				}
			}catch(Exception e) {
				j++;
			}
		}
	}
	public static void findRandomRoom() {
		int j = 1;
		for ( int i = 0; i < j; i++) {
			int ranX = random.nextInt(map.getMaxX());
			int ranY = random.nextInt(map.getMaxY());
			int currentX = map.getxPos();
			int currentY = map.getyPos();
			if(map.getElement(ranX, ranY) == 1 ) {
				map.setxPos(ranX);
				map.setyPos(ranY);
				if(map.checkStuck()) {
					map.setxPos(currentX);
					map.setyPos(currentY);
					j++;
				}
			}
		}
	}
	public static void makeMap(int size) {
		for(int i = 0; i < size; i ++) {
			int total = 20;
			int upBias = random.nextInt(total + 1);
			total -= upBias;
			int downBias = random.nextInt(total + 1);
			total -= downBias;
			int leftBias = random.nextInt(total + 1);
			total -= leftBias;
			int rightBias = total;
			int switchCases = random.nextInt(2); 
			switch(switchCases) {
			case 0:
				for(int j = 0; j < 4; j++) {
					makeRandomRoom(upBias, downBias, leftBias, rightBias);
				}
				break;
			case 1:
				makeRandomBranch();
			}

		}
	}
	public static void makeRoomsRecursively(int rooms) {
		if (rooms == 0) {
			return;
		}
		int number = random.nextInt(4); 
		try {
			switch(number) {
			case 0:
				map.goUp();
				makeRoomsRecursively(rooms-1);
				break;
			case 1:
				map.goDown();
				makeRoomsRecursively(rooms-1);
				break;
			case 2:
				map.goLeft();
				makeRoomsRecursively(rooms-1);
				break;
			case 3:
				map.goRight();
				makeRoomsRecursively(rooms-1);
				break;
			}
			
		}catch(Exception e){
			makeRoomsRecursively(rooms);
		}
	}
	public static void makeBranch(String direction, int rooms){
		int xPos = map.getxPos();
		int yPos = map.getyPos();
		int leftWeight = 1;
		int rightWeight = 1;
		int upWeight = 1;
		int downWeight = 1;
		switch(direction) {
		case "up":
			upWeight = 7;
			break;
		case "down":
			downWeight = 7;
			break;
		case "left":
			leftWeight = 7;
			break;
		case "right":
			rightWeight = 7;
			break;
	} for (int i = 0; i < rooms; i++) {
		makeRandomRoom(upWeight, downWeight, leftWeight, rightWeight);
	}
		
		map.setxPos(xPos);
		map.setyPos(yPos);
	}
	public static void makeRandomBranch() {
		int ranDirection = random.nextInt(4);
		int ranNumber = random.nextInt(4);
		switch(ranDirection) {
		case 0:
			makeBranch("up", ranNumber);
			break;
		case 1:
			makeBranch("down", ranNumber);
			break;
		case 2:
			makeBranch("left", ranNumber);
			break;
		case 3:
			makeBranch("right", ranNumber);
			break;
		
		}
	}
}
