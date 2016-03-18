package ColorStyleClassification;
public class colorRange {
	int r;
	int g;
	int b;
	int numBucket;
	public colorRange(int R, int G, int B, int numSharePerDimension){
		this.r = R;
		this.g = G;
		this.b = B;
		int rangeWidth = 256/numSharePerDimension;
		numBucket = rangeDivisor(rangeWidth, numSharePerDimension);
	}
	private int rangeDivisor(int rangeWidth, int numSharePerDimension)
	{
		int x = r/rangeWidth;
		int y = g/rangeWidth;
		int z = b/rangeWidth;
		int num = x + y * numSharePerDimension + z * numSharePerDimension * numSharePerDimension;
		return num;
	}
}
