package kr.or.ddit.utils;

import java.util.Comparator;

import kr.or.ddit.vo.BtsVO;

public class BtsComparator implements Comparator<BtsVO>{
	@Override
	public int compare(BtsVO o1, BtsVO o2) {
		if(o1.getCount() < o2.getCount()) return 1;
		else if(o1.getCount() > o2.getCount()) return -1;
		else return 0;
	}
}
