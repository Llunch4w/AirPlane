package com.lq.graph;

import java.util.ArrayList;

public class Dijkstra {
	Graph graph;
	public void init() {
		graph = new BuildGraph().build();
		graph.clearRepeat();
	}
	public void dijkstra(String src,String des) {
		graph.run(src,des);
	}
	public ArrayList<String> getPath(String src,String des) {
		init();
		dijkstra(src, des);
		ArrayList<String> reverse_path = graph.getPath();
		ArrayList<String> path = new ArrayList<String>();
		for(int i = reverse_path.size()-1;i >= 0;i--) {
			path.add(reverse_path.get(i));
		}
		return path;
	}
}
