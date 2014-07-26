package de.mroehrl.soiaf_rpg.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * 
 */
public class SOIAFContent {
  
    public static List<SOIAFItem> ITEMS = new ArrayList<SOIAFItem>();
    public static Map<String, SOIAFItem> ITEM_MAP = new HashMap<String, SOIAFItem>();

    static {
        addItem(new SOIAFItem("1", "Currencies"));        
        //addItem(new CurrencyItem("2", "Item 2"));
        //addItem(new CurrencyItem("3", "Item 3"));
    }

    private static void addItem(SOIAFItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SOIAFItem {
        public String id;
        public String content;

        public SOIAFItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
