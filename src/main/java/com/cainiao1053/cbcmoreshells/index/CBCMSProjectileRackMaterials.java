package com.cainiao1053.cbcmoreshells.index;

import com.cainiao1053.cbcmoreshells.Cbcmoreshells;
import com.cainiao1053.cbcmoreshells.cannons.projectile_rack.material.ProjectileRackMaterial;
import com.cainiao1053.cbcmoreshells.cannons.projectile_rack.material.ProjectileRackMaterialProperties;

public class CBCMSProjectileRackMaterials {

	public static final ProjectileRackMaterial


		CAST_IRON = ProjectileRackMaterial.register(Cbcmoreshells.resource("cast_iron"),
				ProjectileRackMaterialProperties.builder()
				.minimumVelocityPerBarrel(2d)
				.weight(3.0f)
				.maxSafePropellantStress(2)
				.failureMode(ProjectileRackMaterialProperties.FailureMode.RUPTURE)
				.connectsInSurvival(false)
				.isWeldable(true)
				.weldDamage(1)
				.weldStressPenalty(1)
				.minimumSpread(0.05f)
				.spreadReductionPerBarrel(2f)
				.build()),

		BRONZE = ProjectileRackMaterial.register(Cbcmoreshells.resource("bronze"),
				ProjectileRackMaterialProperties.builder()
				.minimumVelocityPerBarrel(4d / 3d)
				.weight(2.0f)
				.maxSafePropellantStress(4)
				.failureMode(ProjectileRackMaterialProperties.FailureMode.RUPTURE)
				.connectsInSurvival(false)
				.isWeldable(true)
				.weldDamage(1)
				.weldStressPenalty(0)
				.minimumSpread(0.03f)
				.spreadReductionPerBarrel(1.4f)
				.build()),

		STEEL = ProjectileRackMaterial.register(Cbcmoreshells.resource("steel"),
				ProjectileRackMaterialProperties.builder()
				.minimumVelocityPerBarrel(1d)
				.weight(3.0f)
				.maxSafePropellantStress(10)
				.failureMode(ProjectileRackMaterialProperties.FailureMode.RUPTURE)
				.connectsInSurvival(true)
				.isWeldable(true)
				.weldDamage(0)
				.weldStressPenalty(0)
				.minimumSpread(1f)
				.spreadReductionPerBarrel(0f)
				.build());



}
