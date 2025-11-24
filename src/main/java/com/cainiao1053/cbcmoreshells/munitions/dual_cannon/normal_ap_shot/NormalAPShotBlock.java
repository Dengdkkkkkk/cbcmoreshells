package com.cainiao1053.cbcmoreshells.munitions.dual_cannon.normal_ap_shot;

import com.cainiao1053.cbcmoreshells.CBCMSEntityTypes;
import com.cainiao1053.cbcmoreshells.munitions.dual_cannon.InertDualCannonProjectileBlock;
import net.minecraft.world.entity.EntityType;



public class NormalAPShotBlock extends InertDualCannonProjectileBlock<NormalAPShotProjectile> {

	public NormalAPShotBlock(Properties properties) {
		super(properties);
	}



	@Override
	public EntityType<? extends NormalAPShotProjectile> getAssociatedEntityType() {
		return CBCMSEntityTypes.NORMAL_AP_SHOT.get();
	}

}
