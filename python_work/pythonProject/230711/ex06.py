import numpy as np

np.save('a.npy',[71,62,53,34,25,16])

data = np.load('a.npy')
print(data)