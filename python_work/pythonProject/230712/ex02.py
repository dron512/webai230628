import cv2
import matplotlib.pyplot as plt

# cv2 bgr
# plt rgb
dark = cv2.imread('Dark.png',cv2.IMREAD_COLOR)

oridin_dark = cv2.cvtColor(dark,cv2.COLOR_BGR2RGB)
print(oridin_dark.shape)
plt.imshow(oridin_dark)
plt.scatter([10,20,30],[40,50,60])
plt.show()

gray = cv2.cvtColor(oridin_dark,cv2.COLOR_BGR2GRAY)
print(gray.shape)
plt.imshow(cv2.cvtColor(gray,cv2.COLOR_GRAY2BGR))
plt.scatter([10,20,30],[40,50,60])
plt.show()

import numpy as np

test = np.array([
    [ 0,0,1,0,0],
    [ 0,1,1,1,0],
    [ 0,1,1,1,0]
])
plt.imshow(test)
plt.show()
