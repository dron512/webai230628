import numpy as np

array1 = np.random.randint(0,10,(3,3))
array2 = np.random.normal(0,10,(3,3))

print(array1)
print(array2)

array3 = np.concatenate([array1,array2])
print(array3)

import matplotlib.pyplot as plt

# plt.imshow(array2)
# plt.show()

array4 = np.array([1,2,3,4])
array5 = array4.reshape(2,2)

print(array5)

array4 = np.array([1,2,3,4,5,6,7,8,9,10])
array5 = array4.reshape(-1,2)

print(array5)

array1 = np.arange(4).reshape(1,4)
array2 = np.arange(8).reshape(2,4)

print(array1)
print(array2)

array3 = np.concatenate([array1,array2],axis=0)
print(array3)
