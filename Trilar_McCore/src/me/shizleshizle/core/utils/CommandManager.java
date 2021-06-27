package me.shizleshizle.core.utils;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.*;
import me.shizleshizle.core.commands.bansystem.*;
import me.shizleshizle.core.commands.homes.Delhome;
import me.shizleshizle.core.commands.homes.Home;
import me.shizleshizle.core.commands.homes.Sethome;
import me.shizleshizle.core.commands.messaging.Msg;
import me.shizleshizle.core.commands.messaging.Reply;
import me.shizleshizle.core.commands.messaging.SocialSpy;
import me.shizleshizle.core.commands.spawn.Spawn;
import me.shizleshizle.core.commands.teleportation.*;
import me.shizleshizle.core.commands.tickets.*;
import me.shizleshizle.core.commands.time.DayCmd;
import me.shizleshizle.core.commands.time.NightCmd;
import me.shizleshizle.core.commands.time.PTime;
import me.shizleshizle.core.commands.time.Time;
import me.shizleshizle.core.commands.vaults.Vault;
import me.shizleshizle.core.commands.warps.Deletewarps;
import me.shizleshizle.core.commands.warps.Setwarps;
import me.shizleshizle.core.commands.warps.Warp;
import me.shizleshizle.core.listeners.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
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
        FetchCommands();
        FetchEvents();
    }

    private void FetchCommands() {
        // ban
        registerCommand("ban", new Ban());
        registerCommand("unban", new Unban());
        registerCommand("kick", new Kick());
        registerCommand("mute", new Mute());
        registerCommand("unmute", new Unmute());

        //home
        registerCommand("delhome", new Delhome());
        registerCommand("home", new Home());
        registerCommand("sethome", new Sethome());

        // messaging
        registerCommand("msg", new Msg());
        registerCommand("reply", new Reply());
        registerCommand("socialspy", new SocialSpy());

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
        registerCommand("tpdeny", new TpDeny());
        registerCommand("tphere", new Tphere());
        registerCommand("tpo", new Tpo());
        registerCommand("tpohere", new Tpohere());
        registerCommand("tppos", new Tppos());
        registerCommand("tptoggle", new Tptoggle());

        // tickets
        registerCommand("checkticket", new CheckTicket());
        registerCommand("closeticket", new CloseTicket());
        registerCommand("taketicket", new TakeTicket());
        registerCommand("ticket", new Ticket());
        registerCommand("tickets", new Tickets());

        // time
        registerCommand("time", new Time());
        registerCommand("day", new DayCmd());
        registerCommand("night", new NightCmd());
        registerCommand("ptime", new PTime());

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
        registerCommand("autobroadcaster", new AB());
        registerCommand("afk", new Afk());
        registerCommand("back", new Back());
        registerCommand("broadcast", new Broadcast());
        registerCommand("chatcolor", new ChatColorCmd());
        registerCommand("clearchat", new ClearChat());
        registerCommand("clearinventory", new ClearInventory());
        registerCommand("commandspy", new CommandSpy());
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
        registerCommand("killmobs", new KillMobs());
        registerCommand("list", new ListCmd());
        registerCommand("lockdown", new Lockdown());
        registerCommand("mccore", new Reload());
        registerCommand("me", new Me());
        registerCommand("mods", new Mods());
        registerCommand("nickname", new Nickname());
        registerCommand("permission", new PermissionsCmd());
        registerCommand("prefix", new PrefixCmd());
        registerCommand("rank", new Ranks());
        registerCommand("repair", new Repair());
        registerCommand("rules", new Rules());
        registerCommand("socialmedia", new SocialMedia());
        registerCommand("skull", new Skulls());
        registerCommand("spawnmob", new Spawnmob());
        registerCommand("speed", new Speed());
        registerCommand("staff", new StaffList());
        registerCommand("suicide", new Suicide());
        registerCommand("vanish", new Vanish());
        registerCommand("whois", new WhoIs());
        registerCommand("wild", new Wild());
        registerCommand("workbench", new Workbench());
        if (core.checkVault()) {
            registerCommand("balance", new Balance());
            registerCommand("pay", new Pay());
        }
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
