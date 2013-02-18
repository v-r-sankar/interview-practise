
class TwoOccurances {
	public int firstOccurance = -1;
	public int secondOccurance = -1;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstOccurance;
		result = prime * result + secondOccurance;
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
		if (firstOccurance != other.firstOccurance)
			return false;
		if (secondOccurance != other.secondOccurance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoOccurances [firstOccurance=" + firstOccurance
				+ ", secondOccurance=" + secondOccurance + "]";
	}
}