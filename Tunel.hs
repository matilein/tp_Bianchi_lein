module Tunel ( Tunel, newT, connectsT, usesT )
   where 

import Link (Link(..))
import Point
import Quality (Quality(..))
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) | any (connects links city1 city2) = True
                                  | otherwise = False
connects :: Link -> City -> City -> Bool
connects (Lin cityone citytwo _) = city1 == cityone && city2 == citytwo || city2 == cityone && city1 == citytwo


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun thelinks) = link `elem` thelinks

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT tunel = sum $ map demora tunel
demora :: Link -> Float
demora (Lin _ _ (Qua _ _ demora)) = demora