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
		int ranIndex = random.nextInt(weightNumber.size()); 
		int ranElement = weightNumber.get(ranIndex);

		
		try {
			if (map.checkStuck()) {
				map.setxPos(5);
				map.setyPos(5);
				makeRandomRoom(biasUp,biasDown,biasLeft,biasRight);
			}
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
			makeRandomRoom(biasUp,biasDown,biasLeft,biasRight);
		}
	}
	public static void makeMap(int size) {
		for(int i = 0; i < size; i ++) {
			int totalValue = 40;
			int number = random.nextInt(15); 
			int switchCases = random.nextInt(2); 
			int upBias = random.nextInt(); 
			int downBias = random.nextInt(10); 
			int leftBias = random.nextInt(10); 
			int rightBias = random.nextInt(10); 
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
	}
		makeRandomRoom(upWeight, downWeight, leftWeight, rightWeight);
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
		case 3:
			makeBranch("right", ranNumber);
		
		}
	}
}
