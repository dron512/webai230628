# 타겟값들
# 0     1   2       3   4    5    6     7    8    9
# 티셔츠 바지 스웨터 드레스 코트 샌달 셔츠 스니커즈 가방 앵글부츠
import numpy as np
from tensorflow import keras
# from tensorflow import keras.layers.Dense
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

(train_input, train_target), (test_input, test_target) \
    = keras.datasets.fashion_mnist.load_data()

train_input,val_input,train_target,val_target \
    = train_test_split(train_input, train_target,
                       test_size=0.2, random_state=42)

print(train_input.shape)
print(val_input.shape)

model = keras.Sequential([
              keras.layers.Flatten(input_shape=(28,28)),
              keras.layers.Dense(100,activation="relu",input_shape=(784,)),
              keras.layers.Dense(10,activation="softmax")])

print(model.summary())

# train_scaled = train_input.reshape(-1,784) / 255.0
# test_scaled = test_input.reshape(-1,784) / 255.0

train_scaled = train_input / 255.0
test_scaled = test_input / 255.0

# Adagrad ,RMSprop
model.compile(optimizer='RMSprop',loss="sparse_categorical_crossentropy", metrics="accuracy")
model.fit(train_scaled,train_target,epochs=5)

'''
    sgd loss: 0.4649 - accuracy: 0.8376
    Adagrad loss: 0.6204 - accuracy: 0.7963
    RMSprop loss: 0.3811 - accuracy: 0.8680
'''

score = model.evaluate(test_scaled,test_target)
print(score)

predValue = model.predict(test_scaled[0:5])
print(predValue)
print(np.argmax(predValue,axis=1))
print(test_target[0:5])

print(test_scaled[0].shape)

fig,axis = plt.subplots(1,5)
axis[0].imshow(test_scaled[0].reshape(28,28),cmap='gray_r')
axis[1].imshow(test_scaled[1].reshape(28,28),cmap='gray_r')
axis[2].imshow(test_scaled[2].reshape(28,28),cmap='gray_r')
axis[3].imshow(test_scaled[3].reshape(28,28),cmap='gray_r')
axis[4].imshow(test_scaled[4].reshape(28,28),cmap='gray_r')
plt.show()













