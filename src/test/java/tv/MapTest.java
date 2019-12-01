package tv;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import beans.Apartment;
import business.map.Map;

public class MapTest {
	
	private Map map;
	
	@BeforeClass
	public void Map() {
		HashMap<Integer, ArrayList<Apartment>> hashedMap = new HashMap<Integer, ArrayList<Apartment>>();
		for(int i = 0; i<2 ; i++) {
			ArrayList<Apartment> level = new ArrayList<Apartment>();
			for(int j = 1; j<=5;j++) {
				level.add(new Apartment());
			}
			hashedMap.put(i, level);
		}
		this.map = new Map(hashedMap);
	}
	
	@Test
	public void test() {
		
	}

}
