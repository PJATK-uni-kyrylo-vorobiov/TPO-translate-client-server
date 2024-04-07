package zad1.Handlers;

import zad1.Servers.DictionaryServerBase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

public class MainServerHandler implements Runnable
{
	private final Socket clientSocket;
	private final Map<String, Class<? extends DictionaryServerBase>> dictionaryServers;

	public MainServerHandler(Socket clientSocket, Map<String, Class<? extends DictionaryServerBase>> dictionaryServers)
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

			out.println("Waiting for input in the following format: word_to_translate language_code port");

			String input;
			while ((input = in.readLine()) == null)
			{}

			String[] inputTokens = input.split(" ");

			if (inputTokens.length != 3)
			{
				out.println("Invalid input");
				return;
			}

			String word = inputTokens[0], languageCode = inputTokens[1], port = inputTokens[2];
			System.out.println("Your input: " + Arrays.toString(inputTokens));
			System.out.flush();

			Class<? extends DictionaryServerBase> languageServerClass = dictionaryServers.get(languageCode);

			if (languageServerClass == null)
			{
				out.println("The provided language code is not available");
				return;
			}
			out.println("Translating your word...");

			DictionaryServerBase languageServer = languageServerClass
					.getDeclaredConstructor(String.class, String.class)
					.newInstance(clientSocket.getInetAddress().getHostAddress(), port);

			languageServer.sendResponse(word);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
