# Prevent Player Collision
team join NoCollision @a

# Prison Timer
scoreboard players add #Timer prison-timer 1
execute if entity @e[type=slime, tag=prison-slime] run scoreboard players set #Timer prison-timer 0
execute if score #Timer prison-timer >= #Dummy prison-timer run kill @e[tag=prisonslime]
execute if score #Timer prison-timer >= #Dummy prison-timer run summon minecraft:slime 126 183 -84 {CustomName:"Homie",Tags:["prison-slime"],DeathLootTable:"minecraft:empty",Size:0,Health:99999999999999999999999,Attributes:[{Name:"generic.maxHealth",Base:9223372036854775807}],HandDropChances:[0.5F,2F],HandItems:[{id:"minecraft:ender_pearl",Count:1},{}]}
execute if score #Timer prison-timer > #Dummy prison-timer run scoreboard players set #Timer prison-timer 0

# Pvp Disabler/Enabler
execute as @a run data modify entity @s foodLevel set value 20
execute as @a[tag=!pvp] run data modify entity @s Invulnerable set value true
execute as @a[tag=pvp] run data modify entity @s Invulnerable set value false
execute as @a[tag=pvp] run team join pvp @s
execute as @a[tag=!pvp, team=pvp] run team leave @s

# Portal Tp
# To Pvp Arena
execute as @a[dz=-5, dy=4] positioned 57 164 -22 run tag @s add pvp
execute positioned 57 164 -22 run tp @a[dz=-5, dy=4] 78 118 -48 0 0
# To Parkour
execute positioned 98 164 -22 run tp @a[dz=-5, dy=4] 122.50 156 19.5 0 0
# Pvp Arena To Lobby
execute as @a[dx=3, dy=3] positioned 77 118 -55 run tag @s remove pvp
execute positioned 77 118 -55 run tp @a[dx=3, dy=3] 78.50 162 -40.5 0 0

# VIP Room
# execute positioned 93.5 171 -0.5 as @e[dx=0, dz=0] run data merge entity @s {Motion:[0.0, 10.0, 0.0]}
execute if block 93 171 -1 #minecraft:pressure_plates[powered=true] run data modify entity virtualengi Motion set value [0.0, 1.0, 0.0]

# Secret
execute positioned 56 171 -25 run tp @a[dy=2] 58 183 -53 90 0
execute positioned 50 187 -42 run tp @a[dx=2, dy=2] 51 187 -44 180 0
execute positioned 50 187 -43 run tp @a[dx=2, dy=2] 51 187 -40 0 0
execute positioned 46 187 -48 run tp @a[dz=2, dy=2] 90 131 -21 180 0
execute positioned 55 187 -46 run tp @a[dz=-2, dy=2] 78.50 162 -40.5 0 0
execute positioned 91 131 -19 run tp @a[dx=-2, dy=2] 48 187 -47 270 0

# Particles
scoreboard players add #Timer particle-timer 1
execute if score #Timer particle-timer >= #Dummy particle-timer run particle minecraft:dripping_water 85 128 -33 0.2 0 0.2 1 1
execute if score #Timer particle-timer > #Dummy particle-timer run scoreboard players set #Timer particle-timer 0

particle minecraft:glow 64 118 -50 40 20 30 10 50 normal @a

# No Item Dropping
# execute as @e[type=item, tag=!processed] run data modify entity @s Owner set from entity @s Thrower
# execute as @e[type=item, tag=!processed] run data modify entity @s PickupDelay set value 0
# tag @e[type=item] add processed
