package com.ghostly23.zfspawnpvp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ghostly23.zfspawnpvp.ZFSpawnPvP;

public class SetFlagPos implements CommandExecutor {

	ZFSpawnPvP plugin;


	public SetFlagPos(ZFSpawnPvP plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, 
			String arg, String[] args) {
		
		
		if(args.length != 2){
			sender.sendMessage(ChatColor.RED + "Usage:");
			sender.sendMessage(ChatColor.GRAY + "/setflagpos mid/nord/ost/sued/west base/t1-3");
			return false;
		}
		 List<String> teams = new ArrayList<String>();
		 teams.add("mid");
		 teams.add("nord");
		 teams.add("ost");
		 teams.add("sued");
		 teams.add("west");
		if(!(teams.contains(args[0]))){
			sender.sendMessage(ChatColor.RED + "Not a valid Team.");
			sender.sendMessage(ChatColor.GRAY + "Valid Teams are: mid, nord, ost, sued or west");
			return false;
		}
		List<String> bases = new ArrayList<String>();
		bases.add("base");
		bases.add("t1");
		bases.add("t2");
		bases.add("t3");
		if(!(bases.contains(args[1]))){
			sender.sendMessage(ChatColor.RED + "Not a valid flag point");
			sender.sendMessage(ChatColor.GRAY + "Valid flag points are: base or t1-3");
			return false;
		}
		
		if(!(sender instanceof Player)){
			sender.sendMessage("A console can't select a block with its eyes");
			return false;
		}
		
		
		Player player = (Player) sender;
		
		String world = player.getTargetBlock((Set<Material>)null, 100).getWorld().getName();
		int x = player.getTargetBlock((Set<Material>)null, 100).getX();
		int y = player.getTargetBlock((Set<Material>)null, 100).getY() + 1;
		int z = player.getTargetBlock((Set<Material>)null, 100).getZ();
		plugin.getdataConfig().set("pvp.positions.team." + args[0] + "." + args[1] + ".x", x);
		plugin.getdataConfig().set("pvp.positions.team." + args[0] + "." + args[1] + ".y", y);
		plugin.getdataConfig().set("pvp.positions.team." + args[0] + "." + args[1] + ".z", z);
		plugin.getdataConfig().set("pvp.positions.team." + args[0] + "." + args[1] + ".world", world);
		plugin.savedataConfig();
		
		
		sender.sendMessage(ChatColor.BLUE + "Flag position set for:" + ChatColor.GOLD + args[0]);
		return true;
	}

}
