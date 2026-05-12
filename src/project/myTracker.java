package project;

public class myTracker {
	private int xPos = 0;
	private int yPos = 0;
	int[][] dynamicMap;
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
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				mapString.append(map[i][j]).append(" ");
			}
			mapString.append("\n");
		} 
		return mapString.toString();
	}
}
