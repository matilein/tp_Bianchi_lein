import Control.Exception
import System.IO.Unsafe
import Link
import Point
import Quality
import City
import Tunel
import Region
testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

-- ahora pueden evaluar
funciones = [ difP p1 p2 == sqrt 40, --POINT
              nameC bsas == "bsas",  --CITY
              distanceC bsas rosario == difP p1 p2,
              distanceC rio japon == difP p3 p6,
              capacityQ calidad == 2,  --QUALITY
              delayQ calidad2 == 2,
              connectsL bsas link1,  --LINK
              connectsL bsas link2 == False,
              linksL santafe pilar link4,
              linksL santafe rio link4 == False,
              capacityL link2==4,
              delayL link1 == delayQ calidad * difP p1 p3,
              connectsT bsas pilar tunel1,  --TUNEL
              connectsT rio pilar tunel1 == False,
              delayT tunel1 == delayL link1 + delayL link2 + delayL link3 + delayL link4,
              delayT tunel2 == delayL link1 + delayL link2,
              usesT link1 tunel1,
              usesT link5 tunel1 == False,
              connectedR regionF bsas santafe,  --REGIÓN
              connectedR regionF rio santafe == False,
              linkedR regionF bsas rio,
              linkedR regionF rio santafe == False,
              delayR regionF bsas pilar == delayT tunel1,
              delayR regionF bsas santafe == delayL link1 + delayL link2 + delayL link3,
              availableCapacityForR regionF bsas rio == 0,
              availableCapacityForR regionF santafe pilar == 1]

excepciones = [testF tunel3,
               testF regionE1,
               testF regionE2,
               testF regionE3,
               testF regionE4,
               testF regionE5,
               testF regionE6,
               testF regionE7,
               testF regionE8,
               testF regionE9,
               testF regionE10 ]

--Funciones
p1 = newP 1 (-1)
p2 = newP 3 5
p3 = newP 2 9
p4 = newP (-3) 5
p5 = newP 9 3
p6 = newP 3 4
bsas = newC "bsas" p1
rio = newC "rio" p3
rosario = newC "rosario" p2
santafe = newC "santafe" p4
pilar = newC "pilar" p5
japon = newC "japon" p6
calidad = newQ "calidad" 2 3
calidad2 = newQ "calidad" 4 2
calidad3 = newQ "calidad" 3 5

link1 = newL bsas rio calidad
link2 = newL rio rosario calidad2
link3 = newL rosario santafe calidad3
link4 = newL santafe pilar calidad
link5 = newL bsas santafe calidad2

tunel1 = newT [link1, link2, link3, link4]
tunel2 = newT [link1,link2]

region = newR
region0 = foundR region bsas 
region01 = foundR region0 rio 
region02 = foundR region01 rosario 
region03 = foundR region02 santafe 
region04 = foundR region03 pilar
region05 = linkR region04 bsas rio calidad
region06 = linkR region05 rio rosario calidad2
region07 = linkR region06 rosario santafe calidad3
region08 = linkR region07 santafe pilar calidad
regionAF = tunelR region08 [bsas, rio, rosario, santafe, pilar]
regionF = tunelR regionAF [bsas, rio, rosario, santafe]

--Excepciones
tunel3 = newT [] --Tunel vacío
regionE1 = foundR regionF bsas --Ingresar una ciudad que ya pertenece a región
regionE2 = linkR regionF bsas japon calidad2 --Enlazar una ciudad no perteneciente a la región
regionE3 = tunelR regionF [bsas, rio, rosario, japon] --Crear un tunel con ciudades, cuando al menos una no pertenece a la región
regionE4 = tunelR regionF [bsas] --Crear un tunel con solamente una ciudad
regionE5 = tunelR regionF [] --Crear un tunel sin ciudades
regionE6 = tunelR regionAF [bsas, rio, rosario, santafe, pilar] --Crear un tunel ya existente
regionE7 = delayR regionF bsas japon --Calcular el delay entre ciudades, cuando al menos una no pertenece a la región
regionE8 = delayR regionF rio santafe --Calcular el delay entre ciudades que no estan conectadas
regionE9 = availableCapacityForR regionF bsas japon --Calcular la capacidad entre ciudades, cuando al menos una no pertenece a la región.
regionE10 = availableCapacityForR regionF bsas rosario --Calcular la capacidad de dos ciudades sin enlazar

--Estas excepciones surgen durante la ejecución; se comprueban poniendo el nombre de la variable en la terminal.
regionE11 = tunelR regionF [bsas, rio, rosario] --Crear un tunel con links sin capacidad. El error se comprueba poniendo regionE7 en la terminal.
regionE12 = tunelR regionAF [bsas, rosario, santafe] --Crear un tunel con ciudades sin enlazar. El error se comprueba poniendo regionE8 en la terminal.
