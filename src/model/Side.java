package model;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Side {
	private Shape shape;
	
	public Side(boolean left) {
		if(left) {
			shape = new Rectangle(0,0, 2, 480);
		}
		else {
			shape = new Rectangle(636,0, 2, 480);
		}
	}
	
	public Shape getShape() {
		return shape;
	}
}
