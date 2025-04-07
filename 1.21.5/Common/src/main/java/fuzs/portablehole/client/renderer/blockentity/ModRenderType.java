package fuzs.portablehole.client.renderer.blockentity;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import fuzs.portablehole.PortableHole;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;

import java.util.function.Function;

public abstract class ModRenderType extends RenderType {
    public static final RenderPipeline SPARKLE_PARTICLE_RENDER_PIPELINE = RenderPipelines.register(RenderPipeline.builder(
                    RenderPipelines.PARTICLE_SNIPPET)
            .withLocation("pipeline/translucent_particle")
            .withBlend(BlendFunction.LIGHTNING)
            .build());
    private static final Function<ResourceLocation, RenderType> SPARKLE_PARTICLE = Util.memoize((ResourceLocation resourceLocation) -> create(
            PortableHole.id("sparkle_particle").toString(),
            1536,
            false,
            false,
            SPARKLE_PARTICLE_RENDER_PIPELINE,
            CompositeState.builder()
                    .setTextureState(new TextureStateShard(resourceLocation, TriState.FALSE, false))
                    .setOutputState(PARTICLES_TARGET)
                    .setLightmapState(LIGHTMAP)
                    .createCompositeState(false)));

    private ModRenderType(String string, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, i, bl, bl2, runnable, runnable2);
    }

    public static RenderType sparkleParticle(ResourceLocation resourceLocation) {
        return SPARKLE_PARTICLE.apply(resourceLocation);
    }
}
