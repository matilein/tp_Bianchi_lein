module Tunel ( Tunel, newT, connectsT, usesT )
   where
import Link
import Point
import Quality
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT thelinks = Tun thelinks
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun thelinks) = any (\link -> (city1 `connectsL` link) && (city2 `connectsL` link)) thelinks
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun thelinks) = link `elem` thelinks
-------------------------
{-x1= newP 1 (-1)
x2= newP 1 1
bsas= newC "bsas" x1
rio= newC "rio" x2
calidad = newQ "calidad" 2 1.0
link = newL bsas rio calidad

x3= newP 1 (-1)
x4= newP 1 1
sas= newC "sas" x2
io= newC "io" x3
calida = newQ "calida" 2 1.0
lin = newL sas io calida

x5= newP 1 (-1)
x6= newP 1 1
as= newC "as" x5
o= newC "o" x6
calid = newQ "calid" 2 1.0
li = newL as o calid-}