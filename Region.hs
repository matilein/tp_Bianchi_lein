module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR, getLink )
   where
import Link
import Point
import Quality
import City
import Tunel
import qualified Data.Type.Bool as True

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) newCity | newCity `elem` cities = error"Esta ciudad pertenece actualmente a la región seleccionada."
                                         | otherwise=Reg (cities++[newCity]) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 calidad | city1 `notElem` cities = error"La primer ciudad ingresada no pertenece a la región"
                                                   | city2 `notElem` cities = error"La segunda ciudad ingresada no pertenece a la región"
                                                   | otherwise = Reg cities ((newL city1 city2 calidad):links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región, le pasas una region y una lista de ciudades, hay que fijarse de que esas ciudades esten enlazadas por links y devuele no el tunel que cree sino una region con ese tunel.


connectedR :: Region -> City -> City -> Bool 
connectedR (Reg _ _ tunnels) citi1 citi2 = any (\tunnel -> connectsT citi2 citi1 tunnel) tunnels

linkedR :: Region -> City -> City -> Bool --indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) citi1 citi2 = any (\link -> linksL citi2 citi1 link) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
getLink :: [Link] -> City -> City -> [Link]
getLink links citi1 citi2 = [link | link <- links, linksL citi1 citi2 link ]
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades


auxiliar1 :: Region -> [City] -> Bool
auxiliar1 (Reg cities _ _) citiesN = all (\ciudad -> ciudad `elem` cities) citiesN
--x1= newP 1 (-1)
--bsas= newC "bsas" x1
--rio= newC "rio" x1
--calidad = newQ "calidad" 2 2
--rosario= newC "rosario" x1
--santafe= newC "santafe" x1
--pilar = newC "pilar" x1

--link1 = newL bsas rio calidad
--link2 = newL rio rosario calidad
--link3 = newL rosario santafe calidad
--link4 = newL santafe pilar calidad

--tunelito = [link1, link2, link3, link4]

--regionsita = newR
