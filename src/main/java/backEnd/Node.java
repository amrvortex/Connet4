package backEnd;

public class Node {
	private Node parent;
	private int state[][];
	private double heuristic ;
	private int colNum;
	private int aiPlayerScore;
	private int humanPlayerScore;
	private int alpha=Integer.MIN_VALUE;
	private int beta=Integer.MAX_VALUE;
	private int levelNum;
	
	
	//initial
	public Node(int [][]state,int levelNum,int humanPlayerScore,int aiPlayerScore) {
		this.state=state;
		this.levelNum=levelNum;
		this.aiPlayerScore=aiPlayerScore;
		this.humanPlayerScore=humanPlayerScore;
		
	}
	public Node(int [][]state,int levelNum,int humanPlayerScore,int aiPlayerScore,int alpha,int beta) {
		this.state=state;
		this.levelNum=levelNum;
		this.aiPlayerScore=aiPlayerScore;
		this.humanPlayerScore=humanPlayerScore;
		this.alpha=alpha;
		this.beta=beta;
		
	}
	//working nodes
	public Node(Node parent ,int [][]state,int colNum,int humanPlayerScore,int aiPlayerScore,int alpha,int beta) {
		this.colNum=colNum;
		this.parent=parent;
		this.state=state;
		this.levelNum=parent.getLevelNum()+1;
		this.aiPlayerScore=aiPlayerScore;
		this.humanPlayerScore=humanPlayerScore;
		this.alpha=alpha;
		this.beta=beta;
		
	}
	public Node(Node parent ,int [][]state,int colNum,int humanPlayerScore,int aiPlayerScore) {
		this.colNum=colNum;
		this.parent=parent;
		this.state=state;
		this.levelNum=parent.getLevelNum()+1;
		this.aiPlayerScore=aiPlayerScore;
		this.humanPlayerScore=humanPlayerScore;
		
	}
	public int getAiPlayerScore() {
		return aiPlayerScore;
	}
	public void setAiPlayerScore(int aiPlayer) {
		this.aiPlayerScore = aiPlayer;
	}
	public int getHumanPlayerScore() {
		return humanPlayerScore;
	}
	public void setHumanPlayerScore(int humanPlayer) {
		this.humanPlayerScore = humanPlayer;
	}
	public int getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	public int getBeta() {
		return beta;
	}
	public void setBeta(int beta) {
		this.beta = beta;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int[][] getState() {
		return state;
	}
	public void setState(int[][] state) {
		this.state = state;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	
	
	

}
