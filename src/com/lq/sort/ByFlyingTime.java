package com.lq.sort;

import java.util.*;

import com.lq.common.time.DateTime;
import com.lq.model.Flight;

public class ByFlyingTime {
	public void sort(ArrayList<Flight> flights,String type) {
		if(type.equals("asc"))
			Collections.sort(flights,
					new FlyingTimeAscComparator());
		else if(type.equals("des"))
			Collections.sort(flights,
					new FlyingTimeDesComparator());
	}
}

class FlyingTimeAscComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		DateTime t1 = f1.getStartTime().getRealTime();
		DateTime t2 = f2.getArriveTime().getRealTime();
		int res = t1.sub(t2);
		return res > 0?1:0;
	}
}


class FlyingTimeDesComparator implements Comparator<Flight>{
	public int compare(Flight f1, Flight f2) {
		DateTime t1 = f1.getStartTime().getRealTime();
		DateTime t2 = f2.getArriveTime().getRealTime();
		int res = t1.sub(t2);
		return res < 0?1:0;
	}
}
