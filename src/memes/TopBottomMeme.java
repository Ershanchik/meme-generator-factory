package memes;

public record TopBottomMeme(FittedText top, FittedText bottom) implements Meme {
    @Override public String format() { return "top-bottom"; }

    @Override public String render() {
        return "TOP    " + top + "\nBOTTOM " + bottom;
    }
}
