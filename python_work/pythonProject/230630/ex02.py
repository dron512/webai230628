fish_length = [25.4, 26.3, 26.5, 29.0, 29.0, 29.7, 29.7, 30.0, 30.0, 30.7, 31.0, 31.0,
                31.5, 32.0, 32.0, 32.0, 33.0, 33.0, 33.5, 33.5, 34.0, 34.0, 34.5, 35.0,
                35.0, 35.0, 35.0, 36.0, 36.0, 37.0, 38.5, 38.5, 39.5, 41.0, 41.0, 9.8,
                10.5, 10.6, 11.0, 11.2, 11.3, 11.8, 11.8, 12.0, 12.2, 12.4, 13.0, 14.3, 15.0]
fish_weight = [242.0, 290.0, 340.0, 363.0, 430.0, 450.0, 500.0, 390.0, 450.0, 500.0, 475.0, 500.0,
                500.0, 340.0, 600.0, 600.0, 700.0, 700.0, 610.0, 650.0, 575.0, 685.0, 620.0, 680.0,
                700.0, 725.0, 720.0, 714.0, 850.0, 1000.0, 920.0, 955.0, 925.0, 975.0, 950.0, 6.7,
                7.5, 7.0, 9.7, 9.8, 8.7, 10.0, 9.9, 9.8, 12.2, 13.4, 12.2, 19.7, 19.9]

import numpy as np

fish_data = np.column_stack((fish_length,fish_weight))
# print(fish_data.shape)

ta1 = np.ones(3)
ta0 = np.zeros((3,3))
tafull = np.full((2,2),10)

# print(ta1)
# print(ta0)
# print(tafull)
a = np.ones(10)+np.zeros(10)
# print(a)
fish_target = np.concatenate((np.ones(35),np.zeros(14)))
# print(fish_target)

from sklearn.model_selection import train_test_split

train_input, test_input, train_target, test_target = \
    train_test_split( fish_data, fish_target,stratify=fish_target,random_state=42)

print(train_input.shape)
print(train_target.shape)

print(train_input[:5])
print(train_target[:5])



from sklearn.neighbors import KNeighborsClassifier

knclf = KNeighborsClassifier()
knclf.fit(train_input,train_target)
predValue = knclf.predict([[25,150]])

print(predValue)

dis,indexes = knclf.kneighbors([[25,150]])
print('거리들',dis)
print('순번들',indexes)
print(train_input[indexes])

import matplotlib.pyplot as plt

plt.scatter(train_input[:,0],train_input[:,1])
plt.scatter(25,150,marker='^')
plt.scatter(train_input[indexes,0],train_input[indexes,1],marker='D')
plt.show()