package net.mcreator.test.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.test.init.TestModItems;
import net.mcreator.test.TestMod;

public class TsukItemIsDroppedByPlayerProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		TestMod.queueServerWork(20, () -> {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(0, new ItemStack(TestModItems.TSUKYOMIHEAD_BOOTS.get()));
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.FEET, new ItemStack(TestModItems.TSUKYOMIHEAD_BOOTS.get()));
				}
			}
		});
		TestMod.queueServerWork(20, () -> {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(1, new ItemStack(TestModItems.TSUKYOMIHEAD_LEGGINGS.get()));
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.LEGS, new ItemStack(TestModItems.TSUKYOMIHEAD_LEGGINGS.get()));
				}
			}
		});
		TestMod.queueServerWork(20, () -> {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(2, new ItemStack(TestModItems.TSUKYOMIHEAD_CHESTPLATE.get()));
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.CHEST, new ItemStack(TestModItems.TSUKYOMIHEAD_CHESTPLATE.get()));
				}
			}
		});
		TestMod.queueServerWork(20, () -> {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(3, new ItemStack(TestModItems.TSUKYOMIHEAD_HELMET.get()));
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.HEAD, new ItemStack(TestModItems.TSUKYOMIHEAD_HELMET.get()));
				}
			}
		});
	}
}
