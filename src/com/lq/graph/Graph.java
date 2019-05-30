package com.lq.graph;

import java.util.*;

public class Graph {
	private static double INF=500;
	private int nodeNum;
	private int edgeNum;
	private Vector<Vector<Edge>> graph;
	private Map<Integer,String> map1 = new HashMap<Integer,String>();
	private Map<String,Integer> map2 = new HashMap<String,Integer>();
	Vector<Double> dist;
	Vector<Integer> pre;
	Vector<Boolean> vis;
	int src,des;
	public Graph(int node,int edge) {
		nodeNum = node;
		edgeNum = edge;
		graph = new Vector<Vector<Edge>>(node);
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public int getEdgeNum() {
		return edgeNum;
	}
	public void setMap(ArrayList<String> citys) {
		int cnt = 0;
		for(String city:citys) {
			map1.put(cnt, city);
			map2.put(city, cnt);
			cnt++;
		}
	}
	public void addEdge(String src,String des,
			int gap,float price,int remain) {
		int src_index = map2.get(src);
		int des_index = map2.get(des);
		graph.get(src_index).add(new Edge(des_index,gap,price,remain));
	}
	public void clearRepeat() {
		for(int i = 0;i < graph.size();i++) {
			graph.get(i).sort(new EdgeComparator());
			int n = graph.get(i).size();
			for(int j = 0;j < n-1;j++) {
				if(graph.get(i).get(j).getE() == graph.get(i).get(j+1).getE()) {
					graph.get(i).remove(j+1);
					j--;
					edgeNum--;
				}
			}
		}
	}
	public void run(String src_city,String des_city) {
		src = map2.get(src_city);
		des = map2.get(des_city);
		dist = new Vector<Double>(nodeNum);
		pre = new Vector<Integer>(nodeNum);
		vis = new Vector<Boolean>(nodeNum);
		for(int i = 0;i < nodeNum;i++) {
			dist.set(i, INF);
			pre.set(i,-1);
			vis.set(i,false);
		}
		dist.set(src,0.0);
		for(int i = 0;i < nodeNum;i++) {//选出目前dist最小的点
			double minDist = INF;
			int index = -1;
			for(int j = 0;j < nodeNum;j++) {
				if(vis.get(j) == true)
					continue;
				if(dist.get(j) < minDist) {
					minDist = dist.get(j);
					index = j;
				}
			}
			vis.set(index,true);//标记此点被访问过
			for(int j = 0;j < graph.get(index).size();j++) {//更新
				if(vis.get(j) == true)
					continue;
				Edge edge = graph.get(index).get(j);
				int e = edge.getE();
				double w = edge.getW();
				if(dist.get(e) > minDist + w) {
					dist.set(e,minDist+w);
					pre.set(e,index);
				}
			}
		}
	}
	public ArrayList<String> getPath() {
		ArrayList<String> path = new ArrayList<String>();
		int x = des;
		while(x != -1) {
			path.add(map1.get(x));
			x = pre.get(x);
		}
		return path;
	}
}

class EdgeComparator implements Comparator<Edge>{
	public int compare(Edge e1,Edge e2) {
		if(e1.getE() == e2.getE()) {
			return e1.getW() > e2.getW()?1:0;
		}
		else {
			return e1.getE() < e2.getE()?1:0;
		}
	}
}
