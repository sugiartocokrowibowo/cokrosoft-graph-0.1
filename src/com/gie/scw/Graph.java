package com.gie.scw;

public class Graph {	
	/**
	 * @author sugiarto cokrowibowo
	 * @author sugiartocokrowibowo@gmail.com
	 * @category Mathematics and Computer Science
	 * 
	 */
	private String[] label;
	private int[][]posisi;
	private int[][]adjacency;
	private int numVertex;
	public String[] getLabel() {
		return label;
	}
	public void setLabel(String[] label) {
		this.label = label;
	}
	public int[][] getPosisi() {
		return posisi;
	}
	public void setPosisi(int[][] posisi) {
		this.posisi = posisi;
	}
	public int[][] getAdjacency() {
		return adjacency;
	}
	public void setAdjacency(int[][] adjacency) {
		this.adjacency = adjacency;
	}
	public int getNumVertex() {
		return numVertex;
	}
	public void setNumVertex(int numVertex) {
		this.numVertex = numVertex;
	}
	
	

}
