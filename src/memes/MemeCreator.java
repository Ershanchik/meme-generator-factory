package memes;

import java.util.List;

public abstract class MemeCreator {
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;
    private static final int MAX_INPUT_LENGTH = 500; // защита от гигантского ввода

    public abstract String name();

    protected abstract int expectedTextCount();

    protected abstract Meme build(List<String> texts);

    public final Meme create(String... texts) {
        if (texts == null || texts.length != expectedTextCount())
            throw new LayoutException(name() + ": ожидается текстов: " + expectedTextCount());
        for (String t : texts) {
            if (t == null || t.isBlank())
                throw new LayoutException(name() + ": пустой текст");
            if (t.length() > MAX_INPUT_LENGTH)
                throw new LayoutException(name() + ": текст длиннее " + MAX_INPUT_LENGTH + " символов");
        }
        return build(List.of(texts));
    }

    protected FittedText fit(String text, int width, int height,
                             int maxFont, int minFont, FitPolicy policy) {
        return TextFitter.fit(text, width, height, maxFont, minFont, policy);
    }
}
