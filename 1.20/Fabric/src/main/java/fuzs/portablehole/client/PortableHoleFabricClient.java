package fuzs.portablehole.client;

import fuzs.portablehole.PortableHole;
import fuzs.portablehole.client.renderer.blockentity.ModRenderType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;

import java.io.IOException;

public class PortableHoleFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(PortableHole.MOD_ID, PortableHoleClient::new);
        registerHandlers();
    }

    private static void registerHandlers() {
        CoreShaderRegistrationCallback.EVENT.register(context -> {
            ModRenderType.registerCoreShaders((id, vertexFormat, loadCallback) -> {
                try {
                    context.register(id, vertexFormat, loadCallback);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
}
