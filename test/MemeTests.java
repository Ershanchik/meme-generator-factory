import memes.*;
import memes.comic.ComicPanelMemeCreator;

public class MemeTests {
    private static int passed = 0, failed = 0;

    public static void main(String[] args) {
        MemeCreator topBottom = new TopBottomMemeCreator();
        MemeCreator caption = new CaptionMemeCreator();
        MemeCreator comic = new ComicPanelMemeCreator();
        check("короткий текст -> шрифт 48", () -> {
            TopBottomMeme m = (TopBottomMeme) topBottom.create("Привет", "Мир");
            return m.top().fontSize() == 48;
        });

        check("длинный текст сжимается", () -> {
            TopBottomMeme m = (TopBottomMeme) topBottom.create(
                    "Когда дедлайн завтра а ты только начал читать задание", "Ок");
            return m.top().fontSize() < 48 && m.top().fontSize() >= 20;
        });

        check("перенос по словам", () -> {
            TopBottomMeme m = (TopBottomMeme) topBottom.create(
                    "Когда дедлайн завтра а ты только начал читать задание", "Ок");
            return m.top().lines().size() > 1;
        });

        check("строки не шире холста", () -> {
            TopBottomMeme m = (TopBottomMeme) topBottom.create(
                    "Очень длинная подпись для проверки переноса по словам", "Ок");
            return m.top().lines().stream()
                    .allMatch(l -> l.length() * m.top().fontSize() * 0.6 <= MemeCreator.CANVAS_WIDTH);
        });

        checkThrows("слишком длинный текст -> исключение", () -> caption.create("А".repeat(200)));

        checkThrows("слово шире строки -> исключение", () -> topBottom.create("Б".repeat(60), "Ок"));

        checkThrows("REJECT не сжимает", () ->
                caption.create("Это достаточно длинная подпись которая не влезет в крупный шрифт совсем никак вообще"));

        checkThrows("пустой текст", () -> caption.create("   "));
        checkThrows("null вместо текста", () -> caption.create((String) null));
        checkThrows("неверное количество текстов", () -> topBottom.create("Один"));

        check("comic-panel создаётся", () -> comic.create("Утро", "Дедлайн", "Паника").render().contains("PANEL 3"));
        checkThrows("comic-panel: панель уже холста, длинное слово отклоняется",
                () -> comic.create("Электроэнцефалографически", "Ок", "Ок"));

        check("граница: 33 символа проходят на шрифте 20", () ->
                topBottom.create("а".repeat(33), "Ок") != null);
        checkThrows("граница: 34 символа без пробелов отклоняются", () ->
                topBottom.create("а".repeat(34), "Ок"));

        System.out.println("\nПройдено: " + passed + ", провалено: " + failed);
        if (failed > 0) System.exit(1);
    }

    private interface Check { boolean run(); }
    private interface Action { void run(); }

    private static void check(String name, Check c) {
        boolean ok;
        try { ok = c.run(); } catch (Exception e) { ok = false; }
        report(name, ok);
    }

    private static void checkThrows(String name, Action a) {
        boolean ok;
        try { a.run(); ok = false; } catch (LayoutException e) { ok = true; }
        report(name, ok);
    }

    private static void report(String name, boolean ok) {
        System.out.println((ok ? "[OK]   " : "[FAIL] ") + name);
        if (ok) passed++; else failed++;
    }
}
