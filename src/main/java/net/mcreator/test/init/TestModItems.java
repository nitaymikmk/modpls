
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.test.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.test.item.TsukyomiheadItem;
import net.mcreator.test.item.TsukItem;
import net.mcreator.test.TestMod;

public class TestModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);
	public static final RegistryObject<Item> TSUK = REGISTRY.register("tsuk", () -> new TsukItem());
	public static final RegistryObject<Item> TSUKYOMIHEAD_HELMET = REGISTRY.register("tsukyomihead_helmet", () -> new TsukyomiheadItem.Helmet());
	public static final RegistryObject<Item> TSUKYOMIHEAD_CHESTPLATE = REGISTRY.register("tsukyomihead_chestplate",
			() -> new TsukyomiheadItem.Chestplate());
	public static final RegistryObject<Item> TSUKYOMIHEAD_LEGGINGS = REGISTRY.register("tsukyomihead_leggings",
			() -> new TsukyomiheadItem.Leggings());
	public static final RegistryObject<Item> TSUKYOMIHEAD_BOOTS = REGISTRY.register("tsukyomihead_boots", () -> new TsukyomiheadItem.Boots());
}
