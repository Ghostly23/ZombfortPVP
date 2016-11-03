package com.ghostly23.zfspawnpvp.events.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.ghostly23.zfspawnpvp.ZFSpawnPvP;

public class InventoryClick implements Listener {
	
	ZFSpawnPvP plugin;
	 
	 public InventoryClick (ZFSpawnPvP plugin){
		 this.plugin = plugin;
	 }

	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Inventory inv = event.getInventory();
		if(!(inv.getTitle().equals("Team Auswahl")))return;
		
		if(!(event.getWhoClicked() instanceof Player))return;
		
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		
		if(item.getType() == Material.AIR)return;
		
		if(item.getItemMeta().getDisplayName() == ChatColor.GREEN + "Nord"){
			player.sendMessage("Team ausgewählt");
			plugin.getdataConfig().set("pvp.teams.nord.member", plugin.getdataConfig().getInt("pvp.teams.nord.member") + 1);
			plugin.getdataConfig().set("pvp.player." + event.getWhoClicked().getName() + ".team", "nord");
			plugin.savedataConfig();
			
			String team = plugin.getdataConfig().getString("pvp.player." + player.getName() + ".team");
			 int x = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.x");
			 int y = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.y");
			 int z = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.z");
			 World world = plugin.getServer().getWorld(plugin.getdataConfig().getString("pvp.positions.team." + team + ".base.world"));
			 player.teleport(new Location(world, x, y, z));
			 event.setCancelled(true);
			 player.closeInventory();
			 return;
		}
		if(item.getItemMeta().getDisplayName() == ChatColor.LIGHT_PURPLE + "Ost"){
			plugin.getdataConfig().set("pvp.teams.ost.member", plugin.getdataConfig().getInt("pvp.teams.ost.member") + 1);
			plugin.getdataConfig().set("pvp.player." + event.getWhoClicked().getName() + ".team", "ost");
			plugin.savedataConfig();
			String team = plugin.getdataConfig().getString("pvp.player." + player.getName() + ".team");
			 int x = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.x");
			 int y = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.y");
			 int z = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.z");
			 World world = plugin.getServer().getWorld(plugin.getdataConfig().getString("pvp.positions.team." + team + ".base.world"));
			 player.teleport(new Location(world, x, y, z));
			 event.setCancelled(true);
			 player.closeInventory();
			 return;
		}
		if(item.getItemMeta().getDisplayName() == ChatColor.AQUA + "Süd"){
			plugin.getdataConfig().set("pvp.teams.sued.member", plugin.getdataConfig().getInt("pvp.teams.sued.member") + 1);
			plugin.getdataConfig().set("pvp.player." + event.getWhoClicked().getName() + ".team", "sued");
			plugin.savedataConfig();
			String team = plugin.getdataConfig().getString("pvp.player." + player.getName() + ".team");
			 int x = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.x");
			 int y = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.y");
			 int z = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.z");
			 World world = plugin.getServer().getWorld(plugin.getdataConfig().getString("pvp.positions.team." + team + ".base.world"));
			 player.teleport(new Location(world, x, y, z));
			 event.setCancelled(true);
			 player.closeInventory();
			 return;
		}
		if(item.getItemMeta().getDisplayName() == ChatColor.GOLD + "West"){
			plugin.getdataConfig().set("pvp.teams.west.member", plugin.getdataConfig().getInt("pvp.teams.west.member") + 1);
			plugin.getdataConfig().set("pvp.player." + event.getWhoClicked().getName() + ".team", "west");
			plugin.savedataConfig();
			String team = plugin.getdataConfig().getString("pvp.player." + player.getName() + ".team");
			 int x = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.x");
			 int y = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.y");
			 int z = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.z");
			 World world = plugin.getServer().getWorld(plugin.getdataConfig().getString("pvp.positions.team." + team + ".base.world"));
			 player.teleport(new Location(world, x, y, z));
			 event.setCancelled(true);
			 player.closeInventory();
			 return;
		}
		
		event.setCancelled(true);
		
	}
}
