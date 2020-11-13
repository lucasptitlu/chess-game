package controler;

import vue.ChessGameGUI;

import java.util.ArrayList;
import java.util.List;

import model.Coord;
import model.Couleur;
import model.observable.ChessGame;


/**
 * 
 * @author francoise.perrin
 * 
 * <p>
 * the controller represent the DP Strategy regarding the view
 * 
 * <p>
 * common methods for the controller which consist in establish communication between the view and the model
 * 
 * 
 *
 */
public abstract class AbstractChessGameControler implements ChessGameControlers {

	protected ChessGame chessGame;	 

	/**
	 * constructor
	 * <p>uiiiiiiiiiioii
	 * @param chessGame
	 */
	public AbstractChessGameControler(ChessGame chessGame) {
		super();
		this.chessGame = chessGame;	 
	}

	/**
	 * methose abstraite qui fait le lien le deplacement vue et le deplacement model
	 * <p>
	 * @param initCoord
	 * @param finalCoord
	 * <p>
	 * @return boolean if move done
	 */

	final public boolean move(Coord initCoord, Coord finalCoord) {
		boolean ret = false;
		String promotionType = null; 
		initCoord=conversionCoord(initCoord);
		finalCoord=conversionCoord(finalCoord);

		// if is the current player turn
		//if (this.isPlayerOK(initCoord)) {
		// job move
		ret = this.moveModel(initCoord, finalCoord);	 

		// variable actions regarding the controller type
		if (ret) {	

			this.endMove(initCoord, finalCoord, promotionType);
		}

		//}
		return ret;
	}

	/**
	 * abstract method for verify the current player
	 */
	public abstract boolean isPlayerOK(Coord initCoord) ;


	/**
	 * do a move in the model
	 * <p>
	 * @param initCoord
	 * @param finalCoord
	 * <p>
	 * @return a chessGame move 
	 */


	protected  boolean moveModel(Coord initCoord, Coord finalCoord)  {	
		return chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);	
	}

	/**
	 * end a move in the model
	 * <p>
	 * @param initCoord
	 * @param finalCoord
	 */
	protected abstract void endMove(Coord initCoord, Coord finalCoord, String promotionType) ;

	/**
	 * verify is the dame in finished
	 */
	public boolean isEnd(){
		return this.chessGame.isEnd();		
	}

	/**
	 * display error message method
	 */
	public String getMessage() {
		String ret = "erreur";		 
		ret = this.chessGame.getMessage();	 
		return ret;
	}

	/**
	 * overriding of toString method
	 */
	public String toString() {
		return this.chessGame.toString();
	}

	/**
	 * get the color of the current player
	 * <p>
	 * @return the color of the current player
	 */
	protected Couleur getColorCurrentPlayer(){		
		return this.chessGame.getColorCurrentPlayer();		
	}	

	/**
	 * get the color of the piece specify by the coordinates in parameters
	 * <p>
	 * @param initCoord
	 * <p>
	 * @return a color of the piece from the chessGame
	 */
	protected Couleur getPieceColor(Coord initCoord){		
		return this.chessGame.getPieceColor(initCoord.x, initCoord.y);		
	}	

	/**
	 * convert graphic coordinates to model coordinates
	 * <p>
	 * @param initCoord
	 * <p>
	 * @return coordinates between 0 to 7 instead of 0 to approximatively 850
	 */
	protected Coord conversionCoord(Coord initCoord){
		double coordx;
		double coordy;
		coordx=(double) initCoord.x / (ChessGameGUI.getDim().width /8);
		coordy=(double) initCoord.y / (ChessGameGUI.getDim().height /8);
		return new Coord((int) coordx,(int) coordy);
	}

	public List<Coord> whereCanYouGo(int x,int y){
		Coord initCoord=conversionCoord(new Coord(x,y));
		List<Coord> tabCoord=new ArrayList<Coord>();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(this.chessGame.getEchiquier().isMoveOk(initCoord.x,initCoord.y, i, j)){
					tabCoord.add(new Coord(i,j));
				}
			}
		}

		return tabCoord;

	}

}
