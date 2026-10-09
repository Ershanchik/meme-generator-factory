package memes;

import java.util.List;

public class CaptionMemeCreator extends MemeCreator {
    private static final int CAPTION_HEIGHT = 120;

    @Override public String name() { return "caption"; }
    @Override protected int expectedTextCount() { return 1; }

    @Override
    protected Meme build(List<String> t) {
        // REJECT: подпись крупная и фиксированная, слишком длинную не ужимаем
        return new CaptionMeme(fit(t.get(0), CANVAS_WIDTH, CAPTION_HEIGHT, 32, 32, FitPolicy.REJECT));
    }
}
