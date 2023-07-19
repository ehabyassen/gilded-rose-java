package com.gildedrose;

import com.gildedrose.items.*;
import org.junit.jupiter.api.Test;

import static com.gildedrose.items.UpdatableItem.MAX_QUALITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GildedRoseTest {

    @Test
    void test_that_quality_degrades_by_one_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new UpdatableItem("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    void test_that_quality_degrades_by_two_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new UpdatableItem("foo", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_quality_does_not_degrade_to_a_negative_value() {
        for (int sallIn = 1; sallIn >= 0; sallIn--) {
            for (int quality = 1; quality >= 0; quality--) {
                Item[] items = new Item[]{new UpdatableItem("foo", sallIn, quality)};
                GildedRose app = new GildedRose(items);

                app.updateItems();

                assertFalse(app.items[0].quality < 0);
            }
        }
    }

    @Test
    void test_that_AgedBrie_quality_increases_by_one_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_AgedBrie_quality_increases_by_two_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(7, app.items[0].quality);
    }

    @Test
    void test_that_AgedBrie_does_not_exceed_max_quality_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void test_that_AgedBrie_does_not_exceed_max_quality_if_sellIn_has_passed() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 0, 49)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void test_that_BackstagePass_does_not_exceed_max_quality_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void test_that_BackstagePass_does_not_exceed_max_quality_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateItems();

            assertEquals(MAX_QUALITY, app.items[0].quality);
        }
    }

    @Test
    void test_that_BackstagePass_does_not_exceed_max_quality_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateItems();

            assertEquals(MAX_QUALITY, app.items[0].quality);
        }
    }

    @Test
    void test_that_sellIn_is_decremented_by_one_per_update() {
        Item[] items = new Item[]{new UpdatableItem("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_never_has_to_be_sold() {
        Item[] items = new Item[]{new Sulfuras("Sulfuras, Hand of Ragnaros", 1)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_quality_does_not_degrade() {
        Item[] items = new Item[]{new Sulfuras("Sulfuras, Hand of Ragnaros", 1)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(Sulfuras.QUALITY, app.items[0].quality);
    }

    @Test
    void test_that_BackstagePass_quality_increases_by_one_per_update_if_sellIn_is_more_than_ten_days() {
        Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 11, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_BackstagePass_quality_increases_by_two_per_update_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateItems();

            assertEquals(7, app.items[0].quality);
        }
    }

    @Test
    void test_that_BackstagePass_quality_increases_by_three_per_update_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateItems();

            assertEquals(8, app.items[0].quality);
        }
    }

    @Test
    void test_that_BackstagePass_quality_drops_to_zero_if_sellIn_has_passed() {
        Item[] items = new Item[]{new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_is_degraded_by_two_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Conjured("Conjured Mana Cake", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_is_degraded_by_four_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Conjured("Conjured Mana Cake", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateItems();

        assertEquals(1, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_does_not_degrade_to_a_negative_value() {
        for (int sallIn = 1; sallIn >= 0; sallIn--) {
            for (int quality = 4; quality >= 0; quality--) {
                Item[] items = new Item[]{new Conjured("Conjured Mana Cake", sallIn, quality)};
                GildedRose app = new GildedRose(items);

                app.updateItems();

                assertFalse(app.items[0].quality < 0);
            }
        }
    }
}
