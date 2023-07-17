package com.gildedrose.items;

import com.gildedrose.Item;

public class DefaultItem extends Item {

    static final int MAX_QUALITY = 50;

    public DefaultItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateItem() {
        if (quality > 0) {
            quality--;
        }

        sellIn--;

        if (sellIn < 0 && quality > 0) {
            quality--;
        }
    }
}
