package com.gildedrose.items;

public class Sulfuras extends DefaultItem {

    private static final int QUALITY = 80;

    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        quality = QUALITY;
    }
}
