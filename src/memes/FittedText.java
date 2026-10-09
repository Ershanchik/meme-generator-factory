package memes;

import java.util.List;
public record FittedText(List<String> lines, int fontSize) {
    public FittedText {
        lines = List.copyOf(lines); // неизменяемая копия
    }

    @Override
    public String toString() {
        return "[" + fontSize + "px] " + String.join(" / ", lines);
    }
}
