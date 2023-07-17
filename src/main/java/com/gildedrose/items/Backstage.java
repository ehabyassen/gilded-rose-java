package com.gildedrose.items;

public class Backstage extends DefaultItem {

    public Backstage(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        if (quality < MAX_QUALITY) {
            quality++;
            if (sellIn < 11 && quality < MAX_QUALITY) {
                quality++;
            }
            if (sellIn < 6 && quality < MAX_QUALITY) {
                quality++;
            }
        }

        sellIn--;

        if (sellIn < 0) {
            quality = 0;
        }
    }
}
