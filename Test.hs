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

result :: Int -> Int
result x | x > 5 = 4
         | otherwise = error "hey"
-- ahora pueden evaluar
t = [ difP p1 p2 == sqrt 40, 
      nameC bsas == "bsas", 
      distanceC bsas rosario == sqrt 40,
      distanceC rio japon == sqrt 17,
      capacityQ calidad == 2,
      delayQ calidad == 3,
      connectsL bsas link1,
      connectsL bsas link2 == False,
      linksL santafe pilar link4,
      linksL santafe rio link4 == False,
      capacityL link2==4,
      delayL link1 == delayQ calidad * sqrt 101,
      connectsT bsas pilar tunel1,
      connectsT rio pilar tunel1 == False,
      delayT tunel1 == delayL link1 + delayL link2 + delayL link3 + delayL link4,
      usesT link1 tunel1,
      usesT link5 tunel1 == False,
      connectedR regionF bsas santafe,
      connectedR regionF rio santafe == False,
      linkedR regionF bsas rio,
      linkedR regionF rio santafe == False,
      delayR regionF bsas pilar == delayT tunel1,
      availableCapacityForR regionF bsas rio == 0,
      availableCapacityForR regionF santafe pilar == 1 ]

p1 = newP 1 (-1)
p2 = newP 3 5
p3 = newP 2 9
p4 = newP (-3) 5
bsas = newC "bsas" p1
rio = newC "rio" p3
rosario = newC "rosario" p2
santafe = newC "santafe" p4
pilar = newC "pilar" p3
japon = newC "japon" p2
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
region09 = tunelR region08 [bsas, rio, rosario, santafe, pilar]
regionF = tunelR region09 [bsas, rio, rosario, santafe]