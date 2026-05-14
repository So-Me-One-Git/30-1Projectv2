package project;
import java.util.ArrayList;
import java.util.Random;
public class tester {
	static myMap map = new myMap();
	public static final Random random = new Random();
	public static void main(String[] args) throws Exception {
		map.dynamicMap(11, 11);
		map.setStartPos(6, 6);
		map.makeFracture(4);
		System.out.println(map.printMap(map.dynamicMap));
		map.SecondLayer();
		System.out.println(map.printMap(map.dynamicMap));
		map.conjoin(6, 6);
		System.out.println(map.printMap(map.dynamicMap));
		map.reconnect();
		System.out.println(map.printMap(map.dynamicMap));
		//test;
	}
}
