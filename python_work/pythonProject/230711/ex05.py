import numpy as np

array = np.arange(16).reshape(4,4)
print(array)
value = np.sum(array,axis=1)
print(value)

brray = np.arange(4)
value = np.sum(brray,axis=0)
print(value)