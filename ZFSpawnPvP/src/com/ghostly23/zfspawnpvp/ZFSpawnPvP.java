package com.ghostly23.zfspawnpvp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import com.ghostly23.zfspawnpvp.commands.SetFlagPos;
import com.ghostly23.zfspawnpvp.events.inventory.InventoryClick;
import com.ghostly23.zfspawnpvp.events.player.PlayerJoin;

public class ZFSpawnPvP extends JavaPlugin {
	
	public File configf, dataf;
	public FileConfiguration config;
	public FileConfiguration data;
	
		public void onEnable(){
			createFiles();
			registerCommands();
			registerEvents();
		}
		
		public void onDisable(){
			
		}
		
		
		
		
		public FileConfiguration getdataConfig(){
			return this.data;
		}
		
		public void savedataConfig(){
			try {
				getdataConfig().save(dataf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void createFiles(){
			configf = new File(getDataFolder(), "config.yml");
			dataf = new File(getDataFolder(), "data.yml");
			
			if(!configf.exists()){
				configf.getParentFile().mkdirs();
				saveResource("config.yml", false);
			}
			
			if(!dataf.exists()){
				dataf.getParentFile().mkdirs();
				saveResource("data.yml", false);
			}
			
			
			config = new YamlConfiguration();
			data = new YamlConfiguration();
			try {
				try {
					config.load(configf);
				} catch (InvalidConfigurationException e1) {
					e1.printStackTrace();
				}
				try {
					data.load(dataf);
				} catch (InvalidConfigurationException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void registerCommands(){
			getCommand("setflagpos").setExecutor(new SetFlagPos(this));
		}
		
		public void registerEvents(){
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new PlayerJoin(this), this);
			pm.registerEvents(new InventoryClick(this), this);
		}
		
		
}
