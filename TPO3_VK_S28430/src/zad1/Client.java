package zad1;

import zad1.Servers.MainServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	public static void main(String[] args)
	{
		try (Socket socket = new Socket("localhost", MainServer.MAIN_SERVER_PORT))
		{
			try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
			{
				String line = in.readLine();
				System.out.println(line);
				String input = new Scanner(System.in).nextLine();

				String[] tokens = input.split(" ");
				int port = Integer.parseInt(tokens[2]);

				new PrintWriter(socket.getOutputStream(), true).println(input);

				line = in.readLine();
				System.out.println(line);

				readResponse(port);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void readResponse(int port)
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			Socket socket = serverSocket.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String response = in.readLine();
			System.out.println("Translation: " + response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
