from sklearn.cluster import KMeans
import numpy as np
import matplotlib.pyplot as plt
import cv2

apple = cv2.imread('apple.png',cv2.IMREAD_GRAYSCALE)
apple = 255 - apple
# print(apple.shape)
# plt.imshow(apple,cmap='gray_r')
# plt.show()

fruits = np.load('fruits_300.npy')
print(fruits.shape)

fruits_2d = fruits.reshape(-1, 10000)
print(fruits_2d.shape)

kmeans = KMeans(n_clusters=3, random_state=42)
kmeans.fit(fruits_2d)

print(kmeans.labels_)
print(np.unique(kmeans.labels_, return_counts=True))

# String k = a==10? "aa","bb";
rows = 1
a = 3 if rows < 2 else 10
print(a)

def draw_fruists(arr,ratio=1):
    n = len(arr)
    rows = int(np.ceil(n / 10))
    cols = n if rows < 2 else 10
    _, axs = plt.subplots(rows,cols,figsize=(cols,rows), squeeze=False)
    for i in range(rows):
        for j in range(cols):
            if(i*10+j)<n:
                axs[i,j].imshow(arr[i*10+j],cmap='gray_r')
            axs[i,j].axis('off')
    plt.show()

draw_fruists(fruits[kmeans.labels_==0])

# KMeans 학습기로 클러스터 평균값 그리기
# draw_fruists(kmeans.cluster_centers_.reshape(-1,100,100),ratio=3)

apple_mean = np.mean(fruits_2d[0:100],axis=0)
pineapple_mean = np.mean(fruits_2d[100:200],axis=0)
banana_mean = np.mean(fruits_2d[200:300],axis=0)
means_array = []
means_array.append(apple_mean)
means_array.append(pineapple_mean)
means_array.append(banana_mean)

means = np.array(means_array)
means = means.reshape(-1,100,100)

# 각이미지 평균값 그리기
# draw_fruists(means,ratio=3)

predValue = kmeans.predict(apple.reshape(-1,10000))
print(predValue)

inertia = []
def doPrint():
    for k in range(2,7):
        kmeans = KMeans(n_clusters=k,random_state=42)
        kmeans.fit(fruits_2d)
        inertia.append(kmeans.inertia_)

    plt.plot(range(2,7),inertia)
    plt.show()

doPrint()








