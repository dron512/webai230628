import numpy as np

a = np.array([1,2,3,4])
b = a.reshape(-1,2)
c = a.reshape(2,2)

print(b)
print(c)

a = np.array([3,2,3,4,7,6,7,10,9,13,11,15,13,14,15,16])
b = a.reshape(-1,2)
# c = a.reshape(2,2)

print(b)
print(c)