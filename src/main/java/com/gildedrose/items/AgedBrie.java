package com.gildedrose.items;

public class AgedBrie extends UpdatableItem {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        sellIn--;
        if (quality < MAX_QUALITY) {
            quality++;
        }
        if (sellIn < 0 && quality < MAX_QUALITY) {
            quality++;
        }
    }
}
