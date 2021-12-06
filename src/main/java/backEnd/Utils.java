package backEnd;

import java.util.ArrayList;

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
		double count=0;
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				if(state[i][j]==player) {
					if(i!=0) {
						if(state[i-1][j]==0)
							count++;
						if(j!=0&&state[i-1][j-1]==0)
							count++;
						if(j!=6&&state[i-1][j+1]==0)
							count++;
						
					}
					if(i!=5) {
						if(state[i+1][j]==0)
							count++;
						if(j!=0&&state[i+1][j-1]==0)
							count++;
						if(j!=6&&state[i+1][j+1]==0)
							count++;
					}
					if(j!=6&&state[i][j+1]==0)
						count++;
					if(j!=0&&state[i][j-1]==0)
						count++;
				}
			}
		}
		return count;
	}
	//we call 4 then oneplay till win then threes then twos
	public double  heuristics(Node terminal) {
		//iterate over the arr
		return potentials(terminal.getState(),2)+sequence(terminal,2)-potentials(terminal.getState(),1)-sequence(terminal,1);
		
		
		
	}
	public ArrayList<Node>getNeighbours(Node State) {
		int arr[][]=State.getState();
		ArrayList <Node>returnedNodes=new ArrayList<Node>();
		for(int i=0;i<7;i++){
			int tempArr[][]=putCheckered(arr,i);
			if(!compareArrays(tempArr,State.getState())) {
			Node temp=new Node(State,tempArr,i,State.getHumanPlayerScore(),State.getAiPlayerScore());
			returnedNodes.add(temp);
			}
		}
		return returnedNodes;
	}
	public ArrayList<Node>getNeighboursPruning(Node State) {
		int arr[][]=State.getState();
		ArrayList <Node>returnedNodes=new ArrayList<Node>();
		for(int i=0;i<7;i++){
			int tempArr[][]=putCheckered(arr,i);
			if(!compareArrays(tempArr,State.getState())) {
			Node temp=new Node(State,tempArr,i,State.getHumanPlayerScore(),State.getAiPlayerScore(),Integer.MIN_VALUE,Integer.MAX_VALUE);
			returnedNodes.add(temp);
			}
		}
		return returnedNodes;
	}
	//getNeighbours for pruning
	private int [][]putCheckered(int arr[][],int col) {
		int returnedArr[][]=new int [6][7];
		returnedArr=copyArray(arr,returnedArr);
		for(int j=0;j<6;j++) {
			if(j==5&&arr[j][col]==0) {
				returnedArr[j][col]=2;
			}
			else if(arr[j][col]!=0) {
				returnedArr[j-1][col]=2;
				j=6;
			}
		}
		return returnedArr;
	}
	private boolean compareArrays(int source[][],int destination[][]) {
		boolean returned=true;
		for (int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				if(destination[i][j]!=source[i][j]) {
					returned=false;
				}
				
			}
		}
		return returned;
	}
	int [][]copyArray(int source[][],int destination[][]) {
		for (int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				destination[i][j]=source[i][j];
			}
		}
		return destination;
	}
	
}
