package nilsl.processing.lib.txt.textproviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SentenceProvider implements TextProvider {

	private ArrayList<String> words;
	private Iterator<String> wordIterator;
	
	public SentenceProvider(String theString) {
		words = new ArrayList<String>(Arrays.asList(theString.split("\\s")));
		wordIterator = words.iterator();
	}
	
	@Override
	public String getNextWord() {
		// TODO Auto-generated method stub
		String result = wordIterator.next();
		if (!wordIterator.hasNext()) wordIterator = words.iterator();
		return result;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

}
