package imgsim;

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
		numBucket = rangeDivisor(rangeWidth);
	}
	private int rangeDivisor(int rangeWidth)
	{
		int x = r/rangeWidth;
		int y = g/rangeWidth;
		int z = b/rangeWidth;
		int num = x + y * rangeWidth + z * rangeWidth * rangeWidth;
		return num;
	}
}
