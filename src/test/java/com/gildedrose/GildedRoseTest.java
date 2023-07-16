package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void test_that_quality_degrades_by_one_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    void test_that_quality_degrades_by_two_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("foo", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_quality_does_not_degrade_to_a_negative_value() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Aged_Brie_quality_increases_per_update() {
        Item[] items = new Item[]{new Item("Aged Brie", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_quality_50_is_not_updated() {
        Item[] items = new Item[]{new Item("Aged Brie", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_updatable_quality_49_does_not_exceed_50() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 49)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_updatable_quality_48_does_not_exceed_50() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 48)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Backstage_with_quality_50_is_not_updated() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_does_not_exceed_50_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(50, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_does_not_exceed_50_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(50, app.items[0].quality);
        }
    }

    @Test
    void test_that_sellIn_is_decremented_by_one_per_update_if_item_is_not_Sulfuras() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_never_has_to_be_sold() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_quality_does_not_degrade_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
    }

    @Test
    void test_that_Sulfuras_quality_does_not_degrade_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
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
}
