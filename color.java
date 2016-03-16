package imgsim;

public class color {
	int r;
	int g;
	int b;
	double weight = 0;
	public color(int R, int G, int B) 
	{
	    this.r = R;
	    this.g = G;
	    this.b = B;
	}
	public void setWeight(double w)
	{
		this.weight=w;
	}
}
