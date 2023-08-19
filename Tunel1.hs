module Tunel ( Tunel, newT, connectsT, usesT )
   where
import Link
import Point
import Quality
import City
import qualified Data.Type.Bool as True

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT thelinks = Tun thelinks

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun [link]) = connectsL city1 link && connectsL city2 link -- contiene un enlace solo 
connectsT city1 city2 (Tun (first:rest)) = connectsL city1 first && connectsL city2 (last rest) 
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun thelinks) = link `elem` thelinks
----------------- Variables para probarlo 
x1= newP 1 (-1)
x2= newP 1 1
bsas= newC "bsas" x1
rio= newC "rio" x2
calidad = newQ "calidad" 2 1.0
link = newL bsas rio calidad

x3= newP 1 (-1)
x4= newP 1 1
Rosario= newC "Rosario" x2
santaFe= newC "santaFe" x3
calida = newQ "calida" 2 1.0
lin = newL sas io calida

x5= newP 1 (-1)
x6= newP 1 1
VIctoria = newC "VIctoria" x5
Pilar = newC "Pilar" x6
calid = newQ "calid" 2 1.0
li = newL as o calid
