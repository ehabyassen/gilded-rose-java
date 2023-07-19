package com.gildedrose.items;

public class Conjured extends UpdatableItem {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality > 1) {
            quality -= (sellIn < 0) ? 4 : 2;
            quality = Math.max(quality, 0);
        }
    }
}
