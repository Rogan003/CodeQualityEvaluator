package entity;

public abstract class IncrementId {
	public static int sledeciId = 0;
	protected int id;
	
	IncrementId()
	{
		this.id = IncrementId.sledeciId++;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
		if(id >= IncrementId.sledeciId) // jel ovo okej? testirati sve ovo dobro pri fajlovima
		{
			IncrementId.sledeciId = id + 1;
		}
	}
	
	@Override
	public boolean equals(Object other) // da li ovo ima smisla ovde?
	{
		if(getClass() != other.getClass())
		{
			return false;
		}
		
		IncrementId i = (IncrementId) other;
		if(this.id == i.id)
			return true;
		
		return false;
	}
}
