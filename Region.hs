module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR,delayR, availableCapacityForR )
   where
import Link
import Point
import Quality
import City
import Tunel
import qualified Data.Type.Bool as True
import GHC.Exts.Heap (GenClosure(link))

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

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región, le pasas una region y una lista de ciudades, hay que fijarse de que esas ciudades esten enlazadas por links y devuele no el tunel que cree sino una region con ese tunel.
tunelR (Reg cities links tunels) citiesN | not(cityCheck (Reg cities links tunels) citiesN) = error"Se ingresaron ciudades que no pertenecen a la región"
                                         | length citiesN<=1 = error"Las ciudades ingresadas no son suficientes para crear un túnel (2 o más)"
                                         | any(\tunel -> connectsT (head citiesN) (last citiesN) tunel)tunels = error"Este tunel ya pertenece a la región"
                                         | otherwise = Reg cities links (newT(constructTunel (Reg cities links tunels) [] citiesN):tunels)
                                         --Chequea cuando agrega un link que tenga capacidad
cityCheck :: Region -> [City] -> Bool
cityCheck (Reg cities _ _) citiesN = all (\ciudad -> ciudad `elem` cities) citiesN
constructTunel :: Region -> [Link] -> [City] -> [Link]
constructTunel _ _ [x] = []
constructTunel (Reg _ [] _) _ _ = error"Las ciudades no contienen links que las enlazan"
constructTunel (Reg cities (y:ys) tunels) links (x:xs)| not(linksL x (head xs) y) = constructTunel (Reg cities ys tunels) (y:links) (x:xs)
                                                | otherwise = [y] ++ constructTunel (Reg cities (y:links++ys) tunels) [] xs
findLink :: Region -> City -> City -> Link
findLink (Reg _ [] _) _ _ = error"Las ciudades no estan enlazadas por un link"
findLink (Reg cities (x:xs) tunels) city1 city2 | linksL city1 city2 x = x
                                                | otherwise = findLink (Reg cities xs tunels) city1 city2


connectedR :: Region -> City -> City -> Bool 
connectedR (Reg _ _ tunnels) city1 city2 = any (\tunnel -> connectsT city2 city1 tunnel) tunnels

linkedR :: Region -> City -> City -> Bool --indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = any (\link -> linksL city2 city1 link) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--Hay decisiones que tomar! 
delayR region city1 city2 | not(cityCheck region [city1,city2]) = error"Se ingresaron ciudades que no pertenecen a la región"
                          | otherwise = delayT(findTunel region city1 city2)
findTunel :: Region -> City -> City -> Tunel
findTunel (Reg _ _ []) _ _ = error"Las ciudades no estan conectadas"
findTunel (Reg cities links (x:xs)) city1 city2 | connectsT city1 city2 x = x
                                                | otherwise = findTunel (Reg cities links xs) city1 city2

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 = capacityL link - usedCapacity region link
   where link= findLink region city1 city2

usedCapacity :: Region -> Link -> Int
usedCapacity (Reg _ _ tunels) link = foldr(\tunel acc -> if usesT link tunel then acc+1 else acc) 0 tunels

--Teniendo en cuenta la capacidad que los túneles existentes ocupan 

--Funcion que te da la capacidad de un link (-1 cada vez que esta en un tunel)

--La conexión sólo se da a través de un túnel, y sólo se conectan los extremos 
--Cada vez que se refiere a 'conectadas', necesariamente se refiere a un túnel 


--x1= newP 1 (-1)
--x2=newP 3 5
--x3= newP 2 9
--x4=newP (-3) 5
--bsas= newC "bsas" x1
--rio= newC "rio" x3
--calidad = newQ "calidad" 5 3
--rosario= newC "rosario" x2
--santafe= newC "santafe" x4
--pilar = newC "pilar" x3
--japon = newC "japon" x2

--link1 = newL bsas rio calidad
--link2 = newL rio rosario calidad
--link3 = newL rosario santafe calidad
--link4 = newL santafe pilar calidad

--tunelito = newT [link1, link2, link3, link4]

--regionsita = newR
--region4=foundR regionsita bsas 
--region3=foundR region4 rio 
--region2=foundR region3 rosario 
--region1=foundR region2 santafe 
--region=foundR region1 pilar

--region5 = linkR region bsas rio calidad
--region6 = linkR region5 rio rosario calidad
--region7 = linkR region6 rosario santafe calidad
--region8 = linkR region7 santafe pilar calidad
--region9= tunelR region8 [bsas,rio,rosario,santafe,pilar]