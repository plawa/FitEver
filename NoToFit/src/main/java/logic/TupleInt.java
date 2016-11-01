package logic;

public class TupleInt implements Comparable<TupleInt> {

	public Integer a;
	public Integer b;

	public TupleInt(Integer a, Integer b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(TupleInt o) {
		if(a > o.a)
			return 1;
		if(a < o.a)
			return -1;
		return 0;
	}


}
