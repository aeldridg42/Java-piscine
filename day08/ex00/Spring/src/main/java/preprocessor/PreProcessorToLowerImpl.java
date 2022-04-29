package preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String translate(String string) {
        return string.toLowerCase();
    }
}