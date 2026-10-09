package memes.comic;

import memes.FitPolicy;
import memes.FittedText;
import memes.Meme;
import memes.MemeCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * ЖИВАЯ ЗАЩИТА: третий формат. Лежит в отдельном пакете и только НАСЛЕДУЕТ MemeCreator.
 * Ни один существующий класс не изменён - принцип Open/Closed.
 * Валидация переиспользуется через fit(...), но область у панели своя (уже и ниже).
 */
public class ComicPanelMemeCreator extends MemeCreator {
    private static final int PANEL_WIDTH = CANVAS_WIDTH / 3;
    private static final int PANEL_HEIGHT = 150;

    @Override public String name() { return "comic-panel"; }
    @Override protected int expectedTextCount() { return 3; }

    @Override
    protected Meme build(List<String> t) {
        List<FittedText> panels = new ArrayList<>();
        for (String text : t)
            panels.add(fit(text, PANEL_WIDTH, PANEL_HEIGHT, 24, 10, FitPolicy.SHRINK));
        return new ComicPanelMeme(panels);
    }
}
