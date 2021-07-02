package me.shizleshizle.core.utils;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandManager {
    private HashMap<String, CommandExecutor> commands = new HashMap<>();
    private ArrayList<Listener> events = new ArrayList<>();
    private Main core;

    public CommandManager(Main core) {
        this.core = core;
        Fetch();
    }

    public boolean isEnabled(String commandLabel) {
        return commands.containsKey(commandLabel);
    }

    public void registerCommand(String commandLabel, CommandExecutor exec) {
        if (!commands.containsKey(commandLabel)) {
            commands.put(commandLabel, exec);
        }
    }

    public void unregisterCommand(String commandLabel) {
        if (commands.containsKey((commandLabel))) {
            commands.remove(commandLabel);
        }
    }

    public void registerEvent(Listener event) {
        events.add(event);
    }

    public void registerToServer() {
        registerCommandsToServer();
        registerEventsToServer();
    }

    private void registerCommandsToServer() {
        for (String command : commands.keySet()) {
            Objects.requireNonNull(core.getCommand(command)).setExecutor(commands.get(command));
        }
    }

    private void registerEventsToServer() {
        PluginManager pm = core.getServer().getPluginManager();
        for (Listener event : events) {
            pm.registerEvents(event, core);
        }
    }

    private void Fetch() {
        SmartFetchCommands();
        FetchEvents();
    }

    private String getPath(String cmdName) {
        String path = "me.shizleshizle.core.commands";
        if (cmdName.equalsIgnoreCase("ban")||cmdName.equalsIgnoreCase("unban")||cmdName.equalsIgnoreCase("kick")||cmdName.equalsIgnoreCase("mute")
            ||cmdName.equalsIgnoreCase("staffmode")||cmdName.equalsIgnoreCase("unmute")) {
            path = path + ".bansystem";
        } else if (cmdName.equalsIgnoreCase("unloadchunks")||cmdName.equalsIgnoreCase("clearentities")) {
            path = path + ".clearlag";
        } else if (cmdName.contains("home")) {
            path = path + ".homes";
        } else if (cmdName.equalsIgnoreCase("msg")||cmdName.equalsIgnoreCase("reply")||cmdName.equalsIgnoreCase("socialspy")) {
            path = path + ".messaging";
        } else if ((cmdName.contains("spawn")||cmdName.equalsIgnoreCase("spawn")) && !cmdName.equalsIgnoreCase("spawnmob")) {
            path = path + ".spawn";
        } else if (cmdName.startsWith("tp")) {
            path = path + ".teleportation";
        } else if (cmdName.contains("ticket")||cmdName.equalsIgnoreCase("ticket")||cmdName.equalsIgnoreCase("tickets")) {
            path = path + ".tickets";
        } else if (cmdName.equalsIgnoreCase("day")||cmdName.equalsIgnoreCase("night")||cmdName.equalsIgnoreCase("ptime")
                ||cmdName.equalsIgnoreCase("time")) {
            path = path + ".time";
        } else if (cmdName.equalsIgnoreCase("vault")) {
            path = path + ".vaults";
        } else if (cmdName.contains("warp")||cmdName.equalsIgnoreCase("warp")) {
            path = path + ".warps";
        }
        return path;
    }

    private void SmartFetchCommands() {
        Map<String, Map<String, Object>> cmds = core.getDescription().getCommands();
        Bukkit.getLogger().info(cmds.keySet().toString());
        for (String cmd : cmds.keySet()) {
            if ((cmd.equalsIgnoreCase("pay")||cmd.equalsIgnoreCase("balance")) && !core.checkVault()) continue;
            String pathToClass = getPath(cmd);
            String className = StringHelper.normalCase(cmd);
            if (cmd.equalsIgnoreCase("sun") || cmd.equalsIgnoreCase("storm")) {
                className = "Weather";
            } else if (cmd.startsWith("gm")) {
                className = "Gamemode";
            }
            try {
                Class clazz = Class.forName(pathToClass + "." + className);
                if (CommandExecutor.class.isAssignableFrom(clazz)) {
                    CommandExecutor exe = (CommandExecutor) clazz.getDeclaredConstructor().newInstance();
                    registerCommand(cmd, exe);
                }
            } catch(ClassNotFoundException | NoSuchMethodException|InvocationTargetException|InstantiationException|IllegalAccessException e) {
                Bukkit.getLogger().info("Core >> Error: " + e);
            }
        }
    }

    private void FetchCommands() {
        /*
        // ban
        String className = "Ban";
        try {
            Class c = Class.forName("me.shizleshizle.core.commands.bansystem." + className);
            if (CommandExecutor.class.isAssignableFrom(c)) {
                CommandExecutor ce = (CommandExecutor) c.getDeclaredConstructor().newInstance();
                registerCommand("ban", ce);
                Bukkit.getLogger().info("wow! it works!");
            }
        } catch(ClassNotFoundException | NoSuchMethodException|InvocationTargetException|InstantiationException|IllegalAccessException e) {
            Bukkit.getLogger().info("Core >> Error: " + e);
        }
        //registerCommand("ban", new Ban());
        registerCommand("ban", new Ban());
        registerCommand("unban", new Unban());
        registerCommand("kick", new Kick());
        registerCommand("mute", new Mute());
        registerCommand("staffmode", new Staffmode());
        registerCommand("unmute", new Unmute());

        // clearlag
        registerCommand("unloadchunks", new Unloadchunks());
        registerCommand("clearentities", new Clearentities());

        //home
        registerCommand("delhome", new Delhome());
        registerCommand("home", new Home());
        registerCommand("sethome", new Sethome());

        // messaging
        registerCommand("msg", new Msg());
        registerCommand("reply", new Reply());
        registerCommand("socialspy", new Socialspy());

        // spawn
        registerCommand("spawn", new Spawn());
        registerCommand("setspawn", new Spawn());
        registerCommand("removespawn", new Spawn());

        // teleportation
        registerCommand("tp", new Tp());
        registerCommand("tpa", new Tpa());
        registerCommand("tpaccept", new Tpaccept());
        registerCommand("tpahere", new Tpahere());
        registerCommand("tpall", new Tpall());
        registerCommand("tpdeny", new Tpdeny());
        registerCommand("tphere", new Tphere());
        registerCommand("tpo", new Tpo());
        registerCommand("tpohere", new Tpohere());
        registerCommand("tppos", new Tppos());
        registerCommand("tptoggle", new Tptoggle());

        // tickets
        registerCommand("checkticket", new Checkticket());
        registerCommand("closeticket", new Closeticket());
        registerCommand("taketicket", new Taketicket());
        registerCommand("ticket", new Ticket());
        registerCommand("tickets", new Tickets());

        // time
        registerCommand("time", new Time());
        registerCommand("day", new Day());
        registerCommand("night", new Night());
        registerCommand("ptime", new Ptime());

        // vault
        registerCommand("playervaults", new Vault());

        // warps
        registerCommand("removewarp", new Deletewarps());
        registerCommand("setwarp", new Setwarps());
        registerCommand("warp", new Warp());

        // weather
        registerCommand("weather", new Weather());
        registerCommand("sun", new Weather());
        registerCommand("storm", new Weather());

        // regular
        registerCommand("autobroadcaster", new Autobroadcaster());
        registerCommand("afk", new Afk());
        registerCommand("back", new Back());
        registerCommand("broadcast", new Broadcast());
        registerCommand("chatcolor", new Chatcolor());
        registerCommand("clearchat", new Clearchat());
        registerCommand("clearinventory", new Clearinventory());
        registerCommand("commandspy", new Commandspy());
        registerCommand("enchant", new Enchant());
        registerCommand("enderchest", new Enderchest());
        registerCommand("feed", new Feed());
        registerCommand("fly", new Fly());
        registerCommand("freeze", new Freeze());
        registerCommand("gamemode", new Gamemode());
        registerCommand("gms", new Gamemode());
        registerCommand("gmc", new Gamemode());
        registerCommand("gma", new Gamemode());
        registerCommand("gmsp", new Gamemode());
        registerCommand("getpos", new Getpos());
        registerCommand("god", new God());
        registerCommand("hat", new Hat());
        registerCommand("heal", new Heal());
        registerCommand("help", new Help());
        registerCommand("Invsee", new Invsee());
        registerCommand("item", new Item());
        registerCommand("kill", new Kill());
        registerCommand("killmobs", new Killmobs());
        registerCommand("list", new List());
        registerCommand("maintenance", new Maintenance());
        registerCommand("mccore", new Mccore());
        registerCommand("me", new Me());
        registerCommand("mods", new Mods());
        registerCommand("motd", new Motd());
        registerCommand("name", new Name());
        registerCommand("nickname", new Nickname());
        registerCommand("permission", new Permission());
        registerCommand("prefix", new Prefix());
        registerCommand("rank", new Rank());
        registerCommand("repair", new Repair());
        registerCommand("rules", new Rules());
        registerCommand("socialmedia", new Socialmedia());
        registerCommand("skull", new Skull());
        registerCommand("smelt", new Smelt());
        registerCommand("spawnmob", new Spawnmob());
        registerCommand("speed", new Speed());
        registerCommand("staffchat", new Staffchat());
        registerCommand("staff", new Staff());
        registerCommand("suicide", new Suicide());
        registerCommand("vanish", new Vanish());
        registerCommand("whois", new Whois());
        registerCommand("wild", new Wild());
        registerCommand("workbench", new Workbench());
        if (core.checkVault()) {
            registerCommand("balance", new Balance());
            registerCommand("pay", new Pay());
        }
        */
    }

    private void FetchEvents() {
        registerEvent(new BlockBreak());
        registerEvent(new BlockBreak());
        registerEvent(new BlockPlace());
        registerEvent(new EntityDamage());
        registerEvent(new EntityTarget());
        registerEvent(new FoodChange());
        registerEvent(new InventoryClick());
        registerEvent(new PlayerChat());
        registerEvent(new PlayerInteract());
        registerEvent(new PlayerJoin());
        registerEvent(new PlayerMove());
        registerEvent(new PlayerPD());
        registerEvent(new PlayerPreProcess());
        registerEvent(new PlayerQuit());
    }
}
