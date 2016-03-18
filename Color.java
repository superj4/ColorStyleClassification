package ColorStyleClassification;

public class Color {
	int r, g, b;
	private int index = -1;

	public Color(int R, int G, int B) {
		this.r = R;
		this.g = G;
		this.b = B;
	}

	public int getSquareOfDistance(Color o) {
		return (r - o.r) * (r - o.r)
				+ (g - o.g) * (g - o.g)
				+ (b - o.b) * (b - o.b);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		return "(" + r + ", " + g + ", " + b + ")";
	}

}
