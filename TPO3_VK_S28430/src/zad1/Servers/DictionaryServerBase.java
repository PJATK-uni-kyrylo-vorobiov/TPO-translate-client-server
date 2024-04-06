package zad1.Servers;

public abstract class DictionaryServerBase
{
	protected final String clientAddress, clientPort;
	protected DictionaryServerBase(String clientAddress, String clientPort)
	{
		this.clientAddress = clientAddress;
		this.clientPort = clientPort;
	}
	public abstract String translateWord(String word);
}
