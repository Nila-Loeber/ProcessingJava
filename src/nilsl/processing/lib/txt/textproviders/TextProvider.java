package nilsl.processing.lib.txt.textproviders;

public interface TextProvider {

	public abstract String getNextWord();

	public abstract boolean hasNext();
}
