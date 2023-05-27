package fuzs.portablehole.client;

import fuzs.portablehole.client.particle.FXSparkle;
import fuzs.portablehole.client.renderer.blockentity.TemporaryHoleRenderer;
import fuzs.portablehole.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.BuildCreativeModeTabContentsContext;
import fuzs.puzzleslib.api.client.core.v1.context.ParticleProvidersContext;
import net.minecraft.world.item.CreativeModeTabs;

public class PortableHoleClient implements ClientModConstructor {

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.TEMPORARY_HOLE_BLOCK_ENTITY_TYPE.get(), TemporaryHoleRenderer::new);
    }

    @Override
    public void onRegisterParticleProviders(ParticleProvidersContext context) {
        context.registerParticleFactory(ModRegistry.SPARK_PARTICLE_TYPE.get(), FXSparkle.Factory::new);
    }

    @Override
    public void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsContext context) {
        context.registerBuildListener(CreativeModeTabs.TOOLS_AND_UTILITIES, (itemDisplayParameters, output) -> {
            output.accept(ModRegistry.PORTABLE_HOLE_ITEM.get());
        });
    }
}
