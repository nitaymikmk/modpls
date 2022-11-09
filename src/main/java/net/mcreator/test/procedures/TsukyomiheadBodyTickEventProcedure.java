package net.mcreator.test.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.ParticleTypes;

public class TsukyomiheadBodyTickEventProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, x, y, z, 0, 1, 0);
	}
}
