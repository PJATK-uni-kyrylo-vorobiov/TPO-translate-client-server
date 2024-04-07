package zad1.Servers;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ENDictionaryServer extends DictionaryServerBase
{
	private final Map<String, String> translations;

	public ENDictionaryServer(String clientAddress, String clientPort)
	{
		super(clientAddress, clientPort);

		translations = new HashMap<>(
				Map.of("kot", "cat",
						"samochód", "car",
						"pies", "dog")
		);
	}
	@Override
	protected String translateWord(String word)
	{
		return translations.get(word);
	}

	@Override
	public void sendResponse(String word)
	{
		String translation = translateWord(word);
		try(Socket socket = new Socket(clientAddress, Integer.parseInt(clientPort)))
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(translation);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
