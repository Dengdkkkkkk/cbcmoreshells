package com.cainiao1053.cbcmoreshells;

import com.cainiao1053.cbcmoreshells.client.render.DynamicOutliner;

//@Mod.EventBusSubscriber(modid = Cbcmoreshells.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CbcmoreshellsClient {

    public static final DynamicOutliner CLIENT_LERPED_OUTLINER = new DynamicOutliner();
//	@SubscribeEvent
//	public static void onClientSetup(FMLClientSetupEvent event) {
//		event.enqueueWork(() -> {
//			EntityType<?> cannon = ForgeRegistries.ENTITY_TYPES.getValue(
//					new ResourceLocation("createbigcannons", "ap_shot"));
//
//			if (cannon != null) {
//				RenderHandler.registerExtraLayer(
//						cannon,
//						new BillboardOverlay(
//								new ResourceLocation(Cbcmoreshells.MODID, "textures/block/projectile/apbc_shot.png"),
//								0.6f
//						)
//				);
//			}
//		});
//	}

    public static void clientInit(){}

}