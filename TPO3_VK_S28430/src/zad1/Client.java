package zad1;

import zad1.Servers.MainServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

				new PrintWriter(socket.getOutputStream(), true).println(input);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
