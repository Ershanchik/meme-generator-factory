package memes;
 record CaptionMeme(FittedText caption) implements Meme {
    @Override public String format() { return "caption"; }

    @Override public String render() {
        return "CAPTION " + caption;
    }
}
