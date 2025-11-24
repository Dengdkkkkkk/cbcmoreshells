package com.cainiao1053.cbcmoreshells.munitions.racked_projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public abstract class AbstractRackedRocketBlockItem<ENTITY extends AbstractRackedProjectile> extends FuzedRackedProjectileBlockItem {

	public AbstractRackedRocketBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

//	@Override
//	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//		super.appendHoverText(stack, level, tooltip, flag);
//		RackedRocketProjectileProperties properties = CBCMSMunitionPropertiesHandlers.RACKED_ROCKET.getPropertiesOf(DUAL_HE_ROCKET.get());
//		CBCMSTooltip.appendRackedRocketInfo(stack, level, tooltip, flag, properties.ballistics().durabilityMass(), properties.ballistics().penetration(), properties.ballistics().deflection(), properties.explosion().explosivePower(),properties.lifetime(), properties.steadyStateVel(), properties.thrustTime());
//
//	}

	public abstract EntityType<? extends ENTITY> getAssociatedEntityType();

}
