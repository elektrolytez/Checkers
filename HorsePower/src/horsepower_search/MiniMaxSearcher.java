package horsepower_search;


import java.util.Collections;
import java.util.List;
import java.util.Random;

import horsepower_checkers.*;


/*
 * MiniMax adversarial search algorithm with alpha-beta pruning
 */
public class MiniMaxSearcher {

	private int _recurCount;
	private int _totalRecursion = 0;
	
	public MiniMaxSearcher() {	}
	
	
	public Move minimaxDecision(Board board, int depth){
		double vBest = //Double.NEGATIVE_INFINITY;
				 (double) Integer.MIN_VALUE;
		double v = 0.0;
		Move bestAction = null;
		
		int tempCount = 0;
		for (Move a : board.getActions()) {
			
			
			_recurCount = 0;
			//v = maxValue(board.result(a), depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
			v = maxValue(board.result(a), depth, (double) Integer.MIN_VALUE, (double) Integer.MAX_VALUE);
			if((v == -50000 || v == 50000) && depth > 9) {vBest = v; bestAction = a; break;}
			if (v > vBest) {
				vBest = v;
				bestAction = a;
			}
			tempCount = tempCount+_recurCount;
		}
		System.out.println("Total recursive calls : "+tempCount);
		System.out.println("V:"+v);
		System.out.println("Vbest"+vBest);
		System.out.println("OPK:" +board.get_oppKingCount() + " OPR:" + board.get_oppRegCount());
		//System.out.println(Double.MIN_VALUE);
		//System.out.println(Integer.MIN_VALUE);
		System.out.println("MINIMAX VALUE CHOSEN : "+vBest);
		return bestAction;
	}

	/*
	 * ALPHA = the value of the best alternative for MAX along the path to 'board'
	 * BETA = the value of the best alternative for MIN along the path to 'board'
	 */
	public double maxValue(Board board, int depth, double ALPHA, double BETA){
		if (board.isTerminal(depth)) {
			return board.evaluateFor(board.getPlayer());
		} else {
			_recurCount++;
			_totalRecursion++;
			//double v = Double.NEGATIVE_INFINITY;
			double v = (double) Integer.MIN_VALUE;
			
			List<Move> moveList = board.getActions();
			long seed = System.nanoTime();
			Collections.shuffle(moveList, new Random(seed));
			
			for (Move s : moveList) { //board.getActions()
				v = Math.max(v, minValue(board.result(s), depth - 1, ALPHA, BETA));
				if(v == 50000 && depth > 9) return v;
				if (v >= BETA) {
					return v;
				}
				ALPHA = Math.max(ALPHA, v);
				//if(ALPHA < v) ALPHA = v;
				//else ALPHA = ALPHA;
			}
			return v;	
		}
	}
	
	public double minValue(Board board, int depth, double ALPHA, double BETA){
		if (board.isTerminal(depth)) {
			return board.evaluateFor(board.getPlayer());
		} else {
			_recurCount++;
			_totalRecursion++;
			//double v = Double.POSITIVE_INFINITY;
			double v = (double) Integer.MAX_VALUE;
			List<Move> moveList = board.getActions();
			long seed = System.nanoTime();
			Collections.shuffle(moveList, new Random(seed));
			
			for (Move s : moveList ) { //board.getActions()
				v = Math.min(v, maxValue(board.result(s), depth - 1, ALPHA, BETA));
				if(v == -50000 && depth > 9) return v;
				if (v <= ALPHA) {
					return v;
				}
				BETA = Math.min(BETA, v);
				//if(BETA > v) BETA = v;
				
				
			}
			return v;	
		}
	}
	
	public int getFinalRecursion() {
		return _totalRecursion;
	}
}
