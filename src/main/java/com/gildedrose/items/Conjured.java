package com.gildedrose.items;

public class Conjured extends UpdatableItem {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        sellIn--;
        if (quality > 0) {
            quality -= 2;
        }
        if (sellIn < 0 && quality > 0) {
            quality -= 2;
        }
    }
}
