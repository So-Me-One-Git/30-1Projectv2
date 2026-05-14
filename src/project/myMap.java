package project;
import java.util.Random;
import java.util.ArrayList;

public class myMap {
	private int xPos = 0;
	private int yPos = 0;
	private static final Random random = new Random();
	int[][] dynamicMap;
	boolean[][] visited;
	public void setStartPos(int x, int y) {
		dynamicMap[yPos][xPos] = 0;
		this.xPos = x;
		this.yPos = y;
		dynamicMap[yPos][xPos] = 3;
	}
	public void dynamicMap(int x, int y) {
		this.dynamicMap = new int[y][x];
		dynamicMap[yPos][xPos] = 3;
	}
	public void setxPos(int xPos) {
		this.xPos=xPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getxPos() {
		return this.xPos;
	}
	public int getyPos() {
		return this.yPos;
	}
	public int getMaxX(){
		return dynamicMap[0].length;
	}
	public int getMaxY() {
		return dynamicMap.length;
	}
	public int getElement(int x,int y) {
		return dynamicMap[y][x];
	}
	public void setElement(int x, int y, int value) {
		dynamicMap[y][x] = value;
	}
	public void goUp() throws OverlapException {
		if(!checkOverlap("up")) {
			dynamicMap[yPos-1][xPos]++;
			yPos--;
		}else {
			throw new OverlapException("Overlap");
		}
	}

	public void goDown() throws OverlapException {
		if(!checkOverlap("down")) {
			dynamicMap[yPos+1][xPos]++;
			yPos++;
		}else {
			throw new OverlapException("Overlap");
		}
	}
	public void goRight() throws OverlapException {
		if(!checkOverlap("right")) {
			dynamicMap[yPos][xPos+1]++;
			xPos++;
		}else {
			throw new OverlapException("Overlap");
		}
	}
	public void goLeft() throws OverlapException {
		if(!checkOverlap("left")) {
			dynamicMap[yPos][xPos-1]++;
			xPos--;
		}else {
			throw new OverlapException("Overlap");
		}
	}
	public boolean checkOverlap(String direction){
		int futureX = xPos;
		int futureY = yPos;
		switch(direction) {
		case "up":
			futureY--;
			break;
		case "down":
			futureY++;
			break;
		case "left":
			futureX--;
			break;
		case "right":
			futureX++;
			break;
		}
		if(checkInBounds(futureY, futureX) && dynamicMap[futureY][futureX] == 0) {
			return false;// FALSE MEANS THAT THERE IS NO OVERLAP
		}else{
			return true;
		}
	}
	public boolean checkStuck() {
		if (checkOverlap("down") && checkOverlap("up") && checkOverlap("left") && checkOverlap("right")) {	
			return true;
		}else {
			return false;
		}

	}
	public boolean checkInBounds(int y, int x) {
		if(y >= 0 && y < dynamicMap.length && x >= 0 && x < dynamicMap[y].length) {
			return true;
		}else {
			return false;
		}
	}
	public String printMap(int[][] map) {
		StringBuilder mapString = new StringBuilder();
		for(int i = 0; i < getMaxY(); i++) {
			for(int j = 0; j < getMaxX(); j++) {
				mapString.append(map[i][j]).append(" ");
			}
			mapString.append("\n");
		} 
		return mapString.toString();
	}
	public void makeRandomRoom(int biasUp, int biasDown, int biasLeft, int biasRight) {
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
				goUp();
				break;
			case"down":
				goDown();
				break;
			case"left":
				goLeft();
				break;
			case"right":
				goRight();
				break;
			}
			return;
		}catch(OverlapException e) {
			if(checkStuck()) {
				findRandomRoom();
				makeRandomRoom(biasUp, biasDown, biasLeft, biasRight);
			}else {
				makeRandomRoom(biasUp, biasDown, biasLeft, biasRight);
				return;
			}
		}
	}

	public void findRandomRoom() {
		int ranX = random.nextInt(getMaxX());
		int ranY = random.nextInt(getMaxY());
		int currentX = getxPos();
		int currentY = getyPos();
		if(getElement(ranX, ranY) == 1 || getElement(ranX, ranY) == 3) {
			setxPos(ranX);
			setyPos(ranY);
			if(checkStuck()) {
				setxPos(currentX);
				setyPos(currentY);
				findRandomRoom();
				return;
			}else {
				setElement(ranX, ranY, 4);
			}
		}else {
			findRandomRoom();
			return;
		}
	}
	public void makeMap() {
	}
	public void SecondLayer() {
        for(int y = 0; y < getMaxY(); y++) {
            for(int x = 0; x < getMaxX(); x++) {
                int adjacentRooms = 0;
                if (getElement(x,y) == 1) {
                    adjacentRooms++;
                        if (x < getMaxX() - 1 && getElement(x + 1, y) == 1){
                            adjacentRooms++;
                        }
                        if (x > 0 && (getElement(x - 1,y) == 1)){
                            adjacentRooms++;
                        }
                        if ((y < getMaxX() - 1) && getElement(x, y + 1) == 1){
                            adjacentRooms++;
                        }
                        if ((y > 0) && (getElement(x,y - 1) == 1)){
                            adjacentRooms++;
                        }
                        if (adjacentRooms == 3 && random.nextInt(4) == 0){
                            setElement(x,y,0);
                        }
                        if (adjacentRooms == 4 && random.nextInt(2) == 0){
                        	setElement(x,y,0);
                        }

                    }
                }
            }
        }
	public boolean conjoined(int x, int y) {
		if (getElement(x-1, y) == 3 || getElement(x+1,y) == 3 || getElement(x,y-1) == 3 || getElement(x,y+1) == 3) {
			return true;
		}
		if(conjoined(x-1,y) || conjoined(x+1,y) || conjoined(x,y-1) || conjoined(x,y+1)) {
			return true;
		}
		if (visited[y][x]) {
	        return false;
	    }

	    // Mark as visited
	    visited[y][x] = true;
		return false;
	}
	
	public void makeRandomBranch(){
		int leftWeight = 1;
		int rightWeight = 1;
		int upWeight = 1;
		int downWeight = 1;
		int currentDirection = random.nextInt(4);
		int diagonal = random.nextInt(2);
		int size = random.nextInt(5,15);
		switch(diagonal) {
		case 0:
			switch(currentDirection) {
			case 0:
				upWeight = 15;
				break;
			case 1:
				downWeight = 15;
				break;
			case 2:
				leftWeight = 15;
				break;
			case 3:
				rightWeight = 10;
				break;
			}
		case 2:
			switch(currentDirection) {
			case 0:
				upWeight = 15;
				rightWeight = 15;
				break;
			case 1:
				upWeight = 15;
				leftWeight = 15;
				break;
			case 2:
				downWeight = 15;
				rightWeight = 15;
				break;
			case 3:
				downWeight = 15;
				leftWeight = 15;
				break;
			}
		} for (int i = 0; i < size; i++) {
			makeRandomRoom(upWeight, downWeight, leftWeight, rightWeight);
		}

	}
	public void makeFracture(int size) {
		makeRandomBranch();
		for (int i = 0; i < size; i++) {
			findRandomRoom();
			makeRandomBranch();
		}
		setElement(10, 10, 3);
	}
}
