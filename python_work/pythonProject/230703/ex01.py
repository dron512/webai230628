fish_length = [25.4, 26.3, 26.5, 29.0, 29.0, 29.7, 29.7, 30.0, 30.0, 30.7, 31.0, 31.0,
                31.5, 32.0, 32.0, 32.0, 33.0, 33.0, 33.5, 33.5, 34.0, 34.0, 34.5, 35.0,
                35.0, 35.0, 35.0, 36.0, 36.0, 37.0, 38.5, 38.5, 39.5, 41.0, 41.0, 9.8,
                10.5, 10.6, 11.0, 11.2, 11.3, 11.8, 11.8, 12.0, 12.2, 12.4, 13.0, 14.3, 15.0]
fish_weight = [242.0, 290.0, 340.0, 363.0, 430.0, 450.0, 500.0, 390.0, 450.0, 500.0, 475.0, 500.0,
                500.0, 340.0, 600.0, 600.0, 700.0, 700.0, 610.0, 650.0, 575.0, 685.0, 620.0, 680.0,
                700.0, 725.0, 720.0, 714.0, 850.0, 1000.0, 920.0, 955.0, 925.0, 975.0, 950.0, 6.7,
                7.5, 7.0, 9.7, 9.8, 8.7, 10.0, 9.9, 9.8, 12.2, 13.4, 12.2, 19.7, 19.9]

import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler

fish_data = np.column_stack([fish_length,fish_weight])
fish_target = np.concatenate([np.ones(35),np.zeros(14)])

print(fish_data)
print(fish_target)

train_input,test_input,train_target,test_target \
    = train_test_split(fish_data,fish_target,random_state=42)

# plt.scatter(train_input[:,0],train_input[:,1])
# plt.scatter(25,150)
# plt.show()

mean = np.mean(train_input,axis=0)
std = np.std(train_input,axis=0)

train_scaled = (train_input-mean)/std
test_scaled = (test_input-mean)/std

new = ([25,150]-mean)/std

plt.scatter(train_scaled[:,0],train_scaled[:,1])
plt.scatter(new[0],new[1])
plt.show()

ss = StandardScaler()
ss.fit(train_input,train_target)

train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

plt.scatter(train_scaled[:,0],train_scaled[:,1])
plt.scatter(new[0],new[1])
plt.show()

new1 = ss.transform([[100,100],[10,10],[20,20]])
# new2 = ss.transform([[10,10]])

knclf = KNeighborsClassifier()
knclf.fit(train_scaled,train_target)

predvalue = knclf.predict([new,new1[0],new1[1],new1[2]])
print(predvalue)





