package memes;

import java.util.List;

public class TopBottomMemeCreator extends MemeCreator {
    private static final int BAND_HEIGHT = 100;

    @Override public String name() { return "top-bottom"; }
    @Override protected int expectedTextCount() { return 2; }

    @Override
    protected Meme build(List<String> t) {
        FittedText top = fit(t.get(0), CANVAS_WIDTH, BAND_HEIGHT, 48, 20, FitPolicy.SHRINK);
        FittedText bottom = fit(t.get(1), CANVAS_WIDTH, BAND_HEIGHT, 48, 20, FitPolicy.SHRINK);
        return new TopBottomMeme(top, bottom);
    }
}
