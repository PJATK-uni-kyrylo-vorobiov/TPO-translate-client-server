package zad1.Handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable
{
	private final Socket clientSocket;

	public ClientHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	@Override
	public void run()
	{
		Scanner standardIn = new Scanner(System.in);
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true))
		{
			while (true)
			{
				String line = in.readLine();
				print(line);

				String input = standardIn.nextLine();

				if (input.equalsIgnoreCase("exit"))
				{
					out.println("exit");
					break;
				}

				String[] tokens = input.split(" ");
				int port = Integer.parseInt(tokens[2]);

				ServerSocket serverSocket = new ServerSocket(port);

				out.println(input);

				line = in.readLine();
				print(line);

				readResponse(serverSocket);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void readResponse(ServerSocket serverSocket)
	{
		try (serverSocket)
		{
			Socket socket = serverSocket.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String response = in.readLine();
			print("Translation: " + response);
			print("**************************");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void print(String msg)
	{
		System.out.println(msg);
		System.out.flush();
	}
}
