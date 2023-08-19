module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
import Link
import Point
import Quality
import City
import Tunel
import qualified Data.Type.Bool as True

data Region = Reg [City] [Link] [Tunel]

newR :: [City] -> [Link] -> [Tunel] -> Region
newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) newCity = Reg (newCity:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
--"enlazar" lo entendi como linkearlas, con la calidad
linkR (Reg cities links tunels) city1 city2 calidad = Reg cities ((newL city1 city2 calidad):links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--No entendi

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = --hay que iterar sobre tunels y ver con connectsT si esas dos ciudades esta en alg tunel

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
