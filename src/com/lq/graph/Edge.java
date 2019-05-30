package com.lq.graph;

public class Edge {
	private static double minP = 300;
	private static double maxP = 10000;
	private static int minT = 60;
	private static int maxT = 2880;
	private static int minR = 0;
	private static int maxR = 400;
	private int e;
	private int time;
	private double price;
	private int remain;
	private double w;//Â·¾¶È¨Öµ
	public Edge(int e,int time,double price,int remain) {
		this.e = e;
		this.time = time;
		this.price = price;
		this.remain = remain;
		normalize();
	}
	public void normalize() {
		price = (price-minP)/(maxP-minP);
		time = (time-minT)/(maxT-minT);
		remain = (remain-minR)/(maxR-minR);
		w = price*100 + time*100 - remain*100;
	}
	public int getE() {
		return e;
	}
	public double getW() {
		return w;
	}
}
