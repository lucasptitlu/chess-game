package model;
/**
 * @author lucas/loïc
 * pièce King
 *
 */
public class King extends AbstractPiece {
	/**
	 * @param couleur_de_piece
	 * @param coord
	 * 
	 */
	public King(Color couleur_de_piece,Coord coord){
		super(couleur_de_piece,coord);
		this.name="King";

	}

	public boolean isMoveOk(int xFinal,int yFinal){
		boolean ret=false;
		if(isMoveOnChest(xFinal,yFinal)){
			if((xFinal==getX()+1 || xFinal==getX()-1 || xFinal==getX()) && ( yFinal==getY() || yFinal==getY()+1 || yFinal==getY()-1) ){
				ret= true;
			}
		}
		return ret;
	}

}