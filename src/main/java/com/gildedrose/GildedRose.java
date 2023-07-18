package com.gildedrose;

import com.gildedrose.items.UpdatableItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GildedRose {
    Item[] items;

    private static final Logger LOGGER = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateItems() {
        for (Item item : items) {
            if (item instanceof UpdatableItem) {
                ((UpdatableItem) item).updateItem();
            } else {
                LOGGER.info("Non updatable item: " + item);
            }
        }
    }
}
