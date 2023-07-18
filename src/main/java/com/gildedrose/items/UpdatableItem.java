package com.gildedrose.items;

import com.gildedrose.Item;

public class UpdatableItem extends Item {

    public static final int MAX_QUALITY = 50;

    public UpdatableItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateItem() {
        sellIn--;
        updateQuality();
    }

    protected void updateQuality() {
        if (quality > 0) {
            quality--;
        }
        if (sellIn < 0 && quality > 0) {
            quality--;
        }
    }
}
