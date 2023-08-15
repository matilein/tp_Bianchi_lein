module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City
import Quality
data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL x y z = (Lin x y z)

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL x (Lin y z w) | x== y = True
                        | x==z = True
                        | otherwise = False

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL x y (Lin a b c)  | x==a && y==b = True
                        | x==b && y==a = True
                        | otherwise = False

capacityL :: Link -> Int
capacityL (Lin x y z) = capacityL(z)

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin x y z) = delayL(z)