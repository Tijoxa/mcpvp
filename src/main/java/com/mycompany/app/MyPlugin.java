package com.mycompany.app;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mycompany.app.commands.KitsCommand;
import com.mycompany.app.listeners.DamageReductionListener;
import com.mycompany.app.listeners.FastSoupListener;
import com.mycompany.app.listeners.KangarooOverrideListener;
import com.mycompany.app.recipes.SoupCraft;

public class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info(this.getName() + " has been enabled!");
        registerListeners();
        registerRecipes();
        this.getCommand("kits").setExecutor(new KitsCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(this.getName() + " has been disabled!");
    }

    private void registerListeners() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(new DamageReductionListener(), this);
        manager.registerEvents(new FastSoupListener(), this);
        manager.registerEvents(new KangarooOverrideListener(), this);
    }

    private void registerRecipes() {
        SoupCraft.registerCocoaBeans();
        SoupCraft.registerCactus();
    }

}