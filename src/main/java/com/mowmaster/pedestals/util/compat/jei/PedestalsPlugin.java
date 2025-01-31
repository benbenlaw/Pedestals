package com.mowmaster.pedestals.util.compat.jei;

import com.mowmaster.pedestals.blocks.PedestalBlock;
import com.mowmaster.pedestals.enchants.EnchantmentRegistry;
import com.mowmaster.pedestals.item.*;
import com.mowmaster.pedestals.item.pedestalFilters.*;
import com.mowmaster.pedestals.item.pedestalUpgrades.*;
import com.mowmaster.pedestals.recipes.*;
import com.mowmaster.pedestals.references.Reference;
import com.mowmaster.pedestals.util.compat.jei.advancedprocessing.CrusherAdvancedRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.advancedprocessing.SawmillAdvancedRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.advancedprocessing.SmeltingAdvancedRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.cobblegen.CobbleGenRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.cobblegensilk.CobbleGenSilkRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.color_pallet.ColorPalletRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.color_pallet.ColorPedestalRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.crusher.CrusherRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.fluid_to_xp.FluidtoExpConverterRecipeCategory;
import com.mowmaster.pedestals.util.compat.jei.sawmill.SawMillRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class PedestalsPlugin implements IModPlugin {

    public static final ResourceLocation PLUGIN_UID = new ResourceLocation(Reference.MODID, "plugin/main");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    private static void addInfoPage(IRecipeRegistration reg, Collection<Item> items, String name) {
        if (items.isEmpty()) return;
        String key = getDescKey(new ResourceLocation(Reference.MODID, name));
        List<ItemStack> stacks = items.stream().map(ItemStack::new).collect(Collectors.toList());
        reg.addIngredientInfo(stacks, VanillaTypes.ITEM, key);
    }

    public static void addValueInfoPage(IRecipeRegistration reg, Item item, String name, Object... values) {
        Collection<Item> items = Collections.singletonList(item);
        addValueInfoPage(reg, items, name, values);
    }

    private static void addValueInfoPage(IRecipeRegistration reg, Collection<Item> items, String name, Object... values) {
        if (items.isEmpty()) return;
        String key = getDescKey(new ResourceLocation(Reference.MODID, name));
        List<ItemStack> stacks = items.stream().map(ItemStack::new).collect(Collectors.toList());
        reg.addIngredientInfo(stacks, VanillaTypes.ITEM, I18n.format(key, values));
    }

    private static String getDescKey(ResourceLocation name) {
        return "jei." + name.getNamespace() + "." + name.getPath() + ".desc";
    }

    private static Item getItemFromIngredient(Ingredient ingredient) {
        return ingredient.getMatchingStacks()[0].getItem();
    }

    public static final IRecipeType<SmeltingRecipeAdvanced> SMELTING_ADVANCED_TYPE = SmeltingRecipeAdvanced.recipeType;
    public static final IRecipeType<CrusherRecipeAdvanced> CRUSHER_ADVANCED_TYPE = CrusherRecipeAdvanced.recipeType;
    public static final IRecipeType<SawMillRecipeAdvanced> SAWING_ADVANCED_TYPE = SawMillRecipeAdvanced.recipeType;
    public static final IRecipeType<CrusherRecipe> CRUSHER_TYPE = CrusherRecipe.recipeType;
    public static final IRecipeType<SawMillRecipe> SAWING_TYPE = SawMillRecipe.recipeType;
    public static final IRecipeType<CobbleGenRecipe> COBBLEGEN_TYPE = CobbleGenRecipe.recipeType;
    public static final IRecipeType<CobbleGenSilkRecipe> COBBLEGENSILK_TYPE = CobbleGenSilkRecipe.recipeType;
    public static final IRecipeType<ColoredPedestalRecipe> COLORING_TYPE = ColoredPedestalRecipe.recipeType;
    public static final IRecipeType<ColoredPedestalRecipe> COLORINGP_TYPE = ColoredPedestalRecipe.recipeType;
    public static final IRecipeType<FluidtoExpConverterRecipe> FLUIDTOXP_TYPE = FluidtoExpConverterRecipe.recipeType;
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(SMELTING_ADVANCED_TYPE), SmeltingAdvancedRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(CRUSHER_ADVANCED_TYPE), CrusherAdvancedRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(SAWING_ADVANCED_TYPE), SawmillAdvancedRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(CRUSHER_TYPE), CrusherRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(SAWING_TYPE), SawMillRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(COBBLEGEN_TYPE), CobbleGenRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(COBBLEGENSILK_TYPE), CobbleGenSilkRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(COLORING_TYPE), ColorPedestalRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(COLORINGP_TYPE), ColorPalletRecipeCategory.UID);
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipesForType(FLUIDTOXP_TYPE), FluidtoExpConverterRecipeCategory.UID);
        //Pedestals
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_000, "pedestal000");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_001, "pedestal001");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_002, "pedestal002");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_003, "pedestal003");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_010, "pedestal010");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_011, "pedestal011");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_012, "pedestal012");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_013, "pedestal013");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_020, "pedestal020");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_021, "pedestal021");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_022, "pedestal022");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_023, "pedestal023");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_030, "pedestal030");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_031, "pedestal031");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_032, "pedestal032");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_033, "pedestal033");

        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_100, "pedestal100");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_101, "pedestal101");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_102, "pedestal102");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_103, "pedestal103");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_110, "pedestal110");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_111, "pedestal111");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_112, "pedestal112");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_113, "pedestal113");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_120, "pedestal120");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_121, "pedestal121");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_122, "pedestal122");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_123, "pedestal123");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_130, "pedestal130");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_131, "pedestal131");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_132, "pedestal132");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_133, "pedestal133");

        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_200, "pedestal200");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_201, "pedestal201");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_202, "pedestal202");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_203, "pedestal203");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_210, "pedestal210");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_211, "pedestal211");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_212, "pedestal212");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_213, "pedestal213");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_220, "pedestal220");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_221, "pedestal221");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_222, "pedestal222");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_223, "pedestal223");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_230, "pedestal230");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_231, "pedestal231");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_232, "pedestal232");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_233, "pedestal233");

        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_300, "pedestal300");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_301, "pedestal301");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_302, "pedestal302");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_303, "pedestal303");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_310, "pedestal310");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_311, "pedestal311");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_312, "pedestal312");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_313, "pedestal313");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_320, "pedestal320");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_321, "pedestal321");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_322, "pedestal322");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_323, "pedestal323");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_330, "pedestal330");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_331, "pedestal331");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_332, "pedestal332");
        addValueInfoPage(registration, PedestalBlock.I_PEDESTAL_333, "pedestal333");
        //Items
        addValueInfoPage(registration, ItemColorPallet.COLORPALLET_DEFAULT, "itemcolorpalletdefault");
        addValueInfoPage(registration, ItemColorPallet.COLORPALLET, "itemcolorpallet");
        addValueInfoPage(registration, ItemCraftingPlaceholder.PLACEHOLDER, "placeholder");
        addValueInfoPage(registration, ItemCraftingPlaceholder.PLACEHOLDER_BUCKET, "placeholderbucket");
        addValueInfoPage(registration, ItemLinkingTool.DEFAULT, "linkingtool");
        addValueInfoPage(registration, ItemLinkingToolBackwards.DEFAULT, "linkingtoolbackwards");
        addValueInfoPage(registration, ItemPedestalUpgrades.SPEED, "upgradespeed");
        addValueInfoPage(registration, ItemPedestalUpgrades.CAPACITY, "upgradecapacity");
        addValueInfoPage(registration, ItemPedestalUpgrades.RANGE, "upgraderange");
        addValueInfoPage(registration, ItemPedestalUpgrades.ROUNDROBIN, "upgraderoundrobin");
        addValueInfoPage(registration, ItemPedestalUpgrades.SOUNDMUFFLER, "upgradesoundmuffler");
        addValueInfoPage(registration, ItemPedestalUpgrades.PARTICLEDIFFUSER, "upgradeparticlediffuser");
        addValueInfoPage(registration, ItemTagTool.TAG, "tagtool");
        addValueInfoPage(registration, ItemUpgradeTool.UPGRADE, "upgradetool");
        addValueInfoPage(registration, ItemToolSwapper.QUARRYTOOL, "toolswapper");
        addValueInfoPage(registration, ItemEnchantableBook.SPEED, "bookspeed");
        addValueInfoPage(registration, ItemEnchantableBook.CAPACITY, "bookcapacity");
        addValueInfoPage(registration, ItemEnchantableBook.RANGE, "bookrange");
        addValueInfoPage(registration, ItemEnchantableBook.AREA, "bookarea");
        addValueInfoPage(registration, ItemEnchantableBook.ADVANCED, "bookadvanced");
        addValueInfoPage(registration, ItemEnchantableBook.MAGNET, "bookmagnet");

        //Upgrades
        addValueInfoPage(registration, ItemUpgradeBreaker.BREAKER, "breaker");
        addValueInfoPage(registration, ItemUpgradeChopper.CHOPPER, "chopper");
        addValueInfoPage(registration, ItemUpgradeChopperShrooms.CHOPPER, "choppershrooms");
        addValueInfoPage(registration, ItemUpgradeCobbleGen.COBBLE, "cobble");
        addValueInfoPage(registration, ItemUpgradeCrafter.CRAFTER_ONE, "crafter1");
        addValueInfoPage(registration, ItemUpgradeCrafter.CRAFTER_TWO, "crafter2");
        addValueInfoPage(registration, ItemUpgradeCrafter.CRAFTER_THREE, "crafter3");
        addValueInfoPage(registration, ItemUpgradeCraftermk2.CRAFTER_ONE, "crafter1mk2");
        addValueInfoPage(registration, ItemUpgradeCraftermk2.CRAFTER_TWO, "crafter2mk2");
        addValueInfoPage(registration, ItemUpgradeCraftermk2.CRAFTER_THREE, "crafter3mk2");
        addValueInfoPage(registration, ItemUpgradeCompactingCrafter.COMPACTOR_TWO, "compactor2");
        addValueInfoPage(registration, ItemUpgradeCompactingCrafter.COMPACTOR_THREE, "compactor3");
        addValueInfoPage(registration, ItemUpgradeCrusher.CRUSHER, "crusher");
        addValueInfoPage(registration, ItemUpgradeDefault.DEFAULT, "default");
        addValueInfoPage(registration, ItemUpgradeDropper.DROPPER, "dropper");
        addValueInfoPage(registration, ItemUpgradeEffectGrower.GROWER, "grower");
        addValueInfoPage(registration, ItemUpgradeEffectHarvester.HARVESTER, "harvester");
        addValueInfoPage(registration, ItemUpgradeHarvesterBeeHives.HARVESTERHIVES, "harvesterhives");
        addValueInfoPage(registration, ItemUpgradeEffectPlanter.PLANTER, "planter");
        addValueInfoPage(registration, ItemUpgradeEffectMagnet.MAGNET, "magnet");
        addValueInfoPage(registration, ItemUpgradeEnergyExport.RFEXPORT, "rfexport");
        addValueInfoPage(registration, ItemUpgradeEnergyGeneratorExp.RFEXPGEN, "rfexpgen");
        addValueInfoPage(registration, ItemUpgradeEnergyGenerator.RFFUELGEN, "rffuelgen");
        addValueInfoPage(registration, ItemUpgradeEnergyImport.RFIMPORT, "rfimport");
        addValueInfoPage(registration, ItemUpgradeEnergyRelay.RFRELAY, "rfrelay");
        addValueInfoPage(registration, ItemUpgradeEnergyTank.RFTANK, "rftank");
        addValueInfoPage(registration, ItemUpgradeEnergyCrusher.RFCRUSHER, "rfcrusher");
        addValueInfoPage(registration, ItemUpgradeEnergyFurnace.RFSMELTER, "rfsmelter");
        addValueInfoPage(registration, ItemUpgradeEnergySawMill.RFSAWMILL, "rfsawmill");
        addValueInfoPage(registration, ItemUpgradeExpCollector.XPMAGNET, "xpmagnet");
        addValueInfoPage(registration, ItemUpgradeExpRelay.XPRELAY, "xprelay");
        addValueInfoPage(registration, ItemUpgradeExpTank.XPTANK, "xptank");
        addValueInfoPage(registration, ItemUpgradeExpBottler.XPBOTTLER, "xpbottler");
        addValueInfoPage(registration, ItemUpgradeExpDropper.XPDROPPER, "xpdropper");
        addValueInfoPage(registration, ItemUpgradeExpEnchanter.XPENCHANTER, "xpenchanter");
        addValueInfoPage(registration, ItemUpgradeExpGrindstone.XPGRINDSTONE, "xpgrindstone");
        addValueInfoPage(registration, ItemUpgradeExpAnvil.XPANVIL, "xpanvil");
        addValueInfoPage(registration, ItemUpgradeRecycler.RECYCLER, "recycler");
        addValueInfoPage(registration, ItemUpgradeFluidImport.FLUIDIMPORT, "fluidimport");
        addValueInfoPage(registration, ItemUpgradeFluidExport.FLUIDEXPORT, "fluidexport");
        addValueInfoPage(registration, ItemUpgradeFluidPump.FLUIDPUMP, "fluidpump");
        addValueInfoPage(registration, ItemUpgradeFluidDrain.FLUIDDRAIN, "fluiddrain");
        addValueInfoPage(registration, ItemUpgradeFluidTank.FLUIDTANK, "fluidtank");
        addValueInfoPage(registration, ItemUpgradeFluidRelay.FLUIDRELAY, "fluidrelay");
        addValueInfoPage(registration, ItemUpgradeFluidFilteredImport.FLUIDFILTEREDIMPORT, "fluidfilteredimport");
        addValueInfoPage(registration, ItemUpgradeFluidCrafter.FLUIDCRAFTER_ONE, "fluidcrafter1");
        addValueInfoPage(registration, ItemUpgradeFluidCrafter.FLUIDCRAFTER_TWO, "fluidcrafter2");
        addValueInfoPage(registration, ItemUpgradeFluidCrafter.FLUIDCRAFTER_THREE, "fluidcrafter3");
        addValueInfoPage(registration, ItemUpgradeFilteredImport.FIMPORT, "fimport");
        addValueInfoPage(registration, ItemUpgradeImport.IMPORT, "import");
        addValueInfoPage(registration, ItemUpgradeExport.EXPORT, "export");
        addValueInfoPage(registration, ItemUpgradeRestock.RESTOCK, "restock");
        addValueInfoPage(registration, ItemUpgradeFilteredRestock.FRESTOCK, "frestock");
        addValueInfoPage(registration, ItemUpgradeItemTank.ITEMTANK, "itemtank");
        addValueInfoPage(registration, ItemUpgradeFurnace.SMELTER, "smelter");
        addValueInfoPage(registration, ItemUpgradeSawMill.SAWMILL, "sawmill");
        addValueInfoPage(registration, ItemUpgradeQuarry.QUARRY, "quarry");
        addValueInfoPage(registration, ItemUpgradeMilker.MILKER, "milker");
        addValueInfoPage(registration, ItemUpgradeShearer.SHEARER, "shearer");
        addValueInfoPage(registration, ItemUpgradePlacer.PLACER, "placer");
        addValueInfoPage(registration, ItemUpgradeTeleporter.TELEPORTER, "teleporter");
        addValueInfoPage(registration, ItemUpgradeVoid.VOID, "void");
        addValueInfoPage(registration, ItemUpgradeEnergyVoid.VOIDENERGY, "voidenergy");
        addValueInfoPage(registration, ItemUpgradeFluidVoid.VOIDFLUID, "voidfluid");
        addValueInfoPage(registration, ItemUpgradeAttacker.ATTACK, "attack");
        addValueInfoPage(registration, ItemUpgradeEffect.EFFECT, "effect");
        addValueInfoPage(registration, ItemUpgradeFan.FAN, "fan");
        addValueInfoPage(registration, ItemUpgradeEnderExporter.ENDEREXPORT, "enderexport");
        addValueInfoPage(registration, ItemUpgradeEnderFilteredExporter.ENDERFEXPORT, "enderfilteredexport");
        addValueInfoPage(registration, ItemUpgradeEnderImporter.ENDERIMPORT, "enderimport");
        addValueInfoPage(registration, ItemUpgradeEnderFilteredRestock.ENDERFRESTOCK, "enderfilteredrestock");

        addValueInfoPage(registration, ItemFilterSwapper.FILTERTOOL, "filterswapper");
        addValueInfoPage(registration, ItemFilterBase.BASEFILTER, "filterbase");
        addValueInfoPage(registration, ItemFilterItem.ITEMFILTER, "filteritem");
        addValueInfoPage(registration, ItemFilterItemStack.ITEMSTACKFILTER, "filteritemstack");
        addValueInfoPage(registration, ItemFilterMod.MODFILTER, "filtermod");
        addValueInfoPage(registration, ItemFilterTag.TAGFILTER, "filtertag");
        addValueInfoPage(registration, ItemFilterFood.FOODFILTER, "filterfood");
        addValueInfoPage(registration, ItemFilterEnchanted.ENCHANTEDFILTER, "filterenchanted");
        addValueInfoPage(registration, ItemFilterEnchantedFuzzy.ENCHANTEDFUZZYFILTER, "filterenchantedfuzzy");
        addValueInfoPage(registration, ItemFilterEnchantedExact.ENCHANTEDEXACTFILTER, "filterenchantedexact");
        addValueInfoPage(registration, ItemFilterEnchantedCount.ENCHANTEDCOUNTFILTER, "filterenchantedcount");
        addValueInfoPage(registration, ItemFilterDurability.DURABILITYFILTER, "filterdurability");
        addValueInfoPage(registration, ItemFilterRestricted.RESTRICTIONFILTER, "filterrestriction");
        addValueInfoPage(registration, ItemCraftingPattern.CRAFTINGPATTERN, "craftingpattern");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new SmeltingAdvancedRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CrusherAdvancedRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SawmillAdvancedRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CrusherRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SawMillRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CobbleGenRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CobbleGenSilkRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ColorPedestalRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ColorPalletRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new FluidtoExpConverterRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        //Crafting-Anvil
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeCrafter.CRAFTER_THREE), VanillaRecipeCategoryUid.CRAFTING);
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeExpAnvil.XPANVIL), VanillaRecipeCategoryUid.ANVIL);
        //Crusher
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeCrusher.CRUSHER), CrusherRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeEnergyCrusher.RFCRUSHER), CrusherRecipeCategory.UID);
            ItemStack getCrusher = new ItemStack(ItemUpgradeCrusher.CRUSHER.getItem());
            getCrusher.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getCrusher, CrusherAdvancedRecipeCategory.UID);
            ItemStack getCrusherRF = new ItemStack(ItemUpgradeEnergyCrusher.RFCRUSHER.getItem());
            getCrusherRF.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getCrusherRF, CrusherAdvancedRecipeCategory.UID);
        //Sawmill
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeSawMill.SAWMILL), SawMillRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeEnergySawMill.RFSAWMILL), SawMillRecipeCategory.UID);
            ItemStack getSawmill = new ItemStack(ItemUpgradeSawMill.SAWMILL.getItem());
            getSawmill.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getSawmill, SawmillAdvancedRecipeCategory.UID);
            ItemStack getSawmillRF = new ItemStack(ItemUpgradeEnergySawMill.RFSAWMILL.getItem());
            getSawmillRF.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getSawmillRF, SawmillAdvancedRecipeCategory.UID);
        //Smelter
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeFurnace.SMELTER), VanillaRecipeCategoryUid.FURNACE);
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeEnergyFurnace.RFSMELTER), VanillaRecipeCategoryUid.FURNACE);
            ItemStack getSmelter = new ItemStack(ItemUpgradeFurnace.SMELTER.getItem());
            getSmelter.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getSmelter, SmeltingAdvancedRecipeCategory.UID);
            ItemStack getSmelterRF = new ItemStack(ItemUpgradeEnergyFurnace.RFSMELTER.getItem());
            getSmelterRF.addEnchantment(EnchantmentRegistry.ADVANCED,1);
        registration.addRecipeCatalyst(getSmelterRF, SmeltingAdvancedRecipeCategory.UID);
        //Colored Pedestals
        registration.addRecipeCatalyst(new ItemStack(ItemColorPallet.COLORPALLET), ColorPedestalRecipeCategory.UID);
        //Color Pallets
        registration.addRecipeCatalyst(new ItemStack(ItemLinkingTool.DEFAULT), ColorPalletRecipeCategory.UID);
        //Cobble Gen
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeCobbleGen.COBBLE), CobbleGenRecipeCategory.UID);
        //Cobble Gen Silk
        ItemStack getStack = new ItemStack(ItemUpgradeCobbleGen.COBBLE.getItem());
        getStack.addEnchantment(Enchantments.SILK_TOUCH,1);
        registration.addRecipeCatalyst(getStack, CobbleGenSilkRecipeCategory.UID);
        //Fluid To XP
        registration.addRecipeCatalyst(new ItemStack(ItemUpgradeExpFluidConverter.FLUIDXPCONVERTER), FluidtoExpConverterRecipeCategory.UID);
    }





}
