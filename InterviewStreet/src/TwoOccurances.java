class TwoOccurances implements Comparable<TwoOccurances> {
	public int firstOccurance = -1;
	public int secondOccurance = -1;
	public int index = -1;


	@Override
	public int compareTo(TwoOccurances o) {
		return index - o.index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoOccurances other = (TwoOccurances) obj;
		if (index != other.index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoOccurances [firstOccurance=" + firstOccurance
				+ ", secondOccurance=" + secondOccurance + ", index=" + index
				+ "]";
	}
}