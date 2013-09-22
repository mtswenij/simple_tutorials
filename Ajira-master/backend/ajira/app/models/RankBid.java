package models;

public class RankBid {

	public Bid b;
	public double rank;
	public double price;
	public double qualityScore;

	public RankBid(Bid bd) {
		b = bd;
	}

	public int compareTo(RankBid compareObject) {

		if (this.rank < compareObject.rank)
			return -1;
		else if (this.rank == compareObject.rank)
			return 0;
		else
			return 1;
	}

	public void calcRank() {
		rank = qualityScore * b.price;
	}

	public double getRank() {
		return rank;
	}
	
	public double getPrice() {
		return price;
	}
}
