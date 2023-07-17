package com.gildedrose;

import com.gildedrose.items.DefaultItem;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ((DefaultItem) item).updateItem();
        }
    }
}