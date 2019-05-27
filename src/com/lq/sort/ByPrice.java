package com.lq.sort;

import java.util.*;

import com.lq.model.Flight;

public class ByPrice{
	public void sort(ArrayList<Flight> flights,String type) {
		if(type.equals("asc"))
			Collections.sort(flights,
					new PriceAscComparator());
		else if(type.equals("des"))
			Collections.sort(flights,
					new PriceDesComparator());
	}
}

class PriceAscComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		return f1.getAdultprice() > f2.getAdultprice()?1:0;
	}
}

class PriceDesComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		return f1.getAdultprice() < f2.getAdultprice()?1:0;
	}
}
