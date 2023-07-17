import numpy as np
import matplotlib.pyplot as plt

fruits = np.load('fruits_300.npy')

print(fruits.shape)

# plt.figure(figsize=(2,2))
# 0 0 0 -> 검정색
# 255 255 255 -> 하얀색

apple = fruits[0:100].reshape(-1,100*100)
pineapple = fruits[100:200].reshape(-1,100*100)
banana = fruits[200:300].reshape(-1,100*100)

print(apple[0])
print(apple.shape)

print(apple.mean(axis=1))
print(pineapple.mean(axis=1))
print(banana.mean(axis=1))

# dopaint(apple,pineapple,banana)

plt.hist(np.mean(apple,axis=1),alpha=0.8)
plt.hist(np.mean(pineapple,axis=1),alpha=0.8)
plt.hist(np.mean(banana,axis=1),alpha=0.8)
plt.show()

# fig,axis = plt.subplots(1,3,figsize=(20,5))
#
# axis[0].bar(range(10000),np.mean(apple,axis=0),alpha=0.8)
# axis[1].bar(range(10000),np.mean(pineapple,axis=0),alpha=0.8)
# axis[2].bar(range(10000),np.mean(banana,axis=0),alpha=0.8)
# plt.show()

def dopaint(apple,pineapple,banana):
    fig,axis = plt.subplots(1,3)
    axis[0].imshow(apple.reshape(-1,100),cmap='gray')
    axis[1].imshow(pineapple.reshape(-1,100),cmap='gray')
    axis[2].imshow(banana.reshape(-1, 100), cmap='gray')
    plt.show()

# dopaint(apple.mean(axis=0),
#         pineapple.mean(axis=0),
#         banana.mean(axis=0))

apple_mean = apple.mean(axis=0).reshape(100,100)
pineapple_mean = pineapple.mean(axis=0).reshape(100,100)
banana_mean = banana.mean(axis=0).reshape(100,100)

abs_diff = np.abs(fruits-banana_mean)
print(abs_diff.shape)
abs_mean = np.mean(abs_diff,axis=(1,2))

apple_index = np.argsort(abs_mean)[:100]
print(apple_index)
fig,axis = plt.subplots(10,10,figsize=(10,10))
for i in range(10):
    for j in range(10):
        axis[i,j].imshow(fruits[apple_index[i*10+j]],cmap='gray_r')
plt.show()





