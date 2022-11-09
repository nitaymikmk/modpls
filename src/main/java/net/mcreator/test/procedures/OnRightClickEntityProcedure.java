package net.mcreator.test.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.mcreator.test.network.TestModVariables;
import net.mcreator.test.TestMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OnRightClickEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = entity.getX();
			entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.x = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = entity.getY();
			entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.y = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = entity.getZ();
			entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.z = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		TestMod.queueServerWork(10, () -> {
			if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
				ResourceKey<Level> destinationType = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("test:tsukuyomi"));
				if (_player.level.dimension() == destinationType)
					return;
				ServerLevel nextLevel = _player.server.getLevel(destinationType);
				if (nextLevel != null) {
					_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
					_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
					_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
					for (MobEffectInstance _effectinstance : _player.getActiveEffects())
						_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
					_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
		});
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 110, 255, (false), (false)));
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.HARM, 20, 1, (false), (false)));
		TestMod.queueServerWork(100, () -> {
			if (entity instanceof ServerPlayer _player && !_player.level.isClientSide()) {
				ResourceKey<Level> destinationType = Level.OVERWORLD;
				if (_player.level.dimension() == destinationType)
					return;
				ServerLevel nextLevel = _player.server.getLevel(destinationType);
				if (nextLevel != null) {
					_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
					_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
					_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
					for (MobEffectInstance _effectinstance : _player.getActiveEffects())
						_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
					_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
			TestMod.queueServerWork(5, () -> {
				{
					Entity _ent = entity;
					_ent.teleportTo(
							((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TestModVariables.PlayerVariables())).x),
							((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TestModVariables.PlayerVariables())).y),
							((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TestModVariables.PlayerVariables())).z));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(
								((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new TestModVariables.PlayerVariables())).x),
								((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new TestModVariables.PlayerVariables())).y),
								((entity.getCapability(TestModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new TestModVariables.PlayerVariables())).z),
								_ent.getYRot(), _ent.getXRot());
				}
			});
		});
	}
}
