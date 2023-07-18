package com.gildedrose;

import com.gildedrose.items.UpdatableItem;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateItems() {
        for (Item item : items) {
            if (item instanceof UpdatableItem) {
                ((UpdatableItem) item).updateItem();
            }
        }
    }
}
