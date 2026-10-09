package memes;

import java.util.ArrayList;
import java.util.List;

public final class TextFitter {
    private static final double CHAR_WIDTH = 0.6;
    private static final double LINE_HEIGHT = 1.2;

    private TextFitter() {}


    public static FittedText fit(String text, int width, int height,
                                 int maxFont, int minFont, FitPolicy policy) {
        if (text == null || text.isBlank())
            throw new LayoutException("Пустой текст");
        if (minFont > maxFont)
            throw new IllegalArgumentException("minFont > maxFont");

        String clean = text.trim().replaceAll("\\s+", " ");
        int lowest = (policy == FitPolicy.REJECT) ? maxFont : minFont;

        for (int font = maxFont; font >= lowest; font--) {
            List<String> lines = wrap(clean, font, width);
            if (lines == null) continue;
            double neededHeight = lines.size() * font * LINE_HEIGHT;
            if (neededHeight <= height)
                return new FittedText(lines, font);
        }
        throw new LayoutException("Текст не помещается в область " + width + "x" + height
                + " (политика " + policy + "): \"" + shorten(clean) + "\"");
    }

    static List<String> wrap(String text, int font, int width) {
        int maxChars = (int) Math.floor(width / (font * CHAR_WIDTH));
        if (maxChars < 1) return null;

        List<String> lines = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (String word : text.split(" ")) {
            if (word.length() > maxChars) return null;
            if (current.length() == 0) {
                current.append(word);
            } else if (current.length() + 1 + word.length() <= maxChars) {
                current.append(' ').append(word);
            } else {
                lines.add(current.toString());
                current = new StringBuilder(word);
            }
        }
        if (current.length() > 0) lines.add(current.toString());
        return lines;
    }

    private static String shorten(String s) {
        return s.length() <= 40 ? s : s.substring(0, 37) + "...";
    }
}
