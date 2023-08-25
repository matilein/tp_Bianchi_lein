module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where
import Link
import Point
import Quality
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT [] = error"Tunel vacío"
newT linkList = Tun linkList

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT cityTarget1 cityTarget2 (Tun [link]) = connectsL cityTarget1 link && connectsL cityTarget2 link 
connectsT cityTarget1 cityTarget2 (Tun links) = order cityTarget1 cityTarget2 links || order cityTarget2 cityTarget1 links
   where 
      order c1 c2 links = connectsL c1 (head links) && not(connectsL c1 (links!!1)) && connectsL c2 (last links) && not(connectsL c2 (links !! (length links -2))) 


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun linkList) = foldr(\linkList acc -> delayL linkList + acc) 0 linkList

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT linkTarget (Tun linkList) = linkTarget `elem` linkList
