import java.util.List;


public class Main {
	
	public static void main(String args[])
	{
	
	Board _board = new Board();
	System.out.println(_board.getBlackCheckers());
	System.out.println(_board.toString());
	System.out.println("Move List: for whites");
	List<Move> move = _board.getPossibleMoves(false);
	
	_board.printMoveArray(move);
	System.out.println("Move List End");
	System.out.println("Move List: for blacks");
	 move = _board.getPossibleMoves(true);
	_board.printMoveArray(move);
	System.out.println("Move List End");
	
	
	System.out.println("\nDifferent Board");
	int[] test = new int[36];
	for(int i = 35; i > 27; i--)test[i] = 1;
	for(int i = 26; i > 22; i--)test[i] = 1;
	for(int i = 22; i > 18; i--)test[i] = -1;
	for(int i = 17; i > 13; i--)test[i] = -1;
	for(int i = 13; i > 9; i--)test[i] = 2; 
	for(int i = 8; i > 0; i--)test[i] = 2;
	 test[0] = -3;
     test[9] = -3;
    test[18] = -3;
    test[27] = -3;
	test[26] = -1;//moved the checker 
	test[21] = 1; //new bchecker spot
	test[13] = -1; //former wchecker spot
	test[17] = 2; //move checker to there
	_board = new Board(test);
	System.out.println(_board.toString());
	System.out.println("Move List: for whites");
	move = _board.getPossibleMoves(false);
	_board.printMoveArray(move);
	System.out.println("As you can see since white has no jumps, white has many available moves");
	System.out.println("Move List: for blacks");
	move = _board.getPossibleMoves(true);
	_board.printMoveArray(move);
	System.out.println("As you can see since black has a jump, it has to take it and only displays that move");
	
	
	//altering the board further with kings to display double jumps and king jumps
	for(int i = 35; i > 27; i--)test[i] = -1;
	for(int i = 26; i > 22; i--)test[i] = -1;
	for(int i = 22; i > 18; i--)test[i] = -1;
	for(int i = 17; i > 13; i--)test[i] = -1;
	for(int i = 13; i > 9; i--)test[i] = -1; 
	for(int i = 8; i > 0; i--)test[i] = -1;
	test[24] = 4; //white king
	test[19] = 1;
	test[20] = 3; //black king (opposite direction jump for black)
	test[12] = 1; //reg black (double jump for white) 
	System.out.println("\nDifferent Board");
	_board = new Board(test);
	System.out.println(_board.toString());
	System.out.println("Move List: for whites");
	move = _board.getPossibleMoves(false);
	_board.printMoveArray(move);
	System.out.println("White King double jumps to (1:1)");
	System.out.println("Move List: for blacks");
	move = _board.getPossibleMoves(true);
	_board.printMoveArray(move);
	System.out.println("Black king jumps opposite direction and normal checker cannot");

	System.out.println("The new board is: (If black makes the move");
	
	System.out.println(_board.result(move.get(0)).toString());
	
	System.out.println("The new board is: (If white makes the move");
	move = _board.getPossibleMoves(false);
	System.out.println(_board.result(move.get(0)).toString());
	
	}
}
