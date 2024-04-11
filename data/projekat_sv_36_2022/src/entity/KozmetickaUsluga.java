package entity;

public class KozmetickaUsluga extends IncrementId implements ToFile{
	private TipKozmetickogTretmana tip;
	private int trajanje;
	private String podtip;
	
	public KozmetickaUsluga(TipKozmetickogTretmana tip, int trajanje, String podtip)
	{
		super();
		this.tip = tip;
		this.trajanje = trajanje;
		this.podtip = podtip;
	}
	
	public void setTip(TipKozmetickogTretmana tip)
	{
		this.tip = tip;
	}
	
	public void setTrajanje(int trajanje)
	{
		this.trajanje = trajanje;
	}
	
	public void setPodtip(String podtip)
	{
		this.podtip = podtip;
	}
	
	public TipKozmetickogTretmana getTip()
	{
		return this.tip;
	}
	
	public int getTrajanje()
	{
		return this.trajanje;
	}
	
	public String getPodtip()
	{
		return this.podtip;
	}
	
	@Override
	public int hashCode() // jel ima ovo smisla u mom projektu?
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Id: " + this.id + "\nTip: " + this.tip.getNaziv() + "\nPodtip: " + this.podtip +
				"\nTrajanje(u minutima): " + this.trajanje;
	}
	
	@Override
	public String toFile()
	{
		return this.id + ";" + this.tip.getId() + ";" + this.podtip + ";" +  this.trajanje;
	}
}
