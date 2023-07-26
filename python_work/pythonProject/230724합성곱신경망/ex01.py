# 타겟값들
# 0     1   2       3   4    5    6     7    8    9
# 티셔츠 바지 스웨터 드레스 코트 샌달 셔츠 스니커즈 가방 앵글부츠
# 실행마다 동일한 결과를 얻기 위해 케라스에 랜덤 시드를 사용하고 텐서플로 연산을 결정적으로 만듭니다.
# 8장
# 9장
# nlp
# there are much money. there are many students.
# pip install nltk 영어 문장 끊어주는 거 단어 끊어주는거
# pip install konlpy 한국어 라이브러리

import tensorflow as tf
from tensorflow import keras
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
import cv2

tf.keras.utils.set_random_seed(42)
tf.config.experimental.enable_op_determinism()

(train_input, train_target), (test_input, test_target) = \
    keras.datasets.fashion_mnist.load_data()

train_scaled = train_input.reshape(-1, 28, 28, 1)/255

train_scaled, val_scaled, train_target, val_target = train_test_split(
    train_scaled, train_target, test_size=0.2, random_state=42)

for i in range(10):
    cv2.imwrite(f'a{i}.png',val_scaled[i].reshape(28,28))

# fig, axs = plt.subplots(1,10)
# for i in range(10):
#     axs[i].imshow(val_scaled[i].reshape(28,28),cmap='gray_r')
# plt.show()

# for i in range(10):
#     plt.imshow(val_scaled[i].reshape(28,28))
#     plt.savefig(f'a{i}.png')



'''
model = keras.models.Sequential()
model.add(keras.layers.Conv2D(32, kernel_size=(3,3),
                              input_shape=(28,28,1),
                              activation="relu",
                              padding="same"))
model.add(keras.layers.MaxPooling2D(2))
model.add(keras.layers.Conv2D(64, kernel_size=(3,3),
                              input_shape=(28,28,1),
                              activation="relu",
                              padding="same"))
model.add(keras.layers.MaxPooling2D(2))
model.add(keras.layers.Flatten())
model.add(keras.layers.Dense(100,activation='relu'))
model.add(keras.layers.Dropout(0.3))
model.add(keras.layers.Dense(10,activation='softmax'))
print(model.summary())

model.compile(loss="sparse_categorical_crossentropy",
              optimizer="adam",metrics="accuracy")
checkpoint_cb = keras.callbacks.ModelCheckpoint('best-cnn-model.h5', save_best_only=True)
earlystopping = keras.callbacks.EarlyStopping(patience=2,
                                              restore_best_weights=True)

model.fit(train_scaled,train_target,epochs=20,
          validation_data=(val_scaled,val_target),
          callbacks=[checkpoint_cb,earlystopping])
'''

