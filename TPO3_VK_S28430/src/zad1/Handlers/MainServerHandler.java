package zad1.Handlers;

import zad1.Servers.DictionaryServerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class MainServerHandler implements Runnable
{
	private final Socket clientSocket;
	private final Map<String, DictionaryServerInterface> dictionaryServers;

	public MainServerHandler(Socket clientSocket, Map<String, DictionaryServerInterface> dictionaryServers)
	{
		this.clientSocket = clientSocket;
		this.dictionaryServers = dictionaryServers;
	}

	@Override
	public void run()
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			String[] inputTokens = in.readLine().split(" ");
			String word = inputTokens[0], languageCode = inputTokens[1], port = inputTokens[2];

			DictionaryServerInterface languageServer = dictionaryServers.get(languageCode);

			if (languageServer == null)
			{
				out.println("The provided language code is not available");
				return;
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
