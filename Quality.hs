module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ x y z  = (Qua x y z)

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua x y z) = y
delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua x y z) = z