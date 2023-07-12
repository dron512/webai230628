a = [10,20,30]
b = a

a[0] = 50

print(a)
print(b)

import numpy as np

a = np.array(a)
b = a.copy()

a[1] = 70

print(a)
print(b)

 