from sklearn.linear_model import LogisticRegression
import numpy as np
import cv2

a = cv2.imread('a.png')
b = cv2.imread('b.png')
c = cv2.imread('c.png')

abc = np.concatenate([a, b, c])
print(abc.shape)
abc = abc.reshape(3, -1)
print(abc.shape)

lr = LogisticRegression()
lr.fit(abc, ['A', 'B', 'C'])

test = cv2.imread('test.png')
test = test.reshape(1, -1)
print(test.shape)
predValue = lr.predict(test)
print(predValue)

test = cv2.imread('test1.png')
test = test.reshape(1, -1)
print(test.shape)
predValue = lr.predict(test)
print(predValue)

# a = a.reshape(-1,1)
# print(a.shape)
# b = b.reshape(-1,1)
# print(b.shape)
# c = c.reshape(-1,1)
# print(c.shape)
