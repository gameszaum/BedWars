package br.com.cubeland;

import static br.com.cubeland.messages.Messages.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    static List<Teams> teams = new ArrayList<Teams>();
    List<Player> teamPlayers = new ArrayList<Player>();
    Location location;
    Location bedLocation;
    String name;
    String color;
    int woolData;
    boolean bed;

    public Teams(EnumTeams team) {
        this.location = team.getLocation();
        this.name = team.getName();
        this.color = team.getColor();
        this.woolData = team.getData();
        this.bedLocation = team.getBedLocation();
        this.bed = true;

        teams.add(this);
    }

    public void addPlayer(Player player) {
        this.teamPlayers.add(player);
        teamAssignedMessage(player);
    }

    public static Teams getTeam(Player player) {
        for (Teams team : teams) {
            if (getTeam(team, player) != null) {
                return team;
            }
        }

        return null;
    }

    public static Teams getTeam(Teams team, Player player) {
        if (team.teamPlayers.contains(player)) {
            return team;
        }

        return null;
    }

    public static Teams getTeam(Block block) {
        for (Teams team : teams) {
            if (getTeam(team, block) != null) {
                return team;
            }
        }
        return null;
    }

    public static Teams getTeam(Teams team, Block block) {
        if (isBedLocation(team, block)) {
            return team;
        }

        return null;
    }

    public static boolean isBedLocation(Teams team, Block block) {
        Location location = block.getLocation();
        Location upperBedLocation = getUpperBedLocation(block);

        if (team.hasBed() && (team.bedLocation.equals(location) || team.bedLocation.equals(upperBedLocation))) {
            Bukkit.broadcastMessage("Aqui tem cama (single team).");
            return true;
        }

        return false;
    }

    public static boolean isBedLocation(Teams[] teams, Block block) {
        for (Teams team : teams) {
            if (isBedLocation(team, block)) {
                return true;
            }
        }

        return false;
    }

    public static Location getUpperBedLocation(Block block) {
        Bed bedBlock = (Bed) block.getState().getData();
        BlockFace facing = bedBlock.getFacing();
        Location bedUpperLocation = block.getLocation();

        switch (facing) {
            case NORTH:
                bedUpperLocation.subtract(0, 0, 1);
                break;
            case EAST:
                bedUpperLocation.add(1, 0, 0);
                break;
            case SOUTH:
                bedUpperLocation.add(0, 0, 1);
                break;
            case WEST:
                bedUpperLocation.subtract(1, 0, 0);
                break;
        }

        return bedUpperLocation;
    }

    public boolean hasBed() {
        return bed;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void breakBed(Player player, Block block) {
        this.bed = false;

        bedBreakMessage(player, block);
    }

}
