package me.jophestus.JOPHPromote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class JOPHPromoteListener implements Listener {
	public static JOPHPromote plugin;

	public JOPHPromoteListener(JOPHPromote instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent chat) {
		
		String message = chat.getMessage();
		Player p = chat.getPlayer();
		ChatColor RED = ChatColor.RED;
		ChatColor GREEN = ChatColor.GREEN;
		String group = plugin.getConfig().getString("groupname");
		String phrase = plugin.getConfig().getString("rankupmessage").trim();
		String groupname = plugin.getConfig().getString("announcegroupname")
				.trim();
		if (message.equalsIgnoreCase(phrase)) {
			if (!p.hasPermission("JOPHPromote.norankup")) {
				JOPHPromote.perms.playerAddGroup(p, group);
				p.sendMessage(RED + "[JOPHPromote] " + GREEN
						+ "Congratulations " + p.getName() + "."
						+ " You are now " + groupname);

				Bukkit.broadcastMessage(ChatColor.WHITE + p.getName()
						+ ChatColor.GOLD + " has been ranked to " + groupname
						+ "! ");
				Bukkit.broadcastMessage(ChatColor.AQUA + "Congratulations "
						+ ChatColor.WHITE + p.getName() + "!");
				if (plugin.getConfig().getBoolean("givetools")) {
					PlayerInventory inventory = p.getInventory();
					ItemStack torch = new ItemStack(Material.TORCH, 5);
					ItemStack pick = new ItemStack(Material.STONE_PICKAXE, 1);
					ItemStack spade = new ItemStack(Material.STONE_SPADE, 1);
					ItemStack axe = new ItemStack(Material.STONE_AXE, 1);
					ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
					inventory.addItem(torch);
					inventory.addItem(pick);
					inventory.addItem(axe);
					inventory.addItem(spade);
					inventory.addItem(sword);
				}
				chat.setCancelled(true);
			} else {
				p.sendMessage(RED + "[JOPHPromote] " + GREEN + "I'm sorry "
						+ p.getName() + ", You can't do that.");
				chat.setCancelled(true);
			}
		}
	}
}