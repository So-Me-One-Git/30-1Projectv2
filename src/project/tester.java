package project;
import java.util.ArrayList;
import java.util.Random;
public class tester {
	static myTracker map = new myTracker();
	public static final Random random = new Random();
	public static void main(String[] args) throws Exception {
		makeRandomBranch(4,9);
        System.out.println(map.printMap());
    }
	public static void makeRandomRoom(int biasUp, int biasDown, int biasLeft, int biasRight) {
		ArrayList<String> directionBias = new ArrayList<>();
		for(int i = 0; i < biasUp; i ++) {
			directionBias.add("up");
		}
		for(int i = 0; i < biasDown; i ++) {
			directionBias.add("down");
		}
		for(int i = 0; i < biasLeft; i ++) {
			directionBias.add("left");
		}
		for(int i = 0; i < biasRight; i ++) {
			directionBias.add("right");
		} 
		String ranDirection = directionBias.get(random.nextInt(directionBias.size()));
		try {
			switch (ranDirection){
			case"up":
				map.goUp();
				break;
			case"down":
				map.goDown();
				break;
			case"left":
				map.goLeft();
				break;
			case"right":
				map.goRight();
				break;
			}
			return;
		}catch(OverlapException e) {
			if(map.checkStuck()) {
				findRandomRoom();
				makeRandomRoom(biasUp, biasDown, biasLeft, biasRight);
			}else {
				makeRandomRoom(biasUp, biasDown, biasLeft, biasRight);
			}
		}
	}

	public static void findRandomRoom() {
		int ranX = random.nextInt(map.getMaxX());
		int ranY = random.nextInt(map.getMaxY());
		int currentX = map.getxPos();
		int currentY = map.getyPos();
		if(map.getElement(ranX, ranY) == 1) {
			map.setxPos(ranX);
			map.setyPos(ranY);
			if(map.checkStuck()) {
				map.setxPos(currentX);
				map.setyPos(currentY);
				findRandomRoom();
				return;
			}else {
				map.setElement(ranX, ranY, 4);
			}
		}else {
			findRandomRoom();
			return;
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
				makeRandomBranch(4,9);
			}

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
	public static void makeRandomBranch(int min, int max) {
		int ranDirection = random.nextInt(4);
		int ranNumber = random.nextInt(min, max + 1);
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
