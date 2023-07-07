import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split

fish = pd.read_csv('https://bit.ly/fish_csv_data')
print(fish.head())

# fish.to_csv('fish.csv')
# fish.to_excel('fish.xlsx')

print(pd.unique(fish['Species']))
fish_input = \
    fish[['Weight', 'Length', 'Diagonal', 'Height', 'Width']].to_numpy()

print(fish_input[:5])

fish_target = fish['Species'].to_numpy()
print(fish_target[:5])

train_input, test_input, train_target, test_target = \
    train_test_split(fish_input, fish_target, random_state=42)

ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

print(train_scaled[:5])
print(test_scaled[:5])

knclf = KNeighborsClassifier()
knclf.fit(train_scaled, train_target)

trainscore = knclf.score(train_scaled, train_target)
testscore = knclf.score(test_scaled, test_target)
print(trainscore)
print(testscore)

import matplotlib.pyplot as plt
import numpy as np

z = np.arange(-5, 5, 0.1)
# print(z)
print(np.round(z,decimals=2))

phi = 1/ (1+np.exp(-z))
# print(phi)
print(np.round(phi,decimals=2))

plt.plot(z,phi)
plt.xlabel('z')
plt.ylabel('phi')
# plt.show()

char_arr = np.array(['A','B','C','D','E'])
print(char_arr[[True,False,True,False,False]])

bream_smelt_indexes = (train_target=='Bream') | (train_target=='Smelt')
print(bream_smelt_indexes)
train_bream_smelt = train_scaled[bream_smelt_indexes]
target_bream_smelt = train_target[bream_smelt_indexes]

print(train_bream_smelt[:5])
print(target_bream_smelt[:5])

from sklearn.linear_model import LogisticRegression

lr = LogisticRegression()
lr.fit(train_bream_smelt,target_bream_smelt)

score = lr.score(train_bream_smelt,target_bream_smelt)
print(score)

lr = LogisticRegression(C=20,max_iter=1000)
lr.fit(train_scaled,train_target)

trainPred = lr.score(train_scaled,train_target)
testPred = lr.score(test_scaled,test_target)

print(trainPred)
print(testPred)

decision = lr.decision_function(train_scaled[:5])
print(np.round(decision,decimals=2))

from scipy.special import softmax

proba = softmax(decision,axis=1)
print(np.round(proba,decimals=2))