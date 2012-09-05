package me.jophestus.JOPHPromote;
 
import java.io.File;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;
 
public class JOPHPromote extends JavaPlugin
{
  PluginDescriptionFile pdfFile;
  public static JOPHPromote plugin;
  public final Logger log = Logger.getLogger("Minecraft");
  public final JOPHPromoteListener Listener = new JOPHPromoteListener(this);
  public static Permission perms = null;
 
  public void onDisable()
  {
	  log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
	  
  }
 
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if (command.getName().equalsIgnoreCase("JOPHPromote")) {
        if(args.length==1){
            if(args[0].equalsIgnoreCase("reload")){
                reloadConfig();
          
            }
        }else{
         
     
      sender.sendMessage(ChatColor.DARK_GREEN +
        "+------------------------------+");
      sender.sendMessage(ChatColor.DARK_AQUA + "JOPHPromote: Player promotion plugin.");
      sender.sendMessage(ChatColor.GREEN + "By JOPHESTUS");
      sender.sendMessage(ChatColor.YELLOW + "Version:" + "2.2");
      sender.sendMessage(ChatColor.DARK_GREEN +
        "+------------------------------+");
      
      
        }
    }
 
    return super.onCommand(sender, command, label, args);
  }
 
  public void onEnable()
  {
    this.pdfFile = getDescription();
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this.Listener, this);
    this.log.info(this.pdfFile.getName() + " is now enabled!.");
    setupPermissions();
    if (!new File(getDataFolder(), "config.yml").exists())
      SetupConfig();
  }
 
  private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer()
				.getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
}

public void SetupConfig()
  {
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
  }
}