package com.mycompany.app.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class SoupCraft {
    @SuppressWarnings("deprecation")
    public static void registerCocoaBeans() {
        ItemStack mushroomSoup = new ItemStack(Material.MUSHROOM_SOUP, 1);

        ShapelessRecipe recipe = new ShapelessRecipe(mushroomSoup);

        recipe.addIngredient(Material.BOWL);
        recipe.addIngredient(1, Material.INK_SACK, 3);

        Bukkit.addRecipe(recipe);
    }

    public static void registerCactus() {
        ItemStack mushroomSoup = new ItemStack(Material.MUSHROOM_SOUP, 1);

        ShapelessRecipe recipe = new ShapelessRecipe(mushroomSoup);
        recipe.addIngredient(Material.BOWL);
        recipe.addIngredient(Material.CACTUS);
        recipe.addIngredient(Material.CACTUS);

        Bukkit.addRecipe(recipe);
    }
}
