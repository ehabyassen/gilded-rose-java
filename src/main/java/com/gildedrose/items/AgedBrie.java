package com.gildedrose.items;

public class AgedBrie extends DefaultItem {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        if (quality < MAX_QUALITY) {
            quality++;
        }

        sellIn--;

        if (sellIn < 0 && quality < MAX_QUALITY) {
            quality++;
        }
    }
}
