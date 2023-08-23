module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point
import City
import Quality
data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL cityTarget (Lin city1 city2 _) | cityTarget==city1 = True
                        | cityTarget==city2 = True
                        | otherwise = False

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL cityTarget1 cityTarget2 (Lin city1 city2 _)  | cityTarget1==city1 && cityTarget2==city2 = True
                        | cityTarget1==city2 && cityTarget2==city1 = True
                        | otherwise = False

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexión en este canal
delayL (Lin city1 city2 quality) = delayQ quality * distanceC city1 city2