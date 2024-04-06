package zad1.Servers;

import java.util.HashMap;
import java.util.Map;

public class ENDictionaryServer implements DictionaryServerInterface
{
	private final Map<String, String> translations;

	public ENDictionaryServer()
	{
		translations = new HashMap<>();
		translations.putAll(Map.of(
				"kot", "cat",
				"samochód", "car",
				"pies", "dog"));
	}
	@Override
	public String translateWord(String word)
	{
		return translations.get(word);
	}
}
