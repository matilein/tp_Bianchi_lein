module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
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

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--No entendi

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = --hay que iterar sobre tunels y ver con connectsT si esas dos ciudades esta en alg tunel

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades

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