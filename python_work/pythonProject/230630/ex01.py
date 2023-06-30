fish_length = [25.4, 26.3, 26.5, 29.0, 29.0, 29.7, 29.7, 30.0, 30.0, 30.7, 31.0, 31.0,
                31.5, 32.0, 32.0, 32.0, 33.0, 33.0, 33.5, 33.5, 34.0, 34.0, 34.5, 35.0,
                35.0, 35.0, 35.0, 36.0, 36.0, 37.0, 38.5, 38.5, 39.5, 41.0, 41.0, 9.8,
                10.5, 10.6, 11.0, 11.2, 11.3, 11.8, 11.8, 12.0, 12.2, 12.4, 13.0, 14.3, 15.0]
fish_weight = [242.0, 290.0, 340.0, 363.0, 430.0, 450.0, 500.0, 390.0, 450.0, 500.0, 475.0, 500.0,
                500.0, 340.0, 600.0, 600.0, 700.0, 700.0, 610.0, 650.0, 575.0, 685.0, 620.0, 680.0,
                700.0, 725.0, 720.0, 714.0, 850.0, 1000.0, 920.0, 955.0, 925.0, 975.0, 950.0, 6.7,
                7.5, 7.0, 9.7, 9.8, 8.7, 10.0, 9.9, 9.8, 12.2, 13.4, 12.2, 19.7, 19.9]

data = [[l,w] for l,w in zip(fish_length,fish_weight)]
target = ['bream']*35 + ['smelt']*14

print(data[5])
print(data[:5])

from sklearn.neighbors import KNeighborsClassifier
knclf = KNeighborsClassifier()

train_data = data[:35]
train_target = target[:35]

test_data = data[35:]
test_target = target[35:]

print(train_data)
print(train_target)

# import time
# before_time = time.time()

knclf.fit(train_data,train_target)
# after_time = time.time()

score = knclf.score(test_data,test_target)
print(score)

pred_value = knclf.predict([[10,20]])
print(f'예측한 값 = {pred_value}')

# print(before_time-after_time)

import numpy as np

# print(data.shape)

input_arr = np.array(data)
target_arr = np.array(target)

print(input_arr.shape)
print(target_arr.shape)

np.random.seed(42)
index = np.arange(49)
print(index)
np.random.shuffle(index)
print(index)

train_input = input_arr[index[:35]]
train_target = target_arr[index[:35]]

print(train_input)
print(train_target)

test_input = input_arr[index[35:]]
test_target = target_arr[index[35:]]

import matplotlib.pyplot as plt

plt.scatter(train_input[:,0],train_input[:,1])
plt.scatter(test_input[:,0],test_input[:,1])
plt.scatter([10,20,30],[300,400,500],marker='^')
plt.savefig('a.png')

knclf.fit(train_input,train_target)
score = knclf.score(test_input,test_target)
print(score)

pred_value = knclf.predict([[10,300],[20,400],[30,500]])
print(pred_value)








