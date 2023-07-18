package com.gildedrose;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.gildedrose.items.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private ListAppender<ILoggingEvent> appender;
    private final Logger appLogger = (Logger) LoggerFactory.getLogger(GildedRose.class);

    @BeforeEach
    public void setUp() {
        appender = new ListAppender<>();
        appender.start();
        appLogger.addAppender(appender);
    }

    @AfterEach
    public void tearDown() {
        appLogger.detachAppender(appender);
    }

    @Test
    void test_that_quality_degrades_by_one_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new UpdatableItem("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    void test_that_quality_degrades_by_two_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new UpdatableItem("foo", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_quality_does_not_degrade_to_a_negative_value() {
        Item[] items = new Item[]{new UpdatableItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Aged_Brie_quality_increases_per_update() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_quality_50_is_not_updated() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_updatable_quality_49_does_not_exceed_50() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 0, 49)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Aged_Brie_with_updatable_quality_48_does_not_exceed_50() {
        Item[] items = new Item[]{new AgedBrie("Aged Brie", 0, 48)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_quality_of_Backstage_with_quality_50_is_not_updated() {
        Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", 1, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_does_not_exceed_50_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(50, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_does_not_exceed_50_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", i, 49)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(50, app.items[0].quality);
        }
    }

    @Test
    void test_that_sellIn_is_decremented_by_one_per_update() {
        Item[] items = new Item[]{new UpdatableItem("foo", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_never_has_to_be_sold() {
        Item[] items = new Item[]{new Sulfuras("Sulfuras, Hand of Ragnaros", 1)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void test_that_Sulfuras_quality_does_not_degrade() {
        Item[] items = new Item[]{new Sulfuras("Sulfuras, Hand of Ragnaros", 1)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(Sulfuras.QUALITY, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_increases_by_one_per_update_if_sellIn_is_more_than_ten_days() {
        Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", 11, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void test_that_Backstage_quality_increases_by_two_per_update_if_sellIn_is_between_ten_and_six_inclusive() {
        for (int i = 10; i >= 6; i--) {
            Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(7, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_increases_by_three_per_update_if_sellIn_is_between_five_and_one_inclusive() {
        for (int i = 5; i >= 1; i--) {
            Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", i, 5)};
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertEquals(8, app.items[0].quality);
        }
    }

    @Test
    void test_that_Backstage_quality_drops_to_zero_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Backstage("Backstage passes to a TAFKAL80ETC concert", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_is_degraded_by_two_per_update_if_sellIn_has_not_passed() {
        Item[] items = new Item[]{new Conjured("Conjured Mana Cake", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_is_degraded_by_four_per_update_if_sellIn_has_passed() {
        Item[] items = new Item[]{new Conjured("Conjured Mana Cake", 0, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
    }

    @Test
    void test_that_Conjured_quality_does_not_degrade_to_a_negative_value() {
        Item[] items = new Item[]{new Conjured("Conjured Mana Cake", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_that_non_updatable_item_is_logged_as_info() {
        Item[] items = new Item[]{new Item("non-updatable item", 1, 5)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        List<ILoggingEvent> loggingEvents = appender.list;
        assertEquals(1, loggingEvents.size());
        ILoggingEvent loggingEvent = loggingEvents.get(0);
        assertEquals(Level.INFO, loggingEvent.getLevel());
        assertEquals("Non updatable item: non-updatable item, 1, 5", loggingEvent.getFormattedMessage());
    }
}
