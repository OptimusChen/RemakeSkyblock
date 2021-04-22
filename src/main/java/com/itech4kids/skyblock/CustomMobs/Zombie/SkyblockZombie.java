package com.itech4kids.skyblock.CustomMobs.Zombie;

import com.itech4kids.skyblock.Main;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyblockZombie extends EntityZombie implements Listener {

    public SkyblockZombieType zombieType;
    public int level;
    public String name;
    public int health;
    public int maxHealth;
    private Damageable zombie;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack weapon;
    private String mobName;
    private ArmorStand healthDisplay;

    public SkyblockZombie(SkyblockZombieType zombieType, World world) {
        super(world);
        this.zombieType = zombieType;
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        healthDisplay = this.getBukkitEntity().getWorld().spawn(new Location(this.getBukkitEntity().getLocation().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.25, this.getBukkitEntity().getLocation().getZ()), ArmorStand.class);
        zombie = (Damageable) this.getBukkitEntity();
        zombie.setHealth(1);
        zombie.setMaxHealth(1);
        if (zombieType.equals(SkyblockZombieType.GRAVEYARD)) {
            this.level = 1;
            this.name = "Zombie";
            this.health = 100;
            this.maxHealth = 100;
        }else if (zombieType.equals(SkyblockZombieType.ZOMBIE_VILLAGER)){
            this.level = 1;
            this.name = "Zombie Villager";
            this.health = 120;
            this.maxHealth = 120;
            this.setVillager(true);
            this.helmet = new ItemStack(Material.LEATHER_HELMET);
            this.chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            this.leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            this.boots = new ItemStack(Material.LEATHER_BOOTS);
            this.weapon = null;
        }else if (zombieType.equals(SkyblockZombieType.LAPIS_ZOMBIE)){
            this.level = 7;
            this.name = "Lapis Zombie";
            this.health = 200;
            this.maxHealth = 200;
            this.helmet = new ItemStack(Material.SEA_LANTERN);
            this.chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLUE.getData());
            this.leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1, DyeColor.BLUE.getData());
            this.boots = new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.BLUE.getData());
            this.weapon = null;
        }else if (zombieType.equals(SkyblockZombieType.DIAMOND_RESERVE)){
            this.level = 15;
            this.name = "Zombie";
            this.health = 250;
            this.maxHealth = 250;
            this.helmet = new ItemStack(Material.DIAMOND_HELMET);
            this.chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            this.leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            this.boots = new ItemStack(Material.DIAMOND_BOOTS);
            this.weapon = new ItemStack(Material.DIAMOND_SWORD);
        }else if (zombieType.equals(SkyblockZombieType.SEA_WALKER)){
            this.level = 4;
            this.name = "Sea Walker";
            this.health = 1500;
            this.maxHealth = 1500;
            this.helmet = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            this.chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLUE.getData());
            this.leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1, DyeColor.BLUE.getData());
            this.boots = new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.BLUE.getData());
            this.weapon = new ItemStack(Material.DIAMOND_SWORD);
         }else if (zombieType.equals(SkyblockZombieType.OBSIDIAN_SANCTUARY)){
            this.level = 20;
            this.name = "Zombie";
            this.health = 300;
            this.maxHealth = 300;
            this.helmet = new ItemStack(Material.DIAMOND_BLOCK);
            this.helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            this.chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            this.chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            this.leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            this.leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            this.boots = new ItemStack(Material.DIAMOND_BOOTS);
            this.boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            this.weapon = new ItemStack(Material.DIAMOND_SWORD);
            this.weapon.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        }else if (zombieType.equals(SkyblockZombieType.CRYPT_GHOUL)){
            this.level = 30;
            this.name = "Crypt Ghoul";
            this.health = 2000;
            this.maxHealth = 2000;
            this.helmet = new ItemStack(Material.AIR);
            this.chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            this.leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            this.boots = new ItemStack(Material.CHAINMAIL_BOOTS);
            this.weapon = new ItemStack(Material.IRON_SWORD);
        }else if (zombieType.equals(SkyblockZombieType.GOLDEN_GHOUL)){
            this.level = 50;
            this.name = "Golden Ghoul";
            this.health = 45000;
            this.maxHealth = 45000;
            this.helmet = null;
            this.chestplate = new ItemStack(Material.GOLD_CHESTPLATE);
            this.leggings = new ItemStack(Material.GOLD_LEGGINGS);
            this.boots = new ItemStack(Material.GOLD_BOOTS);
            this.weapon = new ItemStack(Material.GOLD_SWORD);
      }
        this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
        this.setEquipment(0, CraftItemStack.asNMSCopy(weapon));
        this.setEquipment(1, CraftItemStack.asNMSCopy(helmet));
        this.setEquipment(2, CraftItemStack.asNMSCopy(chestplate));
        this.setEquipment(3, CraftItemStack.asNMSCopy(leggings));
        this.setEquipment(4, CraftItemStack.asNMSCopy(boots));
        this.setCustomNameVisible(false);
        this.healthDisplay.setCustomNameVisible(true);
        this.healthDisplay.setGravity(false);
        this.healthDisplay.setVisible(false);
        this.healthDisplay.setCustomName(mobName);
        updateHealth(this.getBukkitEntity(), healthDisplay);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity().equals(this.getBukkitEntity())){
            health = (int) (health - e.getFinalDamage());
            e.setDamage(0);
            if (health <= 0){
                zombie.setHealth(0);
            }
        }
    }

    public void updateHealth(CraftEntity entity, ArmorStand armorStand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()){
                    cancel();
                    armorStand.remove();
                }else{
                    armorStand.teleport(new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + 0.75, entity.getLocation().getZ()));
                    armorStand.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth);
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 5L);
    }

}
