package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String translate(String string) {
        return string.toUpperCase();
    }
}