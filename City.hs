module City ( City, newC, nameC, distanceC )
   where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC x y = (Cit x y)

nameC :: City -> String
nameC (Cit x y) = x

distanceC :: City -> City -> Float
distanceC (Cit x1 y1) (Cit x2 y2) = difP(y1) (y2)