package zad1.Servers;

import zad1.Handlers.MainServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainServer
{
	public static final int MAIN_SERVER_PORT = 5_000;

	private final static Map<String, DictionaryServerInterface> dictionaryServers = new HashMap<>();

	public static void main(String[] args) throws IOException
	{
		dictionaryServers.put("EN", new ENDictionaryServer());

		try(ServerSocket serverSocket = new ServerSocket(MAIN_SERVER_PORT))
		{
			System.out.printf("Main Server listening on port %d...%n", MAIN_SERVER_PORT);
			while (true)
			{
				Socket clientSocket = serverSocket.accept();


				Thread clientThread = new Thread(new MainServerHandler(clientSocket, dictionaryServers));
				clientThread.start();
			}
		}
	}
}
