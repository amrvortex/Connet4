package backEnd;

public class Utils {
	
	/*
	 * player indicate the player turns
	 *  1 or 2
	 *we iterate in 1 direction each time 
	 *after we get to the last int in the sequence we see the next bit if it is 0 
	 *we increse the count if it is 2 or the limit of array we don't increase 
	 *  weights to be determined		
	 * */
	public double sequence(Node state,int player) {//calculated 
		int numOf3s=0,numOf2s=0,numOf4s=0,oneTill4=0;
		
		
		//after looping
		if(player==2) {
			numOf4s=numOf4s-state.getAiPlayerScore();
		}
		else {
			numOf4s=numOf4s-state.getHumanPlayerScore();
		}
		return numOf4s+oneTill4+numOf2s+numOf3s;
	}
	/*
	 * count the surruonding of a bit that has 0 like 
	 * 		surrounding for 1 =4
	 * 			000
	 * 			210
	 * 			121
	 * 
	 * */
	private double potentials (int state[][],int player) {
		
	}
	//we call 4 then oneplay till win then threes then twos
	public double  heuristics(Node terminal) {
		//iterate over the arr
		return potentials(terminal.getState(),2)+sequence(terminal,2)-potentials(terminal.getState(),1)-sequence(terminal,1);
		
		
		
	}
	public Node[] getNeighbours(Node State) {
		
	}
	private int [][]putCheckered(int arr[][]) {
		
		return arr;
	}
	
	
}
