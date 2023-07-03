import numpy as np
x1 = [  [0,0,1,0,0],
        [0,1,1,1,0],
        [0,0,1,0,0] ]
x2 = [  [0,0,1,0,0],
        [1,1,1,0,0],
        [0,0,1,0,0] ]
x3 = [  [0,0,0,0,0],
        [0,0,0,0,0],
        [0,0,0,0,0] ]

x4 = [[0,0,0,0,0],
        [0,0,0,0,0],
        [1,0,0,0,0]]

x1 = np.array(x1).reshape(-1)
# print(x1.shape)
x2 = np.array(x2).reshape(-1)
x3 = np.array(x3).reshape(-1)
y1 = [1,1,0]

from sklearn.neighbors import KNeighborsClassifier
knclf = KNeighborsClassifier(n_neighbors=1)
knclf.fit([x1,x2,x3],y1)
predValue = knclf.predict([ np.array(x4).reshape(-1) ])
print(predValue)


# import matplotlib.pyplot as plt
# plt.imshow(x1)
# plt.show()