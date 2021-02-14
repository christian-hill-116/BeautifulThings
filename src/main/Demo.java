package main;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeautifulThing;
import beans.BeautifulThings;
import database.DatabaseService;

public class Demo {

	public static void main(String[] args) throws SQLException {
		
		DatabaseService ds = new DatabaseService();
		
		BeautifulThing b = new BeautifulThing(0, "Kitties", "Youtube can keep you busy for hours with cute cats!", 8);
		
		ArrayList<BeautifulThing> bList = new ArrayList<BeautifulThing>();
		
		ds.insertOne(b);
		
		
		ds.deleteOne(4);
		
		bList = ds.readAll();
		
		System.out.println("========These are a few of my beautiful things=========");
		for (BeautifulThing bt : bList) {
			System.out.println("ID= " + bt.getId() + " Title= " + bt.getThingTitle() + " Desc= " + bt.getThingDescription() + " Rating= " + bt.getRating());
		}
		
		System.out.println("===========End of the list=========");
		
		b = new BeautifulThing(0, "freshly mowed grass", "Ready for spring time and a green carpet", 8);
		ds.updateOne(9, b);

	}

}
