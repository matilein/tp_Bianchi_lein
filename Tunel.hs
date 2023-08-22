module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where
import Link
import Point
import Quality
import City
import qualified Data.Type.Bool as True

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT [] = error"Tunel vacío"
newT thelinks = Tun thelinks

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun [link]) = connectsL city1 link && connectsL city2 link 
connectsT city1 city2 (Tun [link1,link2]) = connectsL city1 link1 && not(connectsL city1 link2) && not(connectsL city2 link1) && connectsL city2 link2
connectsT city1 city2 (Tun links) = connectsL city1 (links!!0) && not(connectsL city1 (links!!1)) && connectsL city2 (last links) && not(connectsL city2 (links !! (length links -2)))

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun thelinks) =
    sum delays
     where delays = map delayL thelinks

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun thelinks) = link `elem` thelinks