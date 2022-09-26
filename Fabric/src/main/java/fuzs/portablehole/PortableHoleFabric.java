package fuzs.portablehole;

import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;

public class PortableHoleFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CoreServices.FACTORIES.modConstructor(PortableHole.MOD_ID).accept(new PortableHole());
    }
}
