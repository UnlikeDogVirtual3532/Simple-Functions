$title $(players) title {"text":"You Have Been Warned", "bold":true, "italic":true, "color":"red"}
$title $(players) subtitle {"text":"$(text)", "bold":true, "italic":true, "color":"red"}
$playsound minecraft:entity.wither.spawn ambient $(players)
$scoreboard players add $(players) warning 1