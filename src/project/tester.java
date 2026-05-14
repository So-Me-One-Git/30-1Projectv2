package project;
import java.util.ArrayList;
import java.util.Random;
public class tester {
	static myMap map = new myMap();
	public static final Random random = new Random();
	public static void main(String[] args) throws Exception {
		map.dynamicMap(21, 21);
		map.setStartPos(11, 11);
		map.makeFracture(10);
		map.SecondLayer();
		System.out.println(map.printMap(map.dynamicMap));
		//test;
	}
}
