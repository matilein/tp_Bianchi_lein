module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR,delayR, availableCapacityForR )
   where
import Link
import Point
import Quality
import City
import Tunel
data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []


foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) newCity | newCity `elem` cities = error"Esta ciudad pertenece a la región seleccionada."
                                         | otherwise=Reg (newCity:cities) links tunels


linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 calidad | city1 `notElem` cities = error"La primer ciudad ingresada no pertenece a la región"
                                                   | city2 `notElem` cities = error"La segunda ciudad ingresada no pertenece a la región"
                                                   | otherwise = Reg cities ((newL city1 city2 calidad):links) tunels


tunelR :: Region -> [ City ] -> Region
tunelR (Reg cities links tunels) citiesN | not(cityCheck (Reg cities links tunels) citiesN) = error"Se ingresaron ciudades que no pertenecen a la región"
                                         | length citiesN<=1 = error"Las ciudades ingresadas no son suficientes para crear un túnel (2 o más)"
                                         | any(\tunel -> connectsT (head citiesN) (last citiesN) tunel)tunels = error"Este tunel ya pertenece a la región"
                                         | otherwise = Reg cities links (newT(constructTunel (Reg cities links tunels) citiesN):tunels)

--Función auxiliar: Verifica que las ciudades ingresadas pertenezcan a la región proporcionada.
cityCheck :: Region -> [City] -> Bool
cityCheck (Reg cities _ _) citiesN = all (\ciudad -> ciudad `elem` cities) citiesN

--Función auxiliar: Dada una lista de ciudades, crea una lista que contenga los links que las conectan en el orden proporcionado.
constructTunel :: Region -> [City] -> [Link]
constructTunel _ [x] = []
constructTunel region (x:xs)| availableCapacityForR region x (head xs) <=0 = error"A los enlaces/links no les queda capacidad"
                            | otherwise = findLink region x (head xs) : constructTunel region xs

--Función auxiliar: Búsqueda de un link que enlace las dos ciudades especificadas, situadas dentro de la región proporcionada. 
findLink :: Region -> City -> City -> Link
findLink (Reg _ [] _) _ _ = error"Las ciudades no estan enlazadas por un link"
findLink (Reg cities (x:xs) tunels) city1 city2 | linksL city1 city2 x = x
                                                | otherwise = findLink (Reg cities xs tunels) city1 city2


connectedR :: Region -> City -> City -> Bool 
connectedR (Reg _ _ tunnels) city1 city2 = any (\tunnel -> connectsT city2 city1 tunnel) tunnels


linkedR :: Region -> City -> City -> Bool --indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = any (\link -> linksL city2 city1 link) links


delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region city1 city2 | not(cityCheck region [city1,city2]) = error"Se ingresaron ciudades que no pertenecen a la región"
                          | otherwise = delayT(findTunel region city1 city2)

--Función auxiliar: Búsqueda de un túnel que establezca conexión entre las dos ciudades especificadas, situadas dentro de la región proporcionada.
findTunel :: Region -> City -> City -> Tunel
findTunel (Reg _ _ []) _ _ = error"Las ciudades no estan conectadas"
findTunel (Reg cities links (x:xs)) city1 city2 | connectsT city1 city2 x = x
                                                | otherwise = findTunel (Reg cities links xs) city1 city2


availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 = capacityL link - usedCapacity region link
   where link= findLink region city1 city2

--Función auxiliar: Calcula la cantidad de túneles a los que pertenece un enlace/link.
usedCapacity :: Region -> Link -> Int
usedCapacity (Reg _ _ tunels) link = foldr(\tunel acc -> if usesT link tunel then acc+1 else acc) 0 tunels