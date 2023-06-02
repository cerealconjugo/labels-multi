package net.mehvahdjukaar.labels;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Author: MehVahdJukaar
 */
public class LabelsMod {

    public static final String MOD_ID = "labels";
    public static final Logger LOGGER = LogManager.getLogger("Labels");

    public static final boolean OPTIFRICK_HACK = Package.getPackage("net.optifine") != null;

    private static final String NAME = "label";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit() {
        RegHelper.addItemsToTabsRegistration(e -> {
            e.addBefore(CreativeModeTabs.FUNCTIONAL_BLOCKS, i -> i.is(Items.ITEM_FRAME), LABEL_ITEM.get());
        });
    }

    public static final Supplier<EntityType<LabelEntity>> LABEL =
            regEntity(NAME, () -> (
                    EntityType.Builder.<LabelEntity>of(LabelEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE))
            );

    public static final Supplier<Item> LABEL_ITEM = regItem(NAME, () -> new LabelItem(new Item.Properties()));


    public static <T extends Entity> Supplier<EntityType<T>> regEntity(String name, Supplier<EntityType.Builder<T>> builder) {
        return RegHelper.registerEntityType(res(name), () -> builder.get().build(name));
    }

    public static <T extends Item> Supplier<T> regItem(String name, Supplier<T> sup) {
        return RegHelper.registerItem(res(name), sup);
    }
}
