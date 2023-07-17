package com.gildedrose.items;

public class Conjured extends DefaultItem {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        if (quality > 0) {
            quality -= 2;
        }

        sellIn--;

        if (sellIn < 0 && quality > 0) {
            quality -= 2;
        }
    }
}
