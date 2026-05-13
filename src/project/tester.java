package project;
import java.util.ArrayList;
import java.util.Random;
public class tester {
	static myMap map = new myMap();
	public static final Random random = new Random();
	public static void main(String[] args) throws Exception {
		map.dynamicMap(21, 21);
		map.setStartPos(10, 10);
		map.makeFracture(15);
		System.out.println(map.printMap(map.dynamicMap));
	}
}
