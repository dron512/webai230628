import numpy as np
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

# model = model_fn(keras.layers.Dropout(0.3))
# model.load_weights('./model_weights.h5')

model = keras.models.load_model('./best-model.h5')

predvalue = model.predict(val_scaled[10:20])
print(np.argmax(predvalue,axis=1))

fig,axs = plt.subplots(1,10)
for i in range(10):
    axs[i].imshow(val_scaled[i+10],cmap='gray_r')
    axs[i].axis('off')

plt.show()













