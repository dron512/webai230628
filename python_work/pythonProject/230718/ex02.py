import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.model_selection import cross_validate
from sklearn.linear_model import SGDClassifier

tf.keras.utils.set_random_seed(42)
tf.config.experimental.enable_op_determinism()

from tensorflow import keras

(train_input, train_target), (test_input, test_target) = \
    keras.datasets.fashion_mnist.load_data()

print(train_input.shape)

# _,axs = plt.subplots(1,10)
# for i in range(10):
#     axs[i].imshow(train_input[i],cmap='gray_r')
#     axs[i].axis('off')
# plt.show()

# 타겟값들
# 0     1   2       3   4    5    6     7    8    9
# 티셔츠 바지 스웨터 드레스 코트 샌달 셔츠 스니커즈 가방 앵글부츠

train_input = train_input.reshape(-1,28*28)
test_input = test_input.reshape(-1,28*28)

train_scaled = train_input/255.0
test_scaled = test_input/255.0

sc = SGDClassifier(loss='log', max_iter=5, random_state=42)
sc.fit(train_scaled,train_target)
# scores = cross_validate(sc, train_input, train_target, n_jobs=-1)

import numpy as np
# print(np.mean(scores['test_score']))

print(   (train_scaled[0]*255.0).reshape(-1,28*28)   )

predvalue = sc.predict((train_scaled[0]*255.0).reshape(-1,28*28))
print(predvalue)

plt.imshow((train_scaled[1]*255.0).reshape(28,28))
plt.show()








