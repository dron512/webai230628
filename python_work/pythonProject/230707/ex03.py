import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import SGDClassifier

fish = pd.read_csv('https://bit.ly/fish_csv_data')
fish_input = fish[['Weight', 'Length', 'Diagonal', 'Width', 'Height']].to_numpy()
fish_target = fish['Species'].to_numpy()

print(fish_input.shape)
print(fish_target.shape)

train_input, test_input, train_target, test_target = \
    train_test_split(fish_input, fish_target, random_state=42)

ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

print(train_scaled[:5])
print(test_scaled[:5])

sgdcls = SGDClassifier(loss='log',max_iter=10,random_state=42)
sgdcls.fit(train_scaled,train_target)

score = sgdcls.score(train_scaled,train_target)
print(score)
score = sgdcls.score(test_scaled,test_target)
print(score)

sgdcls.partial_fit(train_scaled,train_target)
score = sgdcls.score(train_scaled,train_target)
print(score)
score = sgdcls.score(test_scaled,test_target)
print(score)

sgdcls = SGDClassifier(loss='log',random_state=42)

train_score = []
test_score = []

classes = np.unique(train_target)

for _ in range(0,300):
    sgdcls.partial_fit(train_scaled,train_target,classes=classes)

    train_score.append(sgdcls.score(train_scaled,train_target))
    test_score.append(sgdcls.score(test_scaled, test_target))

import  matplotlib.pyplot as plt

plt.plot(train_score)
plt.plot(test_score)
plt.show()













