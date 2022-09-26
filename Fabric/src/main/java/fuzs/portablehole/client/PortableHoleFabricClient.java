package fuzs.portablehole.client;

import fuzs.portablehole.PortableHole;
import fuzs.puzzleslib.client.core.ClientCoreServices;
import net.fabricmc.api.ClientModInitializer;

public class PortableHoleFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCoreServices.FACTORIES.clientModConstructor(PortableHole.MOD_ID).accept(new PortableHoleClient());
    }
}
