package fuzs.portablehole.init;

import fuzs.portablehole.PortableHole;
import fuzs.portablehole.world.level.block.TemporaryHoleForgeBlock;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.puzzleslib.init.RegistryReference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModRegistryForge {
    private static final RegistryManager REGISTRY = CoreServices.FACTORIES.registration(PortableHole.MOD_ID);
    public static final RegistryReference<Block> TEMPORARY_HOLE_BLOCK = REGISTRY.registerBlock("temporary_hole", () -> new TemporaryHoleForgeBlock(BlockBehaviour.Properties.of(Material.PORTAL, MaterialColor.COLOR_BLACK).noCollission().lightLevel((p_50854_) -> {
        return 15;
    }).strength(-1.0F, 3600000.0F).noDrops()));

    public static void touch() {

    }
}
