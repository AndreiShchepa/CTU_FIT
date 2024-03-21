package cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype;

import cz.cvut.fit.niadp.mvcgame.model.Vector;

public class Position implements Prototype {
    private int dimX;
	private int dimY;

	public Position(int posX, int posY) {
		this.dimX = posX;
		this.dimY = posY;
	}
	private Position(Position source) {
		this.dimX = source.dimX;
		this.dimY = source.dimY;
	}

	@Override
	public Position myclone() {
		return new Position(this);
	}

	public int getX() {
		return dimX;
	}
    
    public int getY() {
		return dimY;
	}
    
    public void setY(int y) {
		this.dimY = y;
	}
    
    public void setX(int x) {
		this.dimX = x;
	}

	public void add( Vector vector ) {
		this.setX( this.getX( ) + vector.getDX( ) );
		this.setY( this.getY( ) + vector.getDY( ) );
	}
}