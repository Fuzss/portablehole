package fuzs.portablehole.client;

import fuzs.portablehole.PortableHole;
import fuzs.portablehole.client.renderer.blockentity.ModRenderType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = PortableHole.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PortableHoleForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(PortableHole.MOD_ID, PortableHoleClient::new);
    }

    @SubscribeEvent
    public static void onRegisterShaders(final RegisterShadersEvent evt) {
        ModRenderType.registerCoreShaders((resourceLocation, vertexFormat, supplier) -> {
            try {
                evt.registerShader(new ShaderInstance(evt.getResourceProvider(), resourceLocation, vertexFormat), supplier);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
