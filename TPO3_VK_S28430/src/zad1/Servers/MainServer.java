package zad1.Servers;

import zad1.Handlers.MainServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainServer
{
	public static final int MAIN_SERVER_PORT = 5001;

	private final static Map<String, Class<? extends DictionaryServerBase>>
			dictionaryServers = new ConcurrentHashMap<>();

	public static void main(String[] args) throws IOException
	{
		dictionaryServers.put("EN", ENDictionaryServer.class);

		try(ServerSocket serverSocket = new ServerSocket(MAIN_SERVER_PORT))
		{
			System.out.printf("Main Server listening on port %d...%n", MAIN_SERVER_PORT);
			while (true)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected");

				Thread clientThread = new Thread(new MainServerHandler(clientSocket, dictionaryServers));
				clientThread.start();
			}
		}
	}
}
