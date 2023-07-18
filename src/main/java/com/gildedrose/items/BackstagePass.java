package com.gildedrose.items;

public class BackstagePass extends UpdatableItem {

    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (sellIn < 0) {
            quality = 0;
        } else if (quality < MAX_QUALITY) {
            quality++;
            if (sellIn < 10 && quality < MAX_QUALITY) {
                quality++;
            }
            if (sellIn < 5 && quality < MAX_QUALITY) {
                quality++;
            }
        }
    }
}
