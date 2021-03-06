package com.example.huedroid.ui.controls;

import com.example.huedroid.Lamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class LightControl {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Lamp> ITEMS = new ArrayList<Lamp>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Lamp> ITEM_MAP = new HashMap<Integer, Lamp>();

    private static final int COUNT = 25;

    private static void addItem(Lamp item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
