package backEnd;

public class minMax {
	int levels;
	private Node Maximize(Node state) {
		
	}
	private Node minimize(Node state) {
		
	}
	private Node MaximizeWithPruning(Node state,int alpha,int beta) {
		
	}
	private Node MinimizeWithPruning(Node state,int alpha,int beta) {
		
	}
	
	
	
	
	
	
	
	
	public int MakeDecesion(int initialState[][],int kLevels,int humanScore ,int aiScore) {
		Node initial= new Node(initialState,0,humanScore,aiScore);
		levels=kLevels;
		Node max=Maximize(initial);
		return max.getColNum();
	}
	public int MakeDecesionWithPruning(int initialState[][],int kLevels,int humanScore ,int aiScore) {
		Node initial= new Node(initialState,0,humanScore,aiScore,Integer.MIN_VALUE,Integer.MAX_VALUE);
		levels=kLevels;
		Node max=MaximizeWithPruning(initial,Integer.MIN_VALUE,Integer.MAX_VALUE);
		return max.getColNum();
	}
	
	
}
