class DiMi implements Comparable<DiMi> {
	int di;
	int mi;
	int index;
	int delay;

	@Override
	public int compareTo(DiMi obj) {
		int diff1 = di - obj.di;
		if (diff1 != 0) {
			return diff1;
		}
		int i = mi - obj.mi;
		if (i == 0) {
			return -1;
		}
		return i;
	}

	public int getDelay() {
		int delay = index - di;
		if (delay < 0) {
			delay = 0;
		}
		return delay;
	}

	@Override
	public String toString() {
		return "DiMi [di=" + di + ", mi=" + mi + "]";
	}

}