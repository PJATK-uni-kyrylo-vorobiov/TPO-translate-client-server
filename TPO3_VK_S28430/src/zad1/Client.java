package zad1;

import zad1.Handlers.ClientHandler;
import zad1.Servers.MainServer;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Client
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", MainServer.MAIN_SERVER_PORT));
			new Thread(new ClientHandler(socket)).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
