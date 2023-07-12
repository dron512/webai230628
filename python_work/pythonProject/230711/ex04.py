a = [10,20,30]
b = [40,50,60]

c = a+b
print(c)

import numpy as np

a = np.array(a)
b = np.array(b)
c = a+b
print(c)

array = np.arange(16).reshape(4,4)
indexes = array>10
print(indexes)
array[indexes] = 100
print(array)