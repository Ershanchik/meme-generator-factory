package memes.comic;

import memes.FittedText;
import memes.Meme;

import java.util.List;

/** Формат 3 (живая защита): комикс из трёх панелей. */
public record ComicPanelMeme(List<FittedText> panels) implements Meme {
    @Override public String format() { return "comic-panel"; }

    @Override public String render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < panels.size(); i++) {
            sb.append("PANEL ").append(i + 1).append(' ').append(panels.get(i));
            if (i < panels.size() - 1) sb.append('\n');
        }
        return sb.toString();
    }
}
