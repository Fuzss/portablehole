package fuzs.portablehole;

import fuzs.portablehole.data.*;
import fuzs.portablehole.init.ModRegistryForge;
import fuzs.puzzleslib.core.CoreServices;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(PortableHole.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PortableHoleForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        CoreServices.FACTORIES.modConstructor(PortableHole.MOD_ID).accept(new PortableHole());
        ModRegistryForge.touch();
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        final ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
        generator.addProvider(new ModBlockStateProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(new ModBlockTagsProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(new ModItemModelProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(new ModLanguageProvider(generator, PortableHole.MOD_ID));
        generator.addProvider(new ModLootTableProvider(generator, PortableHole.MOD_ID));
    }
}
