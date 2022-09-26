package fuzs.portablehole;

import fuzs.portablehole.data.*;
import fuzs.portablehole.init.ModRegistryForge;
import fuzs.puzzleslib.core.CoreServices;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

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
        generator.addProvider(true, new ModBlockStateProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModBlockTagsProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator, PortableHole.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModLanguageProvider(generator, PortableHole.MOD_ID));
        generator.addProvider(true, new ModLootTableProvider(generator, PortableHole.MOD_ID));
    }
}
