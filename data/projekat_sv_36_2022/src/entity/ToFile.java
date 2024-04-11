package entity;

public interface ToFile {
	public static final String delimiter = System.getProperty("file.separator"); // mozda je glupo da postoji ali eto
	public static final String delimiterInEntity = ";";
	
	public String toFile();
}
