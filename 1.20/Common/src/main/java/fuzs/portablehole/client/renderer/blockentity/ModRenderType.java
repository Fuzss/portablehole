package fuzs.portablehole.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import fuzs.portablehole.PortableHole;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public abstract class ModRenderType extends RenderType {
    private static final RenderStateShard.ShaderStateShard RENDERTYPE_TEMPORARY_HOLE_SHADER = new RenderStateShard.ShaderStateShard(() -> ModRenderType.rendertypeTemporaryHoleShader);
    public static final RenderType TEMPORARY_HOLE = create("temporary_hole", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_TEMPORARY_HOLE_SHADER).setTextureState(MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));

    @Nullable
    private static ShaderInstance rendertypeTemporaryHoleShader;

    public ModRenderType(String string, VertexFormat vertexFormat, VertexFormat.Mode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
    }

    public static void registerCoreShaders(RegistrationProvider registrationProvider) {
        registrationProvider.accept(PortableHole.id("rendertype_temporary_hole"), DefaultVertexFormat.POSITION, shaderInstance -> {
            rendertypeTemporaryHoleShader = shaderInstance;
        });
    }

    @FunctionalInterface
    public interface RegistrationProvider {

        void accept(ResourceLocation resourceLocation, VertexFormat vertexFormat, Consumer<ShaderInstance> supplier);
    }
}
