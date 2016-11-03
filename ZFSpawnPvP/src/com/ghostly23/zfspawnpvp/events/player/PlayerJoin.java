package com.ghostly23.zfspawnpvp.events.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.ghostly23.zfspawnpvp.ZFSpawnPvP;

public class PlayerJoin implements Listener {
	
	 ZFSpawnPvP plugin;
	 
	 public PlayerJoin (ZFSpawnPvP plugin){
		 this.plugin = plugin;
	 }

	 @EventHandler
	 private void onPlayerJoin(PlayerJoinEvent event){
		 Player player = event.getPlayer();
		 if(checkIfTeam(player) == true){
			 player.sendMessage("test");
			 String team = plugin.getdataConfig().getString("pvp.player." + player.getName() + ".team");
			 int x = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.x");
			 int y = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.y");
			 int z = plugin.getdataConfig().getInt("pvp.positions.team." + team + ".base.z");
			 World world = plugin.getServer().getWorld(plugin.getdataConfig().getString("pvp.positions.team." + team + ".base.world"));
			 player.teleport(new Location(world, x, y, z));
		 }
		 else{
			 
			 Inventory inv = Bukkit.createInventory(null, 9, "Team Auswahl");
			 ItemStack nord = nameItem(new ItemStack(Material.WOOL, 1, (short) 13), ChatColor.GREEN + "Nord", "Die Himmelsinsel");
			 ItemStack ost = nameItem(new ItemStack(Material.WOOL, 1, (short) 6), ChatColor.LIGHT_PURPLE + "Ost", "Die Wüste");
			 ItemStack sued = nameItem(new ItemStack(Material.WOOL, 1, (short) 3), ChatColor.AQUA + "Süd", "Die See");
			 ItemStack west = nameItem(new ItemStack(Material.WOOL, 1, (short) 1), ChatColor.GOLD + "West", "Die Berge");
			 
			 inv.setItem(1, nord);
			 inv.setItem(3, ost);
			 inv.setItem(5, sued);
			 inv.setItem(7, west);
			 
				BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
				scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
					
				@Override
				public void run() {
			 
			 player.openInventory(inv);
				}
				}, 2);
		 }
	 }
	 
	 private boolean checkIfTeam(Player player){
		 Object o = plugin.getdataConfig().get("pvp.player." + player.getName());
		 if(o == null){
			 player.sendMessage("You are in no team. Please select one.");
			 return false;
		 }
		 return true;
	 }
	 
	 private ItemStack nameItem(ItemStack item, String name, String lore){
		 ItemMeta meta = item.getItemMeta();
		 meta.setDisplayName(name);
		 List<String> lorelist = new ArrayList<String>();
		 lorelist.add(lore);
		 meta.setLore(lorelist);
		 item.setItemMeta(meta);
		 return item;
	 }
	 
	 
}
