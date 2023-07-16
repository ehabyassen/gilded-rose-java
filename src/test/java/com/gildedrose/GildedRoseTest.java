package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void test_that_sellIn_is_decremented_by_one_per_update() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void test_that_quality_degrades_by_two_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("foo", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_quality_is_not_updated_to_a_negative_value_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("foo", 1, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_quality_is_not_updated_to_a_negative_value_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Aged_Brie_quality_increases_by_one_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("Aged Brie", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_Aged_Brie_quality_increases_by_two_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(7, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_an_item_does_not_exceed_50_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("Aged Brie", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_an_item_does_not_exceed_50_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_Sulfuras_quality_never_degrades_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
    }

    @Test
    void test_that_Sulfuras_quality_never_degrades_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
    }

    @Test
    void test_that_Sulfuras_never_has_to_be_sold() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void test_that_Backstage_quality_increases_by_one_per_update_if_sellIn_is_more_than_ten_days() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_increases_by_two_per_update_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(7, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_increases_by_three_per_update_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(8, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_drops_to_zero_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_does_not_exceed_50_if_the_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }
}
