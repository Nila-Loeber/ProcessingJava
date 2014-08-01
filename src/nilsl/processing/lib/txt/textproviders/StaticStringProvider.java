package nilsl.processing.lib.txt.textproviders;

public class StaticStringProvider implements TextProvider {

	private String theString;

	public StaticStringProvider(String theString) {
		this.theString = theString;

	}
	
	@Override
	public String getNextWord() {
		// TODO Auto-generated method stub
		return theString;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

}
