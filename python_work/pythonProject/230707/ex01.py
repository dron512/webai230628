from sklearn.linear_model import LogisticRegression
import numpy as np
import matplotlib.pyplot as plt
import cv2
# acls -4~0,5~10
acls = np.array([[x,y]
        for x,y
        in zip(np.random.randint(-4, 0, (10)), np.random.randint(5,10,(10)))])
# bcls 2~6,0~5
bcls = np.array([[x,y]
        for x,y
        in zip(np.random.randint(2, 6, (10)), np.random.randint(0,5,(10)))])
# ccls -8~-4, -7,5 ~-2.5
ccls = np.array([[x,y]
        for x,y
        in zip(np.random.randint(-8, -4, (10)), np.random.randint(-7.5,-2.5,(10)))])
data = np.concatenate([acls,bcls,ccls])
target = ['A클래스']*10 + ['B클래스']*10 + ['C클래스']*10

plt.scatter(acls[:,0],acls[:,1])
plt.scatter(bcls[:,0],bcls[:,1])
plt.scatter(ccls[:,0],ccls[:,1])
plt.scatter([5,6,-3],[5,10,0])
plt.show()

lr = LogisticRegression()
lr.fit(data,target)

predValue = lr.predict([[5,6],[5,10],[-3,0]])
print(predValue)



















