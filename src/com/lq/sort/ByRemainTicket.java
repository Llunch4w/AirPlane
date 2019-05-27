package com.lq.sort;

import java.util.*;

import com.lq.model.Flight;

public class ByRemainTicket {
	public void sort(ArrayList<Flight> flights,String type) {
		if(type.equals("asc"))
			Collections.sort(flights,
					new RemainTicketAscComparator());
		else if(type.equals("des"))
			Collections.sort(flights,
					new RemainTicketDesComparator());
	}
}

class RemainTicketAscComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		int r1 = f1.getContainer().getReamin();
		int r2 = f2.getContainer().getReamin();
		return r1 > r2?1:0;
	}
}

class RemainTicketDesComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		int r1 = f1.getContainer().getReamin();
		int r2 = f2.getContainer().getReamin();
		return r1 < r2?1:0;
	}
}
