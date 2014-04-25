package horsepower.Search;

import horsepower.Checkers.*;

public class MiniMaxSearcher {

	private int _depth = 0;
	private int _recurCount;
	
	public MiniMaxSearcher() {	}
	
	
	public Move minimaxDecision(Board board, int depth){
		
		_recurCount = 0;
		
		double vBest = Double.NEGATIVE_INFINITY;
		double v;
		Move bestAction = null;
		
		for (Move a : board.getActions()) {
			_depth = depth;
			v = minValue(board.result(a));
			if (v > vBest) {
				vBest = v;
				bestAction = a;
			}
		}
		System.out.println("Total recursive calls : "+_recurCount);
		return bestAction;
	}
	
	public double maxValue(Board board){
		if (board.isTerminal(_depth)) {
			return board.evaluateFor(board.getPlayer());
		} else {
			_depth--;
			_recurCount++;
			double v = Double.NEGATIVE_INFINITY;
			for (Move s : board.getActions()) {
				v = Math.max(v, minValue(board.result(s)));
			}
			//decrement depth by 1
			return v;	
		}
	}
	
	public double minValue(Board board){
		if (board.isTerminal(_depth)) {
			return board.evaluateFor(board.getPlayer());
		} else {
			_depth--;
			_recurCount++;
			double v = Double.POSITIVE_INFINITY;
			for (Move s : board.getActions()) {
				v = Math.min(v, maxValue(board.result(s)));
			}
			return v;	
		}
	}
	
}
