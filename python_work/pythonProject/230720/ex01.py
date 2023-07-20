# 실행마다 동일한 결과를 얻기 위해 케라스에 랜덤 시드를 사용하고 텐서플로 연산을 결정적으로 만듭니다.

# a = None
# if a:
#     print("a값은 True")
# else:
#     print("a값은 False")
#
# a = 10
# if a:
#     print("a값은 True")
# else:
#     print("a값은 False")

import tensorflow as tf
from tensorflow import keras
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

tf.keras.utils.set_random_seed(42)
tf.config.experimental.enable_op_determinism()

(train_input, train_target), (test_input, test_target) = \
    keras.datasets.fashion_mnist.load_data()

train_scaled = train_input / 255.0

train_scaled, val_scaled, train_target, val_target = train_test_split(
    train_scaled, train_target, test_size=0.2, random_state=42)


def model_fn(a_layer=None):
    model = keras.Sequential()
    model.add(keras.layers.Flatten(input_shape=(28, 28)))
    model.add(keras.layers.Dense(100, activation="relu"))
    if a_layer:
        model.add(a_layer)
    model.add(keras.layers.Dense(10, activation="softmax"))
    return model


model = model_fn(keras.layers.Dropout(0.3))

print(model.summary())

model.compile(
        optimizer="adam",
        loss="sparse_categorical_crossentropy",
        metrics="accuracy")

checkpoint_cb = keras.callbacks.ModelCheckpoint('best-model.h5',
                                                save_best_only=True)
early_stopping_cb = keras.callbacks.EarlyStopping(patience=2,
                                                  restore_best_weights=True)

history = model.fit(train_scaled, train_target, epochs=20, verbose=1,
                    validation_data=[val_scaled,val_target],
                    callbacks=[checkpoint_cb,early_stopping_cb])

model.save_weights('model_weights.h5')
model.save('model_whole.h5')

# print(history.history.keys())

# plt.plot(history.history['loss'])
# plt.plot(history.history['val_loss'])
# plt.xlabel('epoch')
# plt.ylabel('loss')
# plt.show()
#
# plt.plot(history.history['accuracy'])
# plt.plot(history.history['val_accuracy'])
# plt.xlabel('epoch')
# plt.ylabel('accuracy')
# plt.show()











