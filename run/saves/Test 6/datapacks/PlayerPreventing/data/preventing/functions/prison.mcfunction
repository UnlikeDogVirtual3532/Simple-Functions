$tp $(players) 126 184 -89 0 0
$title $(players) title {"text":"Good Luck Prison", "bold":true, "italic":true, "color":"red"}
$playsound minecraft:entity.wither.spawn ambient $(players)
$scoreboard players add $(players) warning 1
ban @e[scores={warning=4}, type=player]