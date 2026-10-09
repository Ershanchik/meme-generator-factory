package memes;

import memes.comic.ComicPanelMemeCreator;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, MemeCreator> creators = new LinkedHashMap<>();
        register(creators, new TopBottomMemeCreator());
        register(creators, new CaptionMemeCreator());

        System.out.println("=== Формат 1: top-bottom ===");
        System.out.println(creators.get("top-bottom")
                .create("Когда пишешь код", "И он работает с первого раза").render());

        System.out.println("\n=== Формат 1: длинный текст переносится и сжимается ===");
        System.out.println(creators.get("top-bottom")
                .create("Когда дедлайн завтра а ты только начал читать задание",
                        "И внезапно понимаешь что всё это надо сдавать устно").render());

        System.out.println("\n=== Формат 2: caption ===");
        System.out.println(creators.get("caption").create("Это нормально").render());

        System.out.println("\n=== Отклонение слишком длинного текста ===");
        try {
            creators.get("caption").create("А".repeat(200));
        } catch (LayoutException e) {
            System.out.println("Отклонено: " + e.getMessage());
        }
        register(creators, new ComicPanelMemeCreator());
        System.out.println("\n=== Формат 3 (защита): comic-panel ===");
        System.out.println(creators.get("comic-panel").create("Утро", "Дедлайн", "Паника").render());
    }

    private static void register(Map<String, MemeCreator> map, MemeCreator c) {
        map.put(c.name(), c);
    }
}
