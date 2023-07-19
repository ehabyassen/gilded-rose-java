package com.gildedrose.items;

public class AgedBrie extends UpdatableItem {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < MAX_QUALITY) {
            quality += (sellIn < 0) ? 2 : 1;
            quality = Math.min(quality, MAX_QUALITY);
        }
    }
}
