package com.mycompany.app.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KitsCommand implements CommandExecutor {

    private static File kitsFile = new File("plugins/Kits/kits.yml"); // Path to external kits.yml

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        List<String> kitNames = getKitNames();
        if (kitNames.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No kits available!");
            return true;
        }

        player.sendMessage(ChatColor.GOLD + "Available Kits: " + ChatColor.AQUA + String.join(", ", kitNames));
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<String> getKitNames() {
        List<String> kitNames = new ArrayList<>();

        if (!kitsFile.exists()) {
            Bukkit.getLogger().severe("[MyPlugin] ERROR: kits.yml not found at " + kitsFile.getAbsolutePath());
            return kitNames;
        }

        try (InputStream inputStream = new FileInputStream(kitsFile)) {
            Yaml yaml = new Yaml();
            Object rawData = yaml.load(inputStream);
            Map<String, Object> data = (Map<String, Object>) rawData;

            if (data == null || !data.containsKey("kits")) {
                Bukkit.getLogger().warning("[MyPlugin] kits.yml does not contain a 'kits' section.");
                return kitNames;
            }

            List<Map<String, Object>> kitsList = (List<Map<String, Object>>) data.get("kits");
            for (Map<String, Object> kit : kitsList) {
                if (kit.containsKey("name")) {
                    String kitName = (String) kit.get("name");
                    kitNames.add(ChatColor.YELLOW + kitName);
                } else {
                    Bukkit.getLogger().warning("[MyPlugin] A kit entry is missing the 'name' field.");
                }
            }
        } catch (Exception e) {
            Bukkit.getLogger().severe("[MyPlugin] ERROR: Failed to read kits.yml!");
            e.printStackTrace();
        }

        return kitNames;
    }

}
